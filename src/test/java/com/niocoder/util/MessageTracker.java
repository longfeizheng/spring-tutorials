package com.niocoder.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public abstract class MessageTracker {

    private static List<String> MESSAGES = new ArrayList<>();

    public static void addMsg(String msg) {
        MESSAGES.add(msg);
    }

    public static void clearMsgs() {
        MESSAGES.clear();
    }

    public static List<String> getMsgs() {
        return MESSAGES;
    }
}
