package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ConsoleChat {

    private boolean stopBot = false;
    private boolean quit = false;


    public void chat(BufferedWriter writer, BufferedReader consoleInput) throws IOException {
        List<String> phrases = loadPhrases();
        String userString;
        String botString;
        do {
            System.out.print("User: ");
            userString = consoleInput.readLine();
            writeLog(writer, userString, "User");
            if (userString.equals("stop") || userString.equals("continue") || userString.equals("quit")) {
                controlDecision(userString);
            }

            if (!stopBot) {
                botString = phrases.get((int) (Math.random() * phrases.size()));
                System.out.println("Bot: " + botString);
                writeLog(writer, botString, "Bot");
            }
        } while (!quit);
    }

    private void controlDecision(String userDecision) {
        switch (userDecision) {
            case "stop":
                stopBot = true;
                break;
            case "continue":
                stopBot = false;
                break;
            default:
                quit = true;
                stopBot = true;
        }
    }

    private void writeLog(Writer writer, String chatConversation, String owner) throws IOException {
        writer.write(new GregorianCalendar().getTime() + " " + owner + ": " + chatConversation + System.lineSeparator());
        writer.flush();
    }

    private List<String> loadPhrases() throws IOException {
        List<String> phrases = new ArrayList<>();
        try (InputStream in = ConsoleChat.class.getClassLoader().getResourceAsStream("phrases.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        }
        return phrases;
    }

    public static void main(String[] args) throws IOException {
        File chatLog = new File("log.txt");
        ConsoleChat consoleChat = new ConsoleChat();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chatLog, true));
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {
            consoleChat.chat(writer, consoleInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
