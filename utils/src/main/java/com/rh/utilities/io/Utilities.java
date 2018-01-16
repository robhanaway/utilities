package com.rh.utilities.io;

/**
 * Created by robert.hanaway on 16/01/2018.
 */

public class Utilities {
    static final int EOF = -1;
    static final int DEFAULT_BUFER_SIZE = 1024 * 32;

    private long read;
    private long written;
    private final byte[] buffer;
    private final Callback callback;

    public interface Callback {

    }

    public Utilities(final int bufferSize, final Callback callback) {
        buffer = new byte[bufferSize];
        this.callback = callback;
    }

    public Utilities(final Callback callback) {
        this(DEFAULT_BUFER_SIZE, callback);
    }

    public Utilities() {
        this(null);
    }

    public long getRead() {
        return read;
    }

    public long getWritten() {
        return written;
    }

    public void clearWritten() {
        written = 0;
    }

    public void clearRead() {
        read = 0;
    }

}
