package com.example.startscreen;

import android.app.Application;

public class MyApplication extends Application {
    private boolean shouldExit = false;

    public boolean shouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }
}
