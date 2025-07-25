package com.itxuan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Violation {
    private Integer id;
    private Integer score; //违纪分数，从前端获取到


}
