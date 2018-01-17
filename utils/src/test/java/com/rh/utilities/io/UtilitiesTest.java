package com.rh.utilities.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by robert.hanaway on 17/01/2018.
 */

public class UtilitiesTest {
    static int BUFFER_SIZE = 123;
    static int EMIT_THRESHOLD = 121;
    static int INPUT_STREAM_SIZE = 1024;
    class MyCallback implements Utilities.Callback {
        private long current;
        private long read;
        private long total;
        private Exception exception;
        int emitThreshold;
        private int emitCount;

        public MyCallback(int emitThreshold) {
            this.emitThreshold = emitThreshold;
        }

        @Override
        public int getEmitThreshold() {
            return emitThreshold;
        }

        @Override
        public void emit(long c, long r) {
            emitCount++;
            current = c;
            read = r;
        }

        @Override
        public void error(Exception e) {
            exception = e;
        }

        @Override
        public void complete(long t) {
            total = t;
        }

        public long getCurrent() {
            return current;
        }

        public long getRead() {
            return read;
        }

        public long getTotal() {
            return total;
        }

        public int getEmitCount() {
            return emitCount;
        }

        public Exception getException() {
            return exception;
        }
    }

    @Test
    public void testCopyLargeNoCallback() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[INPUT_STREAM_SIZE]);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Utilities utilities = new Utilities();
        Assert.assertEquals(0, utilities.getRead());
        Assert.assertEquals(0, utilities.getWritten());

        utilities.copy(byteArrayInputStream, byteArrayOutputStream);

        Assert.assertEquals(INPUT_STREAM_SIZE, utilities.getRead());
        Assert.assertEquals(INPUT_STREAM_SIZE, utilities.getWritten());

        utilities.clearWritten();
        utilities.clearRead();

        Assert.assertEquals(0, utilities.getRead());
        Assert.assertEquals(0, utilities.getWritten());


        Utilities.closeQuitely(byteArrayInputStream);
        Utilities.closeQuitely(byteArrayOutputStream);
    }

    @Test
    public void testCopyLargeCallback() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[INPUT_STREAM_SIZE]);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        MyCallback myCallback = new MyCallback(EMIT_THRESHOLD);
        Utilities utilities = new Utilities(BUFFER_SIZE, myCallback);
        Assert.assertEquals(0, utilities.getRead());
        Assert.assertEquals(0, utilities.getWritten());

        utilities.copy(byteArrayInputStream, byteArrayOutputStream);

        Assert.assertEquals(INPUT_STREAM_SIZE, utilities.getRead());
        Assert.assertEquals(INPUT_STREAM_SIZE, utilities.getWritten());
        Assert.assertNull(myCallback.getException());
        Assert.assertEquals((INPUT_STREAM_SIZE/BUFFER_SIZE) + 1, myCallback.getEmitCount());

        Utilities utilities1 = new Utilities(utilities);
        Assert.assertEquals(INPUT_STREAM_SIZE, utilities1.getRead());
        Assert.assertEquals(INPUT_STREAM_SIZE, utilities1.getWritten());

        utilities.clearWritten();
        utilities.clearRead();

        Assert.assertEquals(0, utilities.getRead());
        Assert.assertEquals(0, utilities.getWritten());


        Utilities.closeQuitely(byteArrayInputStream);
        Utilities.closeQuitely(byteArrayOutputStream);
    }

    @Test
    public void testCopyLargeException() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[INPUT_STREAM_SIZE]);
        ByteArrayOutputStream byteArrayOutputStream = null;

        MyCallback myCallback = new MyCallback(EMIT_THRESHOLD);
        Utilities utilities = new Utilities(BUFFER_SIZE, myCallback);


        utilities.copy(byteArrayInputStream, byteArrayOutputStream);
        Assert.assertEquals(BUFFER_SIZE, utilities.getRead());
        Assert.assertEquals(0, utilities.getWritten());
        Assert.assertNotNull(myCallback.exception);
    }
}
