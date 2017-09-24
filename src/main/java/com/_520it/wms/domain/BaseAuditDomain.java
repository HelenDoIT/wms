package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Helen MM on 2017/9/12.
 */
@Getter@Setter
public class BaseAuditDomain extends BaseDomain {
    public static final int NORMAL = 0;
    public static final int AUDITED = 1;
    private int status;
    private Date auditTime;
    private Date inputTime;
    private Employee inputUser;
    private Employee auditor;
}
