package com.server.eventmanagerspring.builder;

import com.server.eventmanagerspring.model.Event;

import java.time.LocalDateTime;

public interface Builder {
    void reset();
    
    void setIp(Integer ip);

    void setName(String name);

    void setState(String state);
    void setTime();

    /**
     * @return Product
     */
    Event getResult();
}