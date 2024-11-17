package com.win_lib.xenvironment.components;

import com.win_lib.sysvshm.SysVSHMConnectionHandler;
import com.win_lib.sysvshm.SysVSHMRequestHandler;
import com.win_lib.sysvshm.SysVSharedMemory;
import com.win_lib.xconnector.UnixSocketConfig;
import com.win_lib.xconnector.XConnectorEpoll;
import com.win_lib.xenvironment.EnvironmentComponent;
import com.win_lib.xserver.SHMSegmentManager;
import com.win_lib.xserver.XServer;

public class SysVSharedMemoryComponent extends EnvironmentComponent {
    private XConnectorEpoll connector;
    public final UnixSocketConfig socketConfig;
    private SysVSharedMemory sysVSharedMemory;
    private final XServer xServer;

    public SysVSharedMemoryComponent(XServer xServer, UnixSocketConfig socketConfig) {
        this.xServer = xServer;
        this.socketConfig = socketConfig;
    }

    @Override
    public void start() {
        if (connector != null) return;
        sysVSharedMemory = new SysVSharedMemory();
        connector = new XConnectorEpoll(socketConfig, new SysVSHMConnectionHandler(sysVSharedMemory), new SysVSHMRequestHandler());
        connector.start();

        xServer.setSHMSegmentManager(new SHMSegmentManager(sysVSharedMemory));
    }

    @Override
    public void stop() {
        if (connector != null) {
            connector.stop();
            connector = null;
        }

        sysVSharedMemory.deleteAll();
    }
}
