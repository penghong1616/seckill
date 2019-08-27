package com.ph.miaosha.service;

import com.ph.miaosha.controller.LoginController;
import com.ph.miaosha.dao.UserDao;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.exception.GlobalException;
import com.ph.miaosha.redis.RedisService;
import com.ph.miaosha.redis.UserKey;
import com.ph.miaosha.result.CodeMsg;
import com.ph.miaosha.util.MD5Util;
import com.ph.miaosha.util.UUIDUtil;
import com.ph.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    private static Logger log= LoggerFactory.getLogger(UserService.class);
    public  static final String COOKIE_NAME="userId";
    @Autowired
    UserDao userDao;
    @Autowired
    RedisService redisService;
    public User getById(long id){
        //取缓存
        User user=redisService.get(UserKey.getById,""+id,User.class);
        if (user!=null){
            return user;
        }
        user=userDao.getById(id);
        if (user!=null){
            redisService.set(UserKey.getById,""+id,user);
        }
        return  user;
    }
    public boolean updatePassword(String token,long id,String passwordNew){
        //取对象
        User user=getById(id);
        if (user==null){
            throw new  GlobalException(CodeMsg.MOBILE_NOT_EXT);
        }
        User userUpdate=new User();
        userUpdate.setId(id);
        userUpdate.setPassword(MD5Util.fromPassToDBPass(passwordNew,user.getSalt()));
        userDao.update(userUpdate);
        //处理缓存
        redisService.delete(UserKey.getById,""+id);
        user.setPassword(userUpdate.getPassword());
        redisService.set(UserKey.token,""+token,user);
        return true;
    }
    public Boolean login(HttpServletResponse response,LoginVo loginVo) {
        if (loginVo==null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile=loginVo.getMobile();
        String password=loginVo.getPassword();
        User user=getById(Long.parseLong(mobile));
        if (user==null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXT);
        String dbPass=user.getPassword();
        String saltDB=user.getSalt();
        String calcPass= MD5Util.fromPassToDBPass(password,saltDB);
        if (!calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token=UUIDUtil.uuid();
        addCookie(response,token,user);
        return true;
    }

    public User getByToken( HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        User user=redisService.get(UserKey.token,token,User.class);
        if (user !=null)
             addCookie(response,token,user);
        return user;
    }
    private void addCookie(HttpServletResponse response,String token,User user){
        redisService.set(UserKey.token,token,user);
        Cookie cookie=new Cookie(COOKIE_NAME,token);
        cookie.setMaxAge(UserKey.token.getExpireSecond());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
