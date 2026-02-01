package org.example.pi1.observer;

import org.example.pi1.subject.Subject;

public interface IObserver {
    void update(Subject subject);
}
