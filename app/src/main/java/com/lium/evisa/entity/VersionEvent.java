package com.lium.evisa.entity;

/**
 * Created by lenovo on 2016/4/15.
 */
public class VersionEvent {
    private EventMessage message;

    public VersionEvent(EventMessage message)
    {
        this.message = message;
    }

    public EventMessage getMessage() {
        return message;
    }
}
