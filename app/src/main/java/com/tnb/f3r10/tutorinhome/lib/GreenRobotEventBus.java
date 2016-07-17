package com.tnb.f3r10.tutorinhome.lib;

import com.tnb.f3r10.tutorinhome.lib.base.EventBus;

/**
 * Created by f3r10 on 13/7/16.
 */
public class GreenRobotEventBus implements EventBus {

    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }
}
