package com.rh.utilities.logging;

/**
 * Created by robert.hanaway on 16/01/2018.
 */
public interface Logging {
    /**
     * V.
     *
     * @param tag the tag
     * @param msg the msg
     */
    void v(String tag, String msg);

    /**
     * V.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    void v(String tag, String msg, Throwable tr);

    /**
     * D.
     *
     * @param tag the tag
     * @param msg the msg
     */
    void d(String tag, String msg);

    /**
     * D.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    void d(String tag, String msg, Throwable tr);

    /**
     * .
     *
     * @param tag the tag
     * @param msg the msg
     */
    void i(String tag, String msg);

    /**
     * .
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    void i(String tag, String msg, Throwable tr);

    /**
     * W.
     *
     * @param tag the tag
     * @param msg the msg
     */
    void w(String tag, String msg);

    /**
     * W.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    void w(String tag, String msg, Throwable tr);

    /**
     * W.
     *
     * @param tag the tag
     * @param tr  the tr
     */
    void w(String tag, Throwable tr);

    /**
     * E.
     *
     * @param tag the tag
     * @param msg the msg
     */
    void e(String tag, String msg);

    /**
     * E.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    void e(String tag, String msg, Throwable tr);

    /**
     * Wtf.
     *
     * @param tag the tag
     * @param msg the msg
     */
    void wtf(String tag, String msg);

    /**
     * Wtf.
     *
     * @param tag the tag
     * @param tr  the tr
     */
    void wtf(String tag, Throwable tr);

    /**
     * Wtf.
     *
     * @param tag the tag
     * @param msg the msg
     * @param tr  the tr
     */
    void wtf(String tag, String msg, Throwable tr);

    /**
     * Var.
     *
     * @param tag     the tag
     * @param message the message
     * @param data    the data
     */
    void var(String tag, String message, Object... data);

    /**
     * Enable.
     */
    void enable();

    /**
     * Disaable.
     */
    void disable();

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    boolean isEnabled();
}
