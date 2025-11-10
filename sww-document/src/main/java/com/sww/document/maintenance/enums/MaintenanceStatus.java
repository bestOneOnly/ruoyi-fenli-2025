package com.sww.document.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum MaintenanceStatus {
    /**
     * 已创建
     */
    CREATED(1, "已创建"),
    /**
     * 已派单
     */
    DISPATCHED(2, "已派单"),
    /**
     * 已处置
     */
    DISPOSED(3, "已处置"),
    /**
     * 已审批
     */
    APPROVED(4, "已审批"),
    ;
    private final Integer code;

    private final String desc;

    /**
     * 根据状态码获取状态枚举
     * @param code 状态码
     * @return 状态枚举
     */
    public static MaintenanceStatus getByCode(Integer code) {
        MaintenanceStatus status = CACHE.get(code);
        if (status == null) {
            throw new IllegalArgumentException("状态码不存在");
        }
        return status;
    }

    private static final Map<Integer, MaintenanceStatus> CACHE = new HashMap<>();

    static {
        for (MaintenanceStatus status : values()) {
            CACHE.put(status.getCode(), status);
        }
    }

    @JsonValue
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("value", this.code);
        map.put("label", this.desc);
        map.put("enumName", this.name());
        return map;
    }

}
