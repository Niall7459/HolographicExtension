/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.subanimation;

import net.kitesoftware.holograms.animation.iface.Animation;

import java.util.Arrays;
import java.util.List;

public enum Fade implements Animation {

    IN {
        @Override
        public String getName() {
            return "fadein";
        }

        @Override
        public List<String> create(String text) {
            return Arrays.asList(
                    "§0" + text,
                    "§8" + text,
                    "§7" + text,
                    "§f" + text
            );
        }
    },

    OUT {
        @Override
        public String getName() {
            return "fadeout";
        }

        @Override
        public List<String> create(String text) {
            return Arrays.asList(
                    "§f" + text,
                    "§7" + text,
                    "§8" + text,
                    "§0" + text
            );
        }
    }
}
