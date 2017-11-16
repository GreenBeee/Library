package com.botscrew;

import com.botscrew.logic.Handler;

public class App {
    public static void main(String[] args) {
        Handler handler = new Handler();
        handler.handle();

        System.exit(0);
    }
}
