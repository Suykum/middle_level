package ru.job4j.io;

import java.io.File;
import java.util.*;

public class SearchFileByExtension {

    private List<File> resultFileList = new ArrayList<>();

    public List<File> files(File parent, List<String> ext) {
        List<File> files = Arrays.asList(parent.listFiles());
        for (File f : files) {
            if (f.isDirectory()) {
                files(f, ext);
            } else {
                if (checkExt(f, ext)) {
                    resultFileList.add(f);
                }
            }
        }
        return resultFileList;
    }

    private boolean checkExt(File file, List<String> ext) {
        boolean result = false;
        for (String s : ext) {
            if (file.getName().endsWith(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
