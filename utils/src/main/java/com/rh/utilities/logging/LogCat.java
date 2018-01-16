package com.rh.utilities.logging;

import android.util.Log;

/**
 * Created by robert.hanaway on 16/01/2018.
 */

public class LogCat implements Logging{
    boolean enabled = true;

    /**
     * V.
     *
     * @param tag the tag
     * @param msg the msg
     */
    @Override
    public void v(String tag, String msg) {
        if (enabled) {
            Log.v(tag, msg);
        }
    }

    /**
     * V.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    @Override
    public void v(String tag, String msg, Throwable tr) {
        if (enabled) {
            Log.v(tag, msg, tr);
        }
    }

    /**
     * D.
     *
     * @param tag the tag
     * @param msg the msg
     */
    @Override
    public void d(String tag, String msg) {
        if (enabled) {
            Log.d(tag, msg);
        }
    }

    /**
     * D.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    @Override
    public void d(String tag, String msg, Throwable tr) {
        if (enabled) {
            Log.d(tag, msg, tr);
        }
    }

    /**
     * .
     *
     * @param tag the tag
     * @param msg the msg
     */
    @Override
    public void i(String tag, String msg) {
        if (enabled) {
            Log.i(tag, msg);
        }
    }

    /**
     * .
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    @Override
    public void i(String tag, String msg, Throwable tr) {
        if (enabled) {
            Log.i(tag, msg, tr);
        }
    }

    /**
     * W.
     *
     * @param tag the tag
     * @param msg the msg
     */
    @Override
    public void w(String tag, String msg) {
        if (enabled) {
            Log.w(tag, msg);
        }
    }

    /**
     * W.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    @Override
    public void w(String tag, String msg, Throwable tr) {
        if (enabled) {
            Log.w(tag, msg, tr);
        }
    }

    /**
     * W.
     *
     * @param tag the tag
     * @param tr  the tr
     */
    @Override
    public void w(String tag, Throwable tr) {
        if (enabled) {
            Log.w(tag, tr);
        }
    }

    /**
     * E.
     *
     * @param tag the tag
     * @param msg the msg
     */
    @Override
    public void e(String tag, String msg) {
        if (enabled) {
            Log.e(tag, msg);
        }
    }

    /**
     * E.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    @Override
    public void e(String tag, String msg, Throwable tr) {
        if (enabled) {
            Log.e(tag, msg, tr);
        }
    }

    /**
     * Wtf.
     *
     * @param tag the tag
     * @param msg the msg
     */
    @Override
    public void wtf(String tag, String msg) {
        if (enabled) {
            Log.wtf(tag, msg);
        }
    }

    /**
     * Wtf.
     *
     * @param tag the tag
     * @param tr  the tr
     */
    @Override
    public void wtf(String tag, Throwable tr) {
        if (enabled) {
            Log.wtf(tag, tr);
        }
    }

    /**
     * Wtf.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    @Override
    public void wtf(String tag, String msg, Throwable tr) {
        if (enabled) {
            Log.wtf(tag, msg, tr);
        }
    }

    /**
     * Var.
     *
     * @param tag     the tag
     * @param message the message
     * @param data    the data
     */
    @Override
    public void var(String tag, String message, Object... data) {
        StringBuilder result = new StringBuilder(message);
        for (Object object: data) {
            result.append(" " + (object != null ? object.toString() : "(null)"));
        }
        v(tag, result.toString());
    }

    /**
     * Enable.
     */
    @Override
    public void enable() {
        enabled = true;
    }

    /**
     * Disaable.
     */
    @Override
    public void disable() {
        enabled = false;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
