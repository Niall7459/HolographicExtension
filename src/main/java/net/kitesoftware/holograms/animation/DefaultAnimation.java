/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation;

import net.kitesoftware.holograms.animation.iface.Animation;
import net.kitesoftware.holograms.animation.impl.*;

public enum DefaultAnimation {
    ALIGN_LEFT      (Align.LEFT),
    ALIGN_RIGHT     (Align.RIGHT),
    FADE_IN         (Fade.IN),
    FADE_OUT        (Fade.OUT),
    BLINK           (new Blink()),
    ERASE           (new Erase()),
    GLOW            (new Glow()),
    PAUSE           (new Pause()),
    PULSE           (new Pulse()),
    RAINBOW         (new Rainbow()),
    SCROLLER        (new Scroller()),
    TYPEWRITER      (new Typewriter()),
    WAVE            (new Wave());

    private Animation animation;

    DefaultAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

}
