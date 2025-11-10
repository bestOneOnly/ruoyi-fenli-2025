package com.sww.document.maintenance.domain.req;


import lombok.Data;

@Data
public class MaintenanceDisposeReq {

    /** 保养工单主键 */
    private Long id;

    /** 处置描述 */
    private String disposeDesc;

}
