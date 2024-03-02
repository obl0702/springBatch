package com.ongbl.springbatch.job2.config;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TotalCountStrategyFactory {

    private final Map<String, TotalCountStrategy> totalCountStrategies;

    public TotalCountStrategyFactory(List<TotalCountStrategy> totalCountStrategies) {
        this.totalCountStrategies = totalCountStrategies.stream()
                .collect(Collectors.toMap(s -> s.getClass().getSimpleName(), Function.identity()));
    }

    public TotalCountStrategy getTotalCountStrategy(String jobName) {
        for (String key :  totalCountStrategies.keySet()) {
            System.out.println("key: " + key);
        }
        return totalCountStrategies.get(jobName + "TotalCountStrategy");
    }
}
