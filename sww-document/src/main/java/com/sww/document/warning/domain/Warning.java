package com.sww.document.warning.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class Warning  implements  Serializable {
    /** 主键 */
    private Long  id;
    /** 报警名称 */
    private String name;
    /** 报警状态 */
    private Integer status;
    /** 派发人员id */
    private Long sendPeopleUserId;
    /** 派发人员对象 */
    @TableField(exist = false)
    private SysUser sendPeopleUser;
    /** 派发备注 */
    private String sendDesc;
    /** 处置人员id */
    private Long disposeUserId;
    /** 处置人员对象 */
    @TableField(exist = false)
    private SysUser disposeUser;
    /** 处置内容 */
    private String disposeContent;
    /** 处置备注 */
    private String disposeDesc;
    /** 审批人员id */
    private Long approveUserId;
    /** 审批人员对象*/
    private SysUser approveUser;
    /** 审批内容*/
    private String approveContent;
    /** 审批备注*/
    private String approveDesc;










}
