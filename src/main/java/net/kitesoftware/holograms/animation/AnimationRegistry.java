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

import java.util.ArrayList;
import java.util.List;

public class AnimationRegistry {
    private final List<Animation> registeredAnimations;

    public AnimationRegistry() {
        registeredAnimations = new ArrayList<>();
        registerDefaultAnimations();
    }

    private void registerDefaultAnimations() {
        for (DefaultAnimation anim : DefaultAnimation.values()) {
            registerAnimation(anim.getAnimation());
        }
    }

    /**
     * Register a custom animation.
     *
     * @param animation Animation
     */
    public void registerAnimation(Animation animation) {
        registeredAnimations.add(animation);
    }

    public Animation getAnimation(String name) {
        for (Animation animation : registeredAnimations) {
            if (animation.getName().equalsIgnoreCase(name)) return animation;
        }

        return null;
    }
}
