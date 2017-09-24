package com._520it.wms.domain;

import com._520it.wms.util.FileUploadUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Helen MM on 2017/9/7.
 */
@Setter@Getter
public class Product extends BaseDomain {
    private String name;
    private String sn;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private String imagePath;
    private String intro;
    private Brand brand;

    public String getSmallImagePath(){
        if(StringUtils.isEmpty(imagePath)){
            return "";
        }
        //imagePath:d3018ea6-f224-4890-a9a7-73e1aaa71e81.jpg
        //smallImagePath:d3018ea6-f224-4890-a9a7-73e1aaa71e81_small.jpg
        //通过imagePath 拼接 FileUploadUtil.suffix 得出 smallImagePath,
        int index=imagePath.lastIndexOf(".");
        return imagePath.substring(0,index)+ FileUploadUtil.suffix+imagePath.substring(index);
    }

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",super.getId());
        map.put("name",this.name);
        map.put("brandName",this.brand != null ? this.brand.getName() : "" );
        map.put("costPrice",this.costPrice);
        map.put("salePrice",this.salePrice);
        return JSON.toJSONString(map);
    }
}
