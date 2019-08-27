package com.ph.miaosha.config;

import com.ph.miaosha.domain.User;
import com.ph.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    UserService userService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?>  clazz=methodParameter.getParameterType();

        return clazz== User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletResponse response=nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        HttpServletRequest request=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String paramToken=request.getParameter(UserService.COOKIE_NAME);
        String cookieToken=getCookie(request,UserService.COOKIE_NAME);
        if (StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return null;
        }
        String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getByToken(response,token);
    }

    private String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies=request.getCookies();
        if(cookies==null||cookies.length<=0){
            return null;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
