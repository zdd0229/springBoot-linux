package com.z.exception;

import com.z.exception.ExceptionEnum.StudentException;

public class ExceptionCast {

    public static void cast(StudentException s) throws BusinessException {

        String id=s.getId();
        String msg=s.getMsg();

        throw new BusinessException(id,msg);

    }

}
