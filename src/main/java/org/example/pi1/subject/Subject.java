package org.example.pi1.subject;

import org.example.pi1.observer.IObserver;

public interface Subject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyAllObservers();
}
