package com.win_lib.xserver.extensions;

import com.win_lib.xconnector.XInputStream;
import com.win_lib.xconnector.XOutputStream;
import com.win_lib.xserver.XClient;
import com.win_lib.xserver.errors.XRequestError;

import java.io.IOException;

public interface Extension {
    String getName();

    byte getMajorOpcode();

    byte getFirstErrorId();

    byte getFirstEventId();

    void handleRequest(XClient client, XInputStream inputStream, XOutputStream outputStream) throws IOException, XRequestError;
}
