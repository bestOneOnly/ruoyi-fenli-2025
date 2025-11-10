package com.sww.document.warning.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sww.document.warning.domain.Warning;
import com.sww.document.warning.dto.WarningApproveReq;
import com.sww.document.warning.enums.WarningActionEnum;
import com.sww.document.warning.mapper.WarningMapper;
import com.sww.document.warning.service.IWarningService;
import org.springframework.stereotype.Service;

@Service
public class WarningActionApproveImpl extends ServiceImpl<WarningMapper, Warning> implements IWarningService<WarningApproveReq> {


    @Override
    public WarningActionEnum support() {
        return WarningActionEnum.APPROVE;
    }

    @Override
    public Class<WarningApproveReq> getClassType() {
        return WarningApproveReq.class;
    }


    @Override
    public boolean handle(WarningApproveReq req) {
        return false;
    }
}
