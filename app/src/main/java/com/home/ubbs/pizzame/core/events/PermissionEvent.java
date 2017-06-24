package com.home.ubbs.pizzame.core.events;

/**
 * Created by udyatbhanu-mac on 6/21/17.
 */

public class PermissionEvent {

    private int eventType;

    public PermissionEvent(int eventType) {
        this.eventType = eventType;

    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }


}
