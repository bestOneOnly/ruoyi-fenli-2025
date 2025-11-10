package com.sww.document.warning.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sww.document.warning.domain.Warning;
import com.sww.document.warning.dto.WarningSendPeopleReq;
import com.sww.document.warning.enums.WarningActionEnum;
import com.sww.document.warning.mapper.WarningMapper;
import com.sww.document.warning.service.IWarningService;
import org.springframework.stereotype.Service;

@Service
public class WarningActionDelegatePersonImpl extends ServiceImpl<WarningMapper, Warning> implements IWarningService<WarningSendPeopleReq> {
    @Override
    public WarningActionEnum support() {
        return WarningActionEnum.DELEGATE_PERSON;
    }

    @Override
    public Class<WarningSendPeopleReq> getClassType() {
        return WarningSendPeopleReq.class;
    }



    @Override
    public boolean handle(WarningSendPeopleReq req) {


        return false;
    }
}
