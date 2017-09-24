package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/8/19.
 */
public class QueryObject {
    @Setter
    @Getter
    private Integer currentPage = 1;
    @Setter
    @Getter
    private Integer pageSize = 3;

    public Integer getStart(){
        return (currentPage - 1)*pageSize;
    }

    //处理关键字为空字符串的情况: 把空字符串转换为NULL
    public String blank2Null(String keyword){
        if(keyword != null && !"".equals(keyword.trim()) ){
            return  keyword;
        }
        return null;
    }
}
