package com.sww.document.maintenance.domain.req;

import lombok.Data;

@Data
public class MaintenanceApproveReq {

     /** 保养工单主键 */
    private Long id;
     /**  审批描述 */
    private String approveDesc;

}
