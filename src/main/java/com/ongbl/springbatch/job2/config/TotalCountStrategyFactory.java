package com.ongbl.springbatch.job2.config;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TotalCountStrategyFactory {

    private final Map<String, TotalCountStrategy> totalCountStrategies;

    public TotalCountStrategyFactory(List<TotalCountStrategy> totalCountStrategies) {
        this.totalCountStrategies = totalCountStrategies.stream()
                .collect(Collectors.toMap(s -> s.getClass().getSimpleName(), Function.identity()));
    }

    public TotalCountStrategy getTotalCountStrategy(String jobName) {
        return totalCountStrategies.get(jobName + "TotalCountStrategy");
    }
}
