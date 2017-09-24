package com._520it.wms;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * Created by Helen MM on 2017/8/21.
 */

public class WMSTest {
    @Test
    public void test1() throws Exception{
        String password = ConfigTools.encrypt("admin");
        System.out.println(password);
    }
}
