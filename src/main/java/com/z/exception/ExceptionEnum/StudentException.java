package com.z.exception.ExceptionEnum;

public enum StudentException {


    NODATA("0001","没有数据"),INSERTFAIL("0002","插入失败");


    private StudentException(String id,String msg ){
        this.id=id;
        this.msg=msg;
    }

    private String id;

    private String msg;

    public String getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }
}
