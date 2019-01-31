package ru.job4j.io;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ConsoleChatTest {
    String ln = System.lineSeparator();

    @Test
    public void whenUserSayHi() throws IOException {
        CharArrayReader reader = new CharArrayReader((
                Joiner.on(ln).join(
                        "hi there",
                        "quit"
                )).toCharArray());
        CharArrayWriter writer = new CharArrayWriter();
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.chat(new BufferedWriter(writer), new BufferedReader(reader));
        assertTrue(writer.toString().contains("User: hi there"));
    }

    @Test
    public void whenUserSayStop() throws IOException {
        CharArrayReader reader = new CharArrayReader((
                Joiner.on(ln).join(
                        "stop",
                        "something",
                        "quit"
                )).toCharArray());
        CharArrayWriter writer = new CharArrayWriter();
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.chat(new BufferedWriter(writer), new BufferedReader(reader));
        List<String> logList = Arrays.asList(writer.toString().split(ln));
        assertTrue(logList.get(0).contains("User: stop")
                && logList.get(1).contains("User: something")
                && logList.get(2).contains("User: quit"));
    }

    @Test
    public void whenUserSayContinue() throws IOException {
        CharArrayReader reader = new CharArrayReader((
                Joiner.on(ln).join(
                        "hi",
                        "stop",
                        "something",
                        "continue",
                        "quit"
                )).toCharArray());
        CharArrayWriter writer = new CharArrayWriter();
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.chat(new BufferedWriter(writer), new BufferedReader(reader));
        List<String> logList = Arrays.asList(writer.toString().split(ln));
        System.out.println(writer.toString());
        assertTrue(logList.get(4).contains("User: continue")
                && logList.get(5).contains("Bot: ")
                && logList.get(6).contains("User: quit"));

    }

}