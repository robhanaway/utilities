package com.rh.utilities.io;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by robert.hanaway on 16/01/2018.
 */

public class Utilities {
    static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 32;

    private long read;
    private long written;

    private final Callback callback;
    private final int bufferSize;
    public interface Callback {
        int getEmitThreshold();
        void emit(long current, long read);
        void error(Exception exception);
        void complete(long totalRead);
    }

    public Utilities(final @NonNull Utilities utilities) {
        this(utilities.bufferSize, utilities.callback);
        read = utilities.getRead();
        written = utilities.getWritten();
    }

    public Utilities(final int bufferSize, final Callback callback) {
        this.bufferSize = bufferSize;
        this.callback = callback;
    }

    public Utilities(final Callback callback) {
        this(DEFAULT_BUFFER_SIZE, callback);
    }

    public Utilities() {
        callback = null;
        bufferSize = DEFAULT_BUFFER_SIZE;
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

    public static void closeQuitely(final InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            //Suppressed
        }
    }

    public static void closeQuitely(final OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            //Suppressed
        }
    }

    public void copy(@NonNull final InputStream inputStream, @NonNull final OutputStream outputStream) {
        copy(inputStream, outputStream, callback);
    }

    public void copy(@NonNull final InputStream inputStream, @NonNull final OutputStream outputStream, final Callback callback) {
        int emitThreshold = callback != null ? callback.getEmitThreshold() : 0;
        byte[] buffer = new byte[bufferSize];
        long totalRead = 0;
        long lastEmit = 0;
        int readFromBuffer;
        try {
            while (EOF != (readFromBuffer = inputStream.read(buffer))) {
                read += readFromBuffer;
                outputStream.write(buffer, 0, readFromBuffer);
                written += readFromBuffer;
                totalRead += readFromBuffer;
                if ((callback != null) && ((totalRead - lastEmit) >= emitThreshold)) {
                    callback.emit(totalRead, readFromBuffer);
                }
            }
            if (callback != null) {
                callback.complete(totalRead);
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.error(e);
            }
        }
    }
}
