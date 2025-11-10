package com.sww.document.warning.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.document.warning.domain.Warning;
import com.sww.document.warning.enums.WarningActionEnum;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractWarningHandler<T> {


    public abstract  WarningActionEnum support();

    public abstract Class<T> getClassType();

    public abstract  boolean handle(T t);

    private Class<T> req;

    @PostConstruct
    public void init(){
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType parameterizedType){
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if(typeArguments.length > 0  && typeArguments[0] instanceof Class){
                this.req = (Class<T>)typeArguments[0];
            }
        }
        if(this.req == null){
            throw new IllegalArgumentException("子类不支持当前类型,具体子类："+this.getClass().getName());
        }

        WarningFactory.register(support(),this);

    }
}
