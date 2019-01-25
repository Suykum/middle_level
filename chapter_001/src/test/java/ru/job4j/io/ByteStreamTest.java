package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ByteStreamTest {
    ByteStream byteStream = new ByteStream();

    @Test
    public void whenIsNumberTrue() {
    boolean result = byteStream.isNumber(new InputStream() {
        @Override
        public int read() throws IOException {
            return 10;
        }
    });
    assertThat(result, is(true));
    }

    @Test
    public void whenIsNumberFalse() {
        boolean result = byteStream.isNumber(new InputStream() {
            @Override
            public int read() throws IOException {
                return 3;
            }
        });
        assertThat(result, is(false));
    }

}