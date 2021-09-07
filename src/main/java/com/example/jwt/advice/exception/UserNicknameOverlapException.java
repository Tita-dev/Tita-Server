package com.example.jwt.advice.exception;

public class UserNicknameOverlapException extends RuntimeException{
    public UserNicknameOverlapException (String msg, Throwable t){
        super(msg, t);
    }
    public UserNicknameOverlapException (String msg){
        super(msg);
    }
    public UserNicknameOverlapException (){
        super();
    }
}
