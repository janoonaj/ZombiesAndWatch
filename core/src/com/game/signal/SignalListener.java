package com.game.signal;

import com.badlogic.ashley.signals.Signal;

public interface SignalListener {
    void signalReceived(Signal signal, Object data);
}
