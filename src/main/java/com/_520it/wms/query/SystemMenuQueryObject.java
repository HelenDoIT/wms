package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Helen MM on 2017/9/6.
 */
@Setter@Getter
public class SystemMenuQueryObject extends QueryObject {
    //父目录的id : 需要NULL值
    private Long parentId;
    private String parentSn;

}
