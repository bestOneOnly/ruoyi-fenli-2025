package com.sww.document.maintenance.service.handler;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.enums.MaintenanceAction;
import com.sww.document.maintenance.enums.MaintenanceActionStatusRelation;
import com.sww.document.maintenance.enums.MaintenanceStatus;
import com.sww.document.maintenance.mapper.MaintenanceMapper;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

@Data
public abstract class AbstractMaintenanceHandler<Req>  extends ServiceImpl<MaintenanceMapper, Maintenance>  {


    private Class<Req> reqClass;


    @PostConstruct
    public void init() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType parameterizedType) {
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if(actualTypeArguments.length >0 && actualTypeArguments[0] instanceof Class<?> clazz) {
                reqClass = (Class<Req>) clazz;
                MaintenanceFactory.register(support(), this);
            }
        }else {
            throw new IllegalArgumentException("泛型参数类型错误");
        }
    }



    /**
     * 支持的操作
     *
     * @return 操作枚举
     */
    protected abstract MaintenanceAction support();

    /**
     * 处理保养工单
     *
     * @param body 保养工单请求体
     * @param from 操作前状态
     * @param to 操作后状态
     */
    protected abstract boolean handle(Req body, Integer from, Integer to);


    /**
     * 处理前根据行为分发状态
     * @param body
     * @return
     */
    protected boolean beforeHandle(Req body) {
        MaintenanceAction action = this.support();
        MaintenanceActionStatusRelation.StatusFromTo statusFromTo = MaintenanceActionStatusRelation.getStatusFromTo(action);
        //from枚举允许为null,所以code也要提供默认null
        Integer from = Optional.ofNullable(statusFromTo)
                .map(MaintenanceActionStatusRelation.StatusFromTo::getFrom)
                .map(MaintenanceStatus::getCode)
                .orElse(null);
        return handle(body, from, statusFromTo.getTo().getCode());
    }




}
