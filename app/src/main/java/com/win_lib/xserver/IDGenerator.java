package com.win_lib.xserver;

public abstract class IDGenerator {
    private static int id = 0;

    public static int generate() {
        return ++id;
    }
}
