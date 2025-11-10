package com.sww.document.maintenance.domain.req;

import lombok.Data;

@Data
public class MaintenanceQueryReq {
    /** 保养工单主键 */
    private Long id;
     /** 保养工单编号 */
    private String no;
     /** 保养工单名称 */
    private String name;
     /** 保养工单状态 */
    private Integer status;

}
