package com.izx.r2dbc.repository;

import com.izx.r2dbc.entity.RouteRule;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RouteRuleRepository extends ReactiveCrudRepository<RouteRule, Long> {
}
