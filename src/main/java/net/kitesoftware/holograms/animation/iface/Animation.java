/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.iface;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Animation {

    String getName();

    default List<String> create(String text) {
        return Collections.emptyList();
    };

    default Matcher getMatcher(String text) {
        String regex = "(.*?)(<" + getName() + "(.*?)>)(.*?)(</" + getName() + "+>)(.*?)";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text);
    }
}
