/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.iface;

import java.util.List;

public interface Animation {
    String getName();
    List<String> create(String text);
}
