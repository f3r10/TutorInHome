package com.tnb.f3r10.tutorinhome.lib.base;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
