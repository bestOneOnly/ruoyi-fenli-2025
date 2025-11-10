package com.sww.document.maintenance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sww.document.maintenance.domain.Maintenance;
import org.apache.ibatis.annotations.Select;

public interface MaintenanceMapper extends BaseMapper<Maintenance> {


    /**
     * count(*)+1 是直接获取到下一个单号 防止出现 第一个单号为0的情况
     * 假设 deleted=0 未删除，deleted=1 已删除
     * @return
     */
    @Select(value = "SELECT COUNT(*)+1 FROM t_maintenance WHERE deleted IN (0, 1)")
    Long countAllIncludingDeleted();

}


