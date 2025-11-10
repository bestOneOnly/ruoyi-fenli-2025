package com.sww.document.maintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceQueryReq;
import com.sww.document.maintenance.enums.MaintenancePageQuery;
import com.sww.document.maintenance.enums.MaintenanceStatus;
import com.sww.document.maintenance.mapper.MaintenanceMapper;
import com.sww.document.maintenance.service.IMaintenanceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements IMaintenanceService {



    @Override
    public List<Maintenance> queryList(MaintenanceQueryReq queryReq, MaintenancePageQuery pageQuery) {
        LambdaQueryWrapper<Maintenance> queryWrapper = Wrappers.lambdaQuery(Maintenance.class);
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getName()), Maintenance::getName, queryReq.getName())
                .like(StringUtils.isNotBlank(queryReq.getNo()), Maintenance::getNo, queryReq.getNo())
                .eq(queryReq.getStatus() != null, Maintenance::getStatus, queryReq.getStatus());
        switch (pageQuery) {
            case PAGE_CREATE_AND_DISPATCH ->{
                List<Integer> codes = List.of(MaintenanceStatus.CREATED.getCode(), MaintenanceStatus.DISPATCHED.getCode());
                queryWrapper.in(Maintenance::getStatus, codes);
            }
            case PAGE_DISPOSE -> queryWrapper.eq(Maintenance::getDispatchUserId, SecurityUtils.getLoginUser().getUserId());
            case PAGE_APPROVE -> {
                List<Integer> codes = List.of(MaintenanceStatus.DISPOSED.getCode(), MaintenanceStatus.APPROVED.getCode());
                queryWrapper.in(Maintenance::getStatus, codes);
            }
            case PAGE_RECORD -> queryWrapper.eq(Maintenance::getStatus, MaintenanceStatus.APPROVED.getCode());
            default -> throw new IllegalArgumentException("不支持的查询类型:" + pageQuery);
        }
        return this.list(queryWrapper);
    }
}
