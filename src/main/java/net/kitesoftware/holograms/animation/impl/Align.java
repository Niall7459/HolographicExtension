/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.impl;

import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;
import net.kitesoftware.holograms.util.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum Align implements ConfigurableAnimation {

    LEFT {
        @Override
        public String getName() {
            return "left";
        }

        @Override
        public Map<String, String> getOptions() {
            return Collections.singletonMap("width", "32");
        }

        @Override
        public List<String> create(String text, Map<String, String> options) {
            return Collections.singletonList(Utils.alignText(
                    text,
                    Integer.parseInt(options.get("width")),
                    false));
        }
    },

    RIGHT {
        @Override
        public String getName() {
            return "right";
        }

        @Override
        public Map<String, String> getOptions() {
            return Collections.singletonMap("width", "32");
        }

        @Override
        public List<String> create(String text, Map<String, String> options) {
            return Collections.singletonList(Utils.alignText(
                    text,
                    Integer.parseInt(options.get("width")),
                    true));
        }
    }
}
