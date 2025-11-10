package com.sww.document.warning.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum WarningStatusEnum {

    UN_CREATE(0,"未创建"),
    FINISH_DELEGATE_PERSON_WAIT_DISPOSE(1,"已派发待处置"),
    FINISH_DISPOSE_WAIT_APPROVE(2,"已处置待审批"),
    FINISH_APPROVE(3,"已审批")
    ;

    private final Integer status;
    private final String desc;

    private static final Map<Integer, WarningStatusEnum> cache;


    static {
        cache = Arrays.stream(WarningStatusEnum.values()).collect(Collectors.toMap(WarningStatusEnum::getStatus, Function.identity()));
    }

    public static  WarningStatusEnum of(Integer status){
        return Optional.ofNullable(cache.get(status))
                .orElseThrow(() -> new IllegalArgumentException("报警没有当前行为status：" + status));
    }

}
