package com.ph.miaosha.exception;

import com.ph.miaosha.result.CodeMsg;

public class GlobalException extends  RuntimeException {
    private CodeMsg codeMsg;
    public  GlobalException(CodeMsg msg){
        super(msg.toString());
        this.codeMsg=msg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
