package com.win_lib.xserver.errors;

public class BadPixmap extends XRequestError {
    public BadPixmap(int id) {
        super(4, id);
    }
}
