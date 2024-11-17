package com.win_lib.xserver.events;

import com.win_lib.xconnector.XOutputStream;
import com.win_lib.xconnector.XStreamLock;
import com.win_lib.xserver.Pixmap;
import com.win_lib.xserver.Window;
import com.win_lib.xserver.extensions.PresentExtension;

import java.io.IOException;

public class PresentIdleNotify extends Event {
    private final int eventId;
    private final Window window;
    private final Pixmap pixmap;
    private final int serial;
    private final int idleFence;

    public PresentIdleNotify(int eventId, Window window, Pixmap pixmap, int serial, int idleFence) {
        super(35);
        this.eventId = eventId;
        this.window = window;
        this.serial = serial;
        this.pixmap = pixmap;
        this.idleFence = idleFence;
    }

    @Override
    public void send(short sequenceNumber, XOutputStream outputStream) throws IOException {
        try (XStreamLock lock = outputStream.lock()) {
            outputStream.writeByte(code);
            outputStream.writeByte(PresentExtension.MAJOR_OPCODE);
            outputStream.writeShort(sequenceNumber);
            outputStream.writeInt(0);
            outputStream.writeShort(getEventType());
            outputStream.writeShort((short)0);
            outputStream.writeInt(eventId);
            outputStream.writeInt(window.id);
            outputStream.writeInt(serial);
            outputStream.writeInt(pixmap.id);
            outputStream.writeInt(idleFence);
        }
    }

    public static short getEventType() {
        return 2;
    }

    public static int getEventMask() {
        return 1<<getEventType();
    }
}
