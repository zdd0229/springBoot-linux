package com.z.bean.VO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubmitOrderResponseVo {

    private int code;
    private String msg;

}
