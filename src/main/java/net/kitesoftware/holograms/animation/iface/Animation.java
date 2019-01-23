/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.iface;

import java.util.List;

public interface Animation {
    String getName();
    List<String> create(String text);
}
