package com.izx.r2dbc.controller;

import com.izx.r2dbc.entity.RouteRule;
import com.izx.r2dbc.service.RouteRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @Autowired
    private RouteRuleService routeRuleService;

    @GetMapping
    public Flux<RouteRule> index() {
        return routeRuleService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<RouteRule> get(@PathVariable(name = "id") Long id) {
        return routeRuleService.findById(id);
    }
}
