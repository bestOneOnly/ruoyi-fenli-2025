package com.sww.document.maintenance.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.AbstractEntity;
import com.sww.document.maintenance.enums.MaintenanceStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 保养工单
 */
@Data
@Accessors(chain = true)
public class Maintenance extends AbstractEntity {

    /**
     * 主键
     */
    private Long id;
    /**
     * 工单编号
     */
    private String no;
    /**
     * 工单状态
     */
    private Integer status;
    /**
     * 工单状态枚举
     */
    @TableField(exist = false)
    private MaintenanceStatus statusEnum;
    /**
     * 工单名称
     */
    private String name;
    /**
     * 工单内容
     */
    private String content;
    /**
     * 派单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchTime;
    /**
     * 派单人
     */
    private Long dispatchUserId;
    /**
     * 派单人姓名
     */
    private String dispatchUserName;
    /**
     * 派单描述
     */
    private String dispatchDesc;
    /**
     * 处置时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disposeTime;
    /**
     * 处置人
     */
    private Long disposeUserId;
    /**
     * 处置人姓名
     */
    private String disposeUserName;
    /**
     * 处置描述
     */
    private String disposeDesc;
    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approveTime;
    /**
     * 审批人
     */
    private Long approveUserId;
    /**
     * 审批人姓名
     */
    private String approveUserName;
    /**
     * 审批描述
     */
    private String approveDesc;
    /**
     * 删除状态
     */
    private Integer deleted;

    public MaintenanceStatus getStatusEnum() {
        if (status != null) {
            statusEnum = MaintenanceStatus.getByCode(status);
        }
        return statusEnum;
    }
}
