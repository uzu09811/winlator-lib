package com.win_lib.xenvironment.components;

import com.win_lib.alsaserver.ALSAClientConnectionHandler;
import com.win_lib.alsaserver.ALSARequestHandler;
import com.win_lib.xconnector.UnixSocketConfig;
import com.win_lib.xconnector.XConnectorEpoll;
import com.win_lib.xenvironment.EnvironmentComponent;

public class ALSAServerComponent extends EnvironmentComponent {
    private XConnectorEpoll connector;
    private final UnixSocketConfig socketConfig;

    public ALSAServerComponent(UnixSocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }

    @Override
    public void start() {
        if (connector != null) return;
        connector = new XConnectorEpoll(socketConfig, new ALSAClientConnectionHandler(), new ALSARequestHandler());
        connector.setMultithreadedClients(true);
        connector.start();
    }

    @Override
    public void stop() {
        if (connector != null) {
            connector.stop();
            connector = null;
        }
    }
}
