package com.win_lib.xserver;

public interface XLock extends AutoCloseable {
    @Override
    void close();
}
