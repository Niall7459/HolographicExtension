/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.iface;

import java.util.List;
import java.util.Map;

public interface ConfigurableAnimation extends Animation {

    Map<String, String> getOptions();
    List<String> create(String text, Map<String, String> options);

}
