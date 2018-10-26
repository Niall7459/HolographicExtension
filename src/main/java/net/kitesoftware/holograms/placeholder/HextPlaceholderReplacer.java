package net.kitesoftware.holograms.placeholder;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;

import java.util.List;

public class HextPlaceholderReplacer implements PlaceholderReplacer {

    private int currentIndex = 0;
    private List<String> frames;

    public HextPlaceholderReplacer(List<String> frames) {
        this.frames = frames;
    }

    @Override
    public String update() {
        String currentFrame = frames.get(currentIndex);

        if (currentIndex == frames.size() - 1) {
            currentIndex = 0;
        }else{
            currentIndex++;
        }

        return currentFrame;
    }
}
