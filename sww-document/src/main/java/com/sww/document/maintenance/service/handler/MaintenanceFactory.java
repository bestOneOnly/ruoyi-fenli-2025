package com.sww.document.maintenance.service.handler;

import com.sww.document.maintenance.enums.MaintenanceAction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MaintenanceFactory {


    private static final Map<MaintenanceAction, AbstractMaintenanceHandler<?>> handlerMap = new HashMap<>();


    public static  void register(MaintenanceAction action, AbstractMaintenanceHandler<?> handler) {
        handlerMap.put(action, handler);
    }

    public <Req> handleHelper<Req> getHandler(MaintenanceAction action) {
        AbstractMaintenanceHandler<Req> handler = (AbstractMaintenanceHandler<Req>) handlerMap.get(action);
        if (handler == null) {
            throw new IllegalArgumentException(String.format("保养行为【%s】对应的处理类不能为空", action));
        }
        return new handleHelper<>(handler);
    }

    public static class handleHelper<Req> {

        private final AbstractMaintenanceHandler<Req> handler;

        public handleHelper(AbstractMaintenanceHandler<Req> handler) {
            this.handler = handler;
        }

        //提交操作
        public boolean handle(Req req) {
            return handler.beforeHandle(req);
        }


    }


}
