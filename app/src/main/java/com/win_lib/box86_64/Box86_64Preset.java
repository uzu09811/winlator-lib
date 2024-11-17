package com.win_lib.box86_64;

import androidx.annotation.NonNull;

public class Box86_64Preset {
    public static final String STABILITY = "STABILITY";
    public static final String COMPATIBILITY = "COMPATIBILITY";
    public static final String INTERMEDIATE = "INTERMEDIATE";
    public static final String PERFORMANCE = "PERFORMANCE";
    public static final String CUSTOM = "CUSTOM";
    public final String id;
    public final String name;

    public Box86_64Preset(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isCustom() {
        return id.startsWith(CUSTOM);
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}