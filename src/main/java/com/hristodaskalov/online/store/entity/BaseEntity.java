package com.hristodaskalov.online.store.entity;

import java.time.Clock;
import java.time.LocalDateTime;

public abstract class BaseEntity {

    private LocalDateTime createdTs;

    public BaseEntity() {
        this.createdTs = LocalDateTime.now(Clock.systemUTC());
    }

    public LocalDateTime getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(LocalDateTime createdTs) {
        this.createdTs = createdTs;
    }
}
