package com.ruoyi.framework.config.properties;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // 针对基本类型long的包装类型Long
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            // 针对基本类型long
//            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
        };
    }
}
