package com.jck.entity;

import com.jck.observer.ObservableDelegate;

public abstract class NumberArray {
    protected String id;
    protected String name;
    protected ObservableDelegate observableDelegate;

    protected NumberArray(String id, String name) {
        this.id = id;
        this.name = name;
        this.observableDelegate = new ObservableDelegate();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ObservableDelegate getObservableDelegate() {
        return observableDelegate;
    }
}