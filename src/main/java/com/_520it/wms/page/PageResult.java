package com._520it.wms.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * Created by Helen MM on 2017/8/19.
 */
@Setter
@Getter
@ToString
public class PageResult {
    public static final PageResult EMPTY_PAGE = new PageResult(1,1,0, Collections.EMPTY_LIST) ;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalCount;
    private List<?> data;
    private Integer totalPage;
    private Integer prevPage;
    private Integer nextPage;

    public PageResult(Integer currentPage, Integer pageSize, Integer totalCount, List<?> data ) {
        this.currentPage = currentPage;
        this.pageSize=pageSize;
        this.totalCount= totalCount;
        this.data= data;

        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }
}
