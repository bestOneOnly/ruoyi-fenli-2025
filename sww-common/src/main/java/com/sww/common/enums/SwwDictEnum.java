package com.sww.common.enums;

import lombok.Getter;

/**
 * SwwDictEnum 接口
 * 这是一个字典枚举的接口，定义了获取代码和描述信息的默认方法
 * 使用了 Lombok 的 @Getter 注解，为接口提供 getter 方法的默认实现
 */

public interface SwwDictEnum {

    /**
     * 获取代码的默认方法
     * @return 返回一个整型代码
     * @throws NoSuchMethodException 当子类没有实现此方法时抛出异常
     */
     int getCode();

    /**
     * 获取描述信息的默认方法
     * @return 返回一个字符串描述
     */
     String getDesc();


}
