package com.sww.document.warning.service;


import com.sww.document.warning.enums.WarningActionEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WarningFactory {

    private static final Map<WarningActionEnum,AbstractWarningHandler<?>> STRATEGY_MAP = new HashMap<>();
//
//    public WarningFactory(List<IWarningService<?>> list){
//        beanMap.putAll(list.stream().collect(Collectors.toMap(IWarningService::support, Function.identity())));
//    }
//
//    public IWarningService<T>  getWarningHandler(WarningActionEnum warningActionEnum){
//        IWarningService<?> service = Optional.ofNullable(warningActionEnum)
//                .map(beanMap::get)
//                .orElseThrow(() -> new IllegalArgumentException("没有对应的报警行为：" + warningActionEnum));
//        Class<?> classType = service.getClassType();
//
//        return null;
//    }

    public static <T> void register(WarningActionEnum warningActionEnum,AbstractWarningHandler<T> handler){
        STRATEGY_MAP.put(warningActionEnum,handler);
    }

    @SuppressWarnings("unchecked")
    public static <T> ActionHandler<T> getHandler(WarningActionEnum actionEnum, Class<T> expectedType) {
        AbstractWarningHandler<?> handler = STRATEGY_MAP.get(actionEnum);
        if (handler == null) {
            throw new RuntimeException("No handler found for action: " + actionEnum);
        }

        // 检查类型是否匹配
        if (!handler.getClassType().isAssignableFrom(expectedType)) {
            throw new RuntimeException(String.format("Expected type %s but got %s",
                    handler.getClassType().getName(), expectedType.getName()));
        }

        return new ActionHandler<>((AbstractWarningHandler<T>) handler);
    }

    @SuppressWarnings("unchecked")
    public static <T> ActionHandler<T> getHandler(WarningActionEnum actionEnum) {
        AbstractWarningHandler<?> handler = STRATEGY_MAP.get(actionEnum);
        if (handler == null) {
            throw new RuntimeException("No handler found for action: " + actionEnum);
        }
        Class<?> classType = handler.getClassType();
        // 这里无法进行类型检查，但可以提供类型安全的API
        return new ActionHandler<>((AbstractWarningHandler<T>) handler);
    }

    public static class ActionHandler<T> {
        private final AbstractWarningHandler<T> handler;

        private ActionHandler(AbstractWarningHandler<T> handler) {
            this.handler = handler;
        }

        public boolean handle(T request) {
            return handler.handle(request);
        }

//        public boolean handle(T request, Map<String, Object> params) {
//            return handler.handle(request, params);
//        }
    }






}
