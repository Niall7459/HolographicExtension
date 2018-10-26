package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scroller extends BaseAnimation {

    public Scroller() {
        super("scroll", "Create scrolling text", new HashMap<String, String>() {{
            put("width", "32");
            put("space", "16");
        }});
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();
        options = Utils.mergeMap(getOptions(), options);

        int width = Integer.parseInt(options.get("width"));
        int space = Integer.parseInt(options.get("space"));

        int pos = 0;
        List<String> temp = new ArrayList<>();

        if (text.length() < width) {
            StringBuilder spaceBuilder = new StringBuilder();

            while(spaceBuilder.length() < width) {
                spaceBuilder.append(" ");
            }

            text = text + spaceBuilder.toString();
        }

        width -= 2;

        for (int i = 0; i < text.length() - width; i++) {
            temp.add(text.substring(i, i + width));
        }

        StringBuilder spacing = new StringBuilder();
        for (int i = 0; i < space; ++i) {
            temp.add(text.substring(text.length() - width + (i > width ? width : i), text.length()) + spacing);
            if(spacing.length() < width) {
                spacing.append(" ");
            }
        }

        for (int i = 0; i < width - space; ++i)
            temp.add(text.substring(text.length() - width + space + i, text.length()) + spacing + text.substring(0, i));

        for (int i = 0; i < space; i++) {
            if (i > spacing.length()) break;

            temp.add(spacing.substring(0, spacing.length() - i) + text.substring(0, width - (space > width ? width : space) + i));
        }

        ChatColor stored = ChatColor.RESET;
        for (int i = 0; i < temp.size(); i++) {
            StringBuilder builder =  new StringBuilder(temp.get(pos++ % temp.size()));

            if(builder.charAt(builder.length() - 1) == '§') {
                builder.setCharAt(builder.length() - 1, ' ');
            }

            if(builder.charAt(0) == '§') {
                ChatColor color = ChatColor.getByChar(builder.charAt(1));
                if (color != null) {
                    stored = color;
                    builder = new StringBuilder(temp.get(pos++ % temp.size()));
                    if (builder.charAt(0) != ' ') {
                        builder.deleteCharAt(0);
                    }
                }
            }

            frames.add(stored + builder.substring(1));

        }

        return frames;
    }
}
