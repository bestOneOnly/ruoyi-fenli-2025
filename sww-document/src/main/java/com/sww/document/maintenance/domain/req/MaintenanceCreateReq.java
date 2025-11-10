package com.sww.document.maintenance.domain.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 保养工单创建请求体
 */
@Data
public class MaintenanceCreateReq {

    /** 保养工单主键 */
    private Long id;
    /** 保养工单名称 */
    @NotBlank(message = "保养工单名称不能为空")
    private String name;
    /**保养工单内容 */
    private String content;

}
