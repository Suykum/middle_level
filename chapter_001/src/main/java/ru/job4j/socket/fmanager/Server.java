package ru.job4j.socket.fmanager;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Server {

    private Socket socket;
    private String root;
    private File rootDir;
    private String currentDir;
    public Server(Socket socket, String root) {
        this.socket = socket;
        this.root = root;
        this.rootDir = Paths.get(root).toFile();
        this.currentDir = root;
    }

    public StringBuilder listRoot() {
        File[] files = rootDir.listFiles();
        return constructStringBuilder(files);
    }

    public StringBuilder listCurrentDirectory() {
        File current = Paths.get(currentDir).toFile();
        File[] files = current.listFiles();
        return constructStringBuilder(files);
    }


    public String changeDirectory(String path) {
        String result = null;
        StringBuilder sb = null;
        if (path.equals("..")) {
            File dir = Paths.get(currentDir).toFile();
            File parent = dir.getParentFile();
            File[] parentDir = parent.listFiles();
            sb = constructStringBuilder(parentDir);
            currentDir = parent.getPath();
            sb.append("CURRENT DIRECTORY IS: " + currentDir).append(",");
            result = sb.toString();
        } else {
            File dir = Paths.get(currentDir + "/" + path).toFile();
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                sb = constructStringBuilder(files);
                currentDir = dir.getPath();
                sb.append("CURRENT DIRECTORY IS: " + currentDir).append(",");
                result = sb.toString();
            } else if (dir.isFile()) {
               result = dir + " is not a directory";
            } else {
                result = dir + " is not found";
            }

        }
        return result;
    }

    private StringBuilder constructStringBuilder(File[] files) {
        StringBuilder sb = new StringBuilder();
        for (File f : files) {
            if (f.isDirectory()) {
                sb.append("<DIR>....").append(f.toString()).append(",");
            } else {
                sb.append("<FILE>...").append(f.toString()).append(",");
            }
        }
        return sb;
    }

    public void sendFile(String filename, PrintWriter out) throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            char[] chars = new char[(int) file.length()];
            br.read(chars, 0, chars.length);
            out.write(chars, 0, chars.length);
            out.flush();
        }
    }

    public void receiveFile(String dest, BufferedReader in) throws IOException {
        FileWriter fw = new FileWriter(dest);
        BufferedWriter bw = new BufferedWriter(fw);
        char[] chars = new char[1024];
        in.read(chars, 0, chars.length);
        bw.write(chars, 0, chars.length);
        bw.flush();
        bw.close();
    }

    public String copyFile(String source, String destination) throws IOException {
        Path path = null;
        String str = null;
        File src = Paths.get(source).toFile();
        File des = Paths.get(destination).toFile();
        if (src.exists()) {
            path = Files.copy(src.toPath(), des.toPath());
            str = "File copied to: " + path.toString();
        } else {
            str = "File not found";
        }
        return str;
    }

    public void exit(String exit) {
    }

    public void run() throws IOException {
        String ask = null;

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String[] commands = null;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                commands = ask.split(" ");
                switch (commands[0]) {
                    case ("root"):
                        out.println(listRoot());
                        break;
                    case ("dir"):
                        out.println(listCurrentDirectory());
                        break;
                    case ("cd"):
                        out.println(changeDirectory(commands[1]));
                        break;
                    case ("copy"):
                        out.println(copyFile(commands[1], commands[2]));
                        break;
                    case ("download"):
                        sendFile(commands[1], out);
                        break;
                    case ("upload"):
                        receiveFile(commands[2], in);
                        break;
                    case ("exit"):
                        out.println("Exit");
                    default:
                        out.println("Enter valid value");
                }
            } while (!"exit".equals(commands[0]));
        }
    }

    public static void main(String[] args) throws IOException {
        int port;
        String root;
        try (InputStream in = Server.class.getClassLoader().getResourceAsStream("manager.properties")) {
            Properties config = new Properties();
            config.load(in);
            port = Integer.parseInt(config.getProperty("port"));
            root = config.getProperty("rootDir");
        }

        try (Socket socket = new ServerSocket(port).accept()) {
            Server server = new Server(socket, root);
            server.run();
        }
    }
}
