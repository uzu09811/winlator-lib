package com.win_lib.xserver;

public abstract class XResource {
    public final int id;

    public XResource(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
