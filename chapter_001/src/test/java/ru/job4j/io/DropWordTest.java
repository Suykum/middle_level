package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DropWordTest {
    @Test
    public void whenAbusedWordsDropped() {
        DropWord dropWord = new DropWord();
        String[] abuses = {"stop", "start"};
        String inputString = "something stop then start bla bla bla";
        ByteArrayInputStream inputArray = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream outputArray = new ByteArrayOutputStream();
        try (InputStreamReader input = new InputStreamReader(inputArray);
             OutputStreamWriter output = new OutputStreamWriter(outputArray)) {
            dropWord.dropAbuses(input, output, abuses);

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(outputArray.toString(), is("something then bla bla bla "));
    }
}