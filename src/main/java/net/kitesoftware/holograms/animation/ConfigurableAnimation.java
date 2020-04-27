/*
 *  Holographic Extension
 *  Copyright (C) 2015 - 2019 Niall7459
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.kitesoftware.holograms.animation;

import java.util.List;
import java.util.Map;

public interface ConfigurableAnimation extends Animation {
    /**
     * Get the available options for this animation.
     *
     * @return Map of option in format key - default value.
     */
    Map<String, String> getOptions();

    /**
     * Create animation
     *
     * @param text    Text to create animation from
     * @param options Provided options to override defaults.
     * @return Created animation frames from settings.
     */
    List<String> create(String text, Map<String, String> options);

    /**
     * Create animation from default settings.
     *
     * @param text Text to create animation from.
     * @return Created animation frames.
     */
    default List<String> create(String text) {
        return create(text, null);
    }
}
