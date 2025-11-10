package com.sww.document.maintenance.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MaintenanceActionStatusRelation {

    private static final Map<MaintenanceAction, StatusFromTo> relationMap = new HashMap<>();

    static {
        // 行为 = from -> to
        // 创建 = null -> 已创建
        relation(MaintenanceAction.CREATE, null, MaintenanceStatus.CREATED);
        // 派单 = 已创建 -> 已派发
        relation(MaintenanceAction.DISPATCH, MaintenanceStatus.CREATED, MaintenanceStatus.DISPATCHED);
        // 处置 = 已派发 -> 已处置
        relation(MaintenanceAction.DISPOSE, MaintenanceStatus.DISPATCHED, MaintenanceStatus.DISPOSED);
        // 审批 = 已处置 -> 已审批
        relation(MaintenanceAction.APPROVE, MaintenanceStatus.DISPOSED, MaintenanceStatus.APPROVED);

    }

    public static StatusFromTo getStatusFromTo(MaintenanceAction action) {
        Objects.requireNonNull(action, "当前保养行为不能为空");
        StatusFromTo statusFromTo = relationMap.get(action);
        Objects.requireNonNull(statusFromTo, "保养行为对应的状态转换关系不能为空");
        MaintenanceStatus to = statusFromTo.getTo();
        Objects.requireNonNull(to, String.format("保养行为【%s】对应的【to】状态不能为空", action));
        return statusFromTo;
    }


    private static void relation(MaintenanceAction action, MaintenanceStatus from, MaintenanceStatus to) {
        Objects.requireNonNull(action, "保养行为不能为空");
        Objects.requireNonNull(to, "保养行为对应的【to】状态不能为空");
        relationMap.put(action, new StatusFromTo(from, to));
    }

    /**
     * 比如正常行为是已派发 ->已处置  想退回从已处置 ->已派发 ,防止硬编码（尤其在状态出现分支或者中间增加步骤非常有用）
     *
     * @param backAction 返回行为的枚举
     * @param action     需要将from和to调换的枚举
     */
    private static void backRelation(MaintenanceAction backAction, MaintenanceAction action) {
        Objects.requireNonNull(action, "退回保养行为不能为空");
        StatusFromTo statusFromTo = relationMap.get(action);
        MaintenanceStatus from = statusFromTo.getFrom();
        MaintenanceStatus to = statusFromTo.getTo();
        Objects.requireNonNull(from, "退回保养行为对应的【from】状态不能为空");
        Objects.requireNonNull(to, "退回保养行为对应的【to】状态不能为空");
    }


    @Data
    @AllArgsConstructor
    public static class StatusFromTo {

        private MaintenanceStatus from;

        private MaintenanceStatus to;
    }
}
