/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.iface;

import java.util.List;
import java.util.Map;

public interface ConfigurableAnimation extends Animation {

    Map<String, String> getOptions();
    List<String> create(String text, Map<String, String> options);

    //Not used on configurable animations.
    default List<String> create(String text) {
        return create(text, null);
    }

}
