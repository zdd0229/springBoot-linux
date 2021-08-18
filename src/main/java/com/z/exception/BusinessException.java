package com.z.exception;

public class BusinessException extends RuntimeException {

    private String id;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BusinessException(String id,String msg){
        this.id=id;
        this.msg=msg;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
