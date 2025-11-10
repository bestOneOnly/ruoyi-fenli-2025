package com.sww.document.warning.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * 比较操作枚举
 */
@Getter
@AllArgsConstructor
@Validated
public enum CompareOperatorEnum {
    /**
     * 等于
     */
    EQUAL(1, "等于", (a, b) -> toBigDecimal(a).compareTo(toBigDecimal(b)) == 0),
    /**
     * 不等于
     */
    NOT_EQUAL(2, "不等于", (a, b) -> toBigDecimal(a).compareTo(toBigDecimal(b)) != 0),
    /**
     * 大于
     */
    GREATER_THAN(3, "大于", (a, b) -> toBigDecimal(a).compareTo(toBigDecimal(b)) > 0),
    /**
     * 大于等于
     */
    GREATER_THAN_OR_EQUAL(4, "大于等于", (a, b) -> toBigDecimal(a).compareTo(toBigDecimal(b)) >= 0),
    /**
     * 小于
     */
    LESS_THAN(5, "小于", (a, b) -> toBigDecimal(a).compareTo(toBigDecimal(b)) < 0),
    /**
     * 小于等于
     */
    LESS_THAN_OR_EQUAL(6, "小于等于", (a, b) -> toBigDecimal(a).compareTo(toBigDecimal(b)) <= 0);


    private final Integer code;

    private final String desc;

    private final BiFunction<Number,Number,Boolean> compareFunction;

    /**
     * 根据code获取当前枚举，static方法
     */


    public static CompareOperatorEnum getByCode(Integer code){
        for (CompareOperatorEnum compareOperatorEnum : values()) {
            if(compareOperatorEnum.getCode().equals(code)){
                return compareOperatorEnum;
            }
        }
        throw new IllegalArgumentException("没有当前类型");
    }

    /**
     * 比较两个数是否符合当前枚举的比较规则
     */
    public boolean compare(@NotNull Number real, @NotNull Number expect){
        // 添加显式的null检查，确保使用者不能传递null值
        Objects.requireNonNull(real, "real参数不能为null");
        Objects.requireNonNull(expect, "expect参数不能为null");
        return compareFunction.apply(real, expect);
    }

    /**
     * 将Number转换为BigDecimal，避免精度问题
     */
    private static BigDecimal toBigDecimal(Number number) {
        // 对于Float和Double，使用字符串构造避免精度问题
        if (number instanceof Float || number instanceof Double) {
            return new BigDecimal(number.toString());
        }
        // 对于整数类型，使用longValue
        return BigDecimal.valueOf(number.longValue());
    }
}