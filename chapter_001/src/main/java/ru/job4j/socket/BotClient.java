package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BotClient {
    private Socket socket;

    public BotClient(Socket socket) {
        this.socket = socket;
    }

    public void run() throws IOException {
        String userAsk = null;
        String botAnswer = null;
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            do {
                userAsk = console.nextLine();
                out.println(userAsk);
                botAnswer = in.readLine();
                System.out.println(botAnswer);
            } while (!userAsk.equals("exit"));
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 4242);
        BotClient client = new BotClient(socket);
        client.run();
    }
}
