package com.game.miniGame.signal;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

public class EventListener implements Listener {
    private final SignalListener listener;

    public EventListener(SignalListener listener) {
        this.listener = listener;
    }

    @Override
    public void receive(Signal signal, Object object) {
        listener.signalReceived(signal, object);
    }
}
