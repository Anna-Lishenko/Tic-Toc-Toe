package com.exception;

public class EnterIndexException extends  Exception {
    public EnterIndexException (Integer index){
        super(String.valueOf(index));
    }
}
