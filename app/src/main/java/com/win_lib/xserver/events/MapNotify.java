package com.win_lib.xserver.events;

import com.win_lib.xconnector.XOutputStream;
import com.win_lib.xconnector.XStreamLock;
import com.win_lib.xserver.Window;

import java.io.IOException;

public class MapNotify extends Event {
    private final Window event;
    private final Window window;

    public MapNotify(Window event, Window window) {
        super(19);
        this.event = event;
        this.window = window;
    }

    @Override
    public void send(short sequenceNumber, XOutputStream outputStream) throws IOException {
        try (XStreamLock lock = outputStream.lock()) {
            outputStream.writeByte(code);
            outputStream.writeByte((byte)0);
            outputStream.writeShort(sequenceNumber);
            outputStream.writeInt(event.id);
            outputStream.writeInt(window.id);
            outputStream.writeByte((byte)(window.attributes.isOverrideRedirect() ? 1 : 0));
            outputStream.writePad(19);
        }
    }
}
