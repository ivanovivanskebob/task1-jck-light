package com.jck.observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableDelegate implements Observable {
    private List<Observer> observers;

    public ObservableDelegate() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object source, Object eventData) {
        for (Observer observer : observers) {
            observer.update(source, eventData);
        }
    }
}