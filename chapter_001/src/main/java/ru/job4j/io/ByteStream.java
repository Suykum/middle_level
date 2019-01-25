package ru.job4j.io;
import java.io.IOException;
import java.io.InputStream;

public class ByteStream {
    public boolean isNumber(InputStream inputStream) {
        boolean result = false;
        int number;
        try (InputStream in = inputStream) {
            number = in.read();
            if (number != 0 && number % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
