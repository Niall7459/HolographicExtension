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

import net.kitesoftware.holograms.animation.impl.*;

public enum DefaultAnimation {
    ALIGN_LEFT(Align.LEFT),
    ALIGN_RIGHT(Align.RIGHT),
    FADE_IN(Fade.IN),
    FADE_OUT(Fade.OUT),
    BLINK(new Blink()),
    ERASE(new Erase()),
    GLOW(new Glow()),
    PAUSE(new Pause()),
    PULSE(new Pulse()),
    RAINBOW(new Rainbow()),
    SCROLLER(new Scroller()),
    TYPEWRITER(new Typewriter()),
    WAVE(new Wave());

    private final Animation animation;

    DefaultAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

}
