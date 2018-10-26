package net.kitesoftware.holograms.animation;

import java.util.List;
import java.util.Map;

public abstract class BaseAnimation {

    private String name;
    private String description;
    private Map<String, String> options;

    public BaseAnimation(String name, String description, Map<String, String> options) {
        this.name = name;
        this.description = description;
        this.options = options;
    }

    public abstract List<String> setAnimations(String text, Map<String, String> options);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getOptions() {
        return options;
    }
}
