package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DropWord {

    public void dropAbuses(InputStreamReader in, OutputStreamWriter out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(in);
        BufferedWriter writer = new BufferedWriter(out)) {
            String str = reader.readLine();
            String[] strings = str.split(" ");
            List<String> abuseList = Arrays.stream(abuse).collect(Collectors.toList());
            for (String s : strings) {
                if (!abuseList.contains(s)) {
                    writer.write(s + " ");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
