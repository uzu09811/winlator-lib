package com.win_lib.renderer;

import com.win_lib.xserver.Drawable;

class RenderableWindow {
    final Drawable content;
    short rootX;
    short rootY;
    final boolean forceFullscreen;

    public RenderableWindow(Drawable content, int rootX, int rootY) {
        this(content, rootX, rootY, false);
    }

    public RenderableWindow(Drawable content, int rootX, int rootY, boolean forceFullscreen) {
        this.content = content;
        this.rootX = (short)rootX;
        this.rootY = (short)rootY;
        this.forceFullscreen = forceFullscreen;
    }
}
