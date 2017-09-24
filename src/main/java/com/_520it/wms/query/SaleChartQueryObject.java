package com._520it.wms.query;

import com._520it.wms.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Helen MM on 2017/9/12.
 */
@Setter@Getter
public class SaleChartQueryObject extends QueryObject {
    public static  final Map<String,String> GROUP_TYPES =new LinkedHashMap<>();

    static {
        GROUP_TYPES.put("sm.name","销售人员");
        GROUP_TYPES.put("p.name","货品名称");
        GROUP_TYPES.put("c.name","客户");
        GROUP_TYPES.put("b.name","货品品牌");
        GROUP_TYPES.put("date_format(sa.vdate,'%Y-%m')","订货日期(月)");
        GROUP_TYPES.put("date_format(sa.vdate,'%Y-%m-%d')","订货日期(日)");
    }

    private  Date beginDate;
    private  Date endDate;
    private String keyword;
    private Long clientId = -1L;
    private Long brandId = -1L;
    private String groupType ="sm.name";

    public Date getEndDate(){
        return DateUtils.getEndDate(this.endDate);
    }
}
