package ru.job4j.socket.fmanager;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class ClientInput {
    private Socket socket;

    public ClientInput(Socket socket) {
        this.socket = socket;
    }

    public String getMenu() {
        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Menu").append(ls);
        sb.append("root:                     List root directory").append(ls);
        sb.append("dir:                      List current directory").append(ls);
        sb.append("cd directory:             Enter directory or ..").append(ls);
        sb.append("copy srcPath desPath:     Copy file to ather place").append(ls);
        sb.append("download srcPath desPath: Download file").append(ls);
        sb.append("upload srcPath desPath:   Upload a file").append(ls);
        sb.append("exit:                     Close program").append(ls);
        return sb.toString();
    }

    public StringBuilder getServerAnswer(BufferedReader in) throws IOException {
        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        String answer = in.readLine();
        String[] str = answer.split(",");
        for (String s : str) {
            sb.append(s).append(ls);
        }
        return sb;
    }

    public void download(String dest, BufferedReader in) throws IOException {
       FileWriter fw = new FileWriter(dest);
        BufferedWriter bw = new BufferedWriter(fw);
        char[] chars = new char[1024];
        in.read(chars, 0, chars.length);
        bw.write(chars, 0, chars.length);
        bw.flush();
        bw.close();
    }

    public void upload(String fileName, PrintWriter out) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            char[] chars = new char[(int) file.length()];
            br.read(chars, 0, chars.length);
            out.write(chars, 0, chars.length);
            out.flush();
        }

    }

    public void run() throws IOException {
        String userAsk = null;
        String[] commands;
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            do {

                System.out.println(getMenu());
                userAsk = console.nextLine();

                if (userAsk.contains("download")) {
                    commands = userAsk.split(" ");
                    out.println(userAsk);
                    download(commands[2], in);
                } else if (userAsk.contains("upload")) {
                    commands = userAsk.split(" ");
                    out.println(userAsk);
                    upload(commands[1], out);
                } else {
                    out.println(userAsk);
                    System.out.println(getServerAnswer(in));
                }
            } while (!userAsk.equals("exit"));
        }
    }

    public static void main(String[] args) throws IOException {
        int port;
        String host;
        try (InputStream in = ClientInput.class.getClassLoader().getResourceAsStream("manager.properties")) {
            Properties config = new Properties();
            config.load(in);
            port = Integer.parseInt(config.getProperty("port"));
            host = config.getProperty("host");
        }
        Socket socket = new Socket(host, port);
        ClientInput client = new ClientInput(socket);
        client.run();
    }
}
