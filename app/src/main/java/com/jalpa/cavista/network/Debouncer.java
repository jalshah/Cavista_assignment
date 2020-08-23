package com.jalpa.cavista.network;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Debounce class is responsible to send request every 250 millisec.
 */
public class Debouncer {
    Handler handler = new Handler(Looper.getMainLooper());

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<Object, Future<?>> delayedMap = new ConcurrentHashMap<>();

    public void debounce(final Object key, final Runnable runnable) {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 250);
    }

}


