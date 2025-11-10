package com.sww.document.warning.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sww.document.warning.domain.Warning;
import com.sww.document.warning.dto.WarningActionDisposeReq;
import com.sww.document.warning.enums.WarningActionEnum;
import com.sww.document.warning.mapper.WarningMapper;
import com.sww.document.warning.service.IWarningService;
import org.springframework.stereotype.Service;

@Service
public class WarningActionDisposeImpl extends ServiceImpl<WarningMapper, Warning> implements IWarningService<WarningActionDisposeReq> {
    @Override
    public WarningActionEnum support() {
        return WarningActionEnum.DISPOSE;
    }

    @Override
    public Class<WarningActionDisposeReq> getClassType() {
        return WarningActionDisposeReq.class;
    }

    @Override
    public boolean handle(WarningActionDisposeReq warningActionDisposeReq) {


        return false;
    }
}
