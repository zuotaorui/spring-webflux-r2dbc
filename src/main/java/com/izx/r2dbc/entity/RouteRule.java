package com.izx.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("im_route_rule")
public class RouteRule {

    @Id
    private Long id;

    /**
     * 路由id
     */
    @Column("route_id")
    private String routeId;

    @Column("uri")
    private String uri;

    private Integer ordered;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    //    @UpdateTimestamp
    @Column("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private long version;

}
