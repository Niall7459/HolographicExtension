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
package net.kitesoftware.holograms.placeholder;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;

import java.util.List;

public class PlaceholderAnimationReplacer implements PlaceholderReplacer {

    private final List<String> frames;
    private int currentIndex = 0;

    public PlaceholderAnimationReplacer(List<String> frames) {
        this.frames = frames;
    }

    @Override
    public String update() {
        String currentFrame = frames.get(currentIndex);

        if (currentIndex == frames.size() - 1) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }

        return currentFrame;
    }
}
