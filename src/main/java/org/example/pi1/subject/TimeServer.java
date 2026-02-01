package org.example.pi1.subject;

import org.example.pi1.observer.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimeServer implements Subject {

    private int timeState = 0;
    private Timer timer;
    private boolean running = false;

    private final List<IObserver> observers = new ArrayList<>();

    public void start() {
        if (running) return;
        running = true;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, 1000);
    }

    public void stop() {
        running = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void tick() {
        timeState++;
        notifyAllObservers();
    }

    public int getState() {
        return timeState;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (IObserver observer : observers) {
            observer.update(this);
        }
    }
}
