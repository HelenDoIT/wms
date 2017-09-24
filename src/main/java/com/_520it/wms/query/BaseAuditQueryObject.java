package com._520it.wms.query;

import com._520it.wms.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Helen MM on 2017/9/12.
 */
@Setter@Getter
public class BaseAuditQueryObject extends QueryObject {
    private Date beginDate;
    private  Date endDate;
    private Integer status = -1;
    public Date getEndDate(){
        return DateUtils.getEndDate(this.endDate);
    }
}
