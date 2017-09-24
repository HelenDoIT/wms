package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/21.
 */
@Setter@Getter
public class EmployeeQueryObject extends QueryObject {
    private  String keyword;
    private Long deptId = -1L; //默认-1为所有部门

    public String getKeyword(){
        return blank2Null(keyword);
    }
}
