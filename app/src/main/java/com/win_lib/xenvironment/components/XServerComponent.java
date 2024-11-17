package com.win_lib.xenvironment.components;

import com.win_lib.xenvironment.EnvironmentComponent;
import com.win_lib.xconnector.XConnectorEpoll;
import com.win_lib.xconnector.UnixSocketConfig;
import com.win_lib.xserver.XClientConnectionHandler;
import com.win_lib.xserver.XClientRequestHandler;
import com.win_lib.xserver.XServer;

public class XServerComponent extends EnvironmentComponent {
    private XConnectorEpoll connector;
    private final XServer xServer;
    private final UnixSocketConfig socketConfig;

    public XServerComponent(XServer xServer, UnixSocketConfig socketConfig) {
        this.xServer = xServer;
        this.socketConfig = socketConfig;
    }

    @Override
    public void start() {
        if (connector != null) return;
        connector = new XConnectorEpoll(socketConfig, new XClientConnectionHandler(xServer), new XClientRequestHandler());
        connector.setInitialInputBufferCapacity(262144);
        connector.setCanReceiveAncillaryMessages(true);
        connector.start();
    }

    @Override
    public void stop() {
        if (connector != null) {
            connector.stop();
            connector = null;
        }
    }

    public XServer getXServer() {
        return xServer;
    }
}
