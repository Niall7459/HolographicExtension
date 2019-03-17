/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.iface;

import java.util.List;

public interface Animation {
    /**
     * Get the name of this animation.
     * @return String name
     */
    String getName();

    /**
     * Create animation
     * @param text Text to create animation from.
     * @return Created animation frames.
     */
    List<String> create(String text);
}
