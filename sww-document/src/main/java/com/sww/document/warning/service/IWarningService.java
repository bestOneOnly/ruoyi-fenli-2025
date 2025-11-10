package com.sww.document.warning.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.document.warning.domain.Warning;
import com.sww.document.warning.enums.WarningActionEnum;

public interface IWarningService<T> extends IService<Warning> {


    WarningActionEnum support();

    Class<T> getClassType();

    boolean handle(T t);
}
