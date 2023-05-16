package com.server.eventmanagerspring.event.builder;

import com.server.eventmanagerspring.event.model.Event;

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