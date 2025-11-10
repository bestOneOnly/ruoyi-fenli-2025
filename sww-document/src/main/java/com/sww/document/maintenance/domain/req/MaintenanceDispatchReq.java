package com.sww.document.maintenance.domain.req;

import lombok.Data;

@Data
public class MaintenanceDispatchReq {

     /** 保养工单主键 */
    private Long id;
     /** 派单用户主键 */
    private Long dispatchUserId;
     /**  派单描述 */
    private String dispatchDesc;

}
