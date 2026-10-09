package com.jck.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObservableDelegateTest {
    private static final String EVENT_DATA = "testEvent";
    private static final Object SOURCE = new Object();

    private ObservableDelegate delegate;
    private List<String> receivedEvents;

    @BeforeEach
    void setUp() {
        delegate = new ObservableDelegate();
        receivedEvents = new ArrayList<>();
    }

    @Test
    void addObserver_validObserver_addsToList() {
        // given
        Observer observer = createTrackingObserver();

        // when
        delegate.addObserver(observer);
        delegate.notifyObservers(SOURCE, EVENT_DATA);

        // then
        assertEquals(1, receivedEvents.size());
        assertEquals(EVENT_DATA, receivedEvents.get(0));
    }

    @Test
    void removeObserver_existingObserver_removesFromList() {
        // given
        Observer observer = createTrackingObserver();
        delegate.addObserver(observer);

        // when
        delegate.removeObserver(observer);
        delegate.notifyObservers(SOURCE, EVENT_DATA);

        // then
        assertTrue(receivedEvents.isEmpty());
    }

    @Test
    void notifyObservers_multipleObservers_notifiesAll() {
        // given
        List<String> firstObserverEvents = new ArrayList<>();
        List<String> secondObserverEvents = new ArrayList<>();
        Observer firstObserver = (source, data) -> firstObserverEvents.add((String) data);
        Observer secondObserver = (source, data) -> secondObserverEvents.add((String) data);
        delegate.addObserver(firstObserver);
        delegate.addObserver(secondObserver);

        // when
        delegate.notifyObservers(SOURCE, EVENT_DATA);

        // then
        assertEquals(1, firstObserverEvents.size());
        assertEquals(1, secondObserverEvents.size());
    }

    @Test
    void notifyObservers_noObservers_doesNotThrow() {
        // given
        // when & then
        delegate.notifyObservers(SOURCE, EVENT_DATA);
    }

    @Test
    void notifyObservers_passesCorrectSource() {
        // given
        Object[] receivedSource = new Object[1];
        Observer observer = (source, data) -> receivedSource[0] = source;
        delegate.addObserver(observer);

        // when
        delegate.notifyObservers(SOURCE, EVENT_DATA);

        // then
        assertEquals(SOURCE, receivedSource[0]);
    }

    @Test
    void removeObserver_nonExistingObserver_doesNotThrow() {
        // given
        Observer observer = createTrackingObserver();

        // when & then
        delegate.removeObserver(observer);
    }

    private Observer createTrackingObserver() {
        return (source, data) -> receivedEvents.add((String) data);
    }
}