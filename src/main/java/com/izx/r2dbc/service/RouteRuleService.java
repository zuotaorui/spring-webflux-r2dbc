package com.izx.r2dbc.service;

import com.izx.r2dbc.entity.RouteRule;
import com.izx.r2dbc.repository.RouteRuleRepository;
import com.izx.r2dbc.util.DataConstant;
import com.izx.r2dbc.util.JsonUtil;
import com.izx.r2dbc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.InstanceNotFoundException;
import java.time.Duration;
import java.time.Instant;

@Service
public class RouteRuleService {

    @Autowired
    private RouteRuleRepository routeRuleRepository;

    @Autowired
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    public Flux<RouteRule> findAll() {
        return routeRuleRepository.findAll();
    }

    public Mono<RouteRule> findById(Long id) {
        return findByIdFromRedis(id)
                .switchIfEmpty(findByIdFromDB(id));
    }

    private Mono<RouteRule> findByIdFromRedis(Long id) {
        String redisKey = RedisUtil.getKey(DataConstant.STR_ROUTE_RULE, String.valueOf(id));
        return reactiveStringRedisTemplate.opsForValue().get(redisKey)
                .switchIfEmpty(Mono.empty())
                .transform(mono -> mono.map(val -> JsonUtil.string2Obj(val, RouteRule.class)));
    }

    private Mono<RouteRule> findByIdFromDB(Long id) {
        String redisKey = RedisUtil.getKey(DataConstant.STR_ROUTE_RULE, String.valueOf(id));
        return routeRuleRepository.findById(id)
                .switchIfEmpty(Mono.error(new InstanceNotFoundException("route rule not exist")))
                .doOnSuccess(routeRule -> reactiveStringRedisTemplate.opsForValue()
                        .set(redisKey, JsonUtil.obj2String(routeRule), Duration.ofMinutes(10L))
                        .subscribe());
    }
}
