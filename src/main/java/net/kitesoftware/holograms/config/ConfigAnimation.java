package net.kitesoftware.holograms.config;

import java.util.List;

public class ConfigAnimation {

    private String name;
    private Double refresh;
    private List<String> frames;

    ConfigAnimation(String name, Double refresh, List<String> frames) {
        this.name = name;
        this.refresh = refresh;
        this.frames = frames;
    }


    public String getName() {
        return name;
    }

    public Double getRefreshRate() {
        return refresh;
    }

    public List<String> getFrames() {
        return frames;
    }
}
