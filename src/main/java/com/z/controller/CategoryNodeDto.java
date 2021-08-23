package com.z.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CategoryNodeDto {

    private String name;
//    private Long parentCid;
//    private Long catLevel;
    private Long sort;
//    private String icon;

    private List<CategoryNodeDto> nextLevel;

}
