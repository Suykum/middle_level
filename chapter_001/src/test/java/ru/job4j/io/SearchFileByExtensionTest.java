package ru.job4j.io;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchFileByExtensionTest {
    SearchFileByExtension search = new SearchFileByExtension();

    @Test
    public void whenSearch() throws IOException {
        String dir = System.getProperty("java.io.tmpdir");
        File parent = new File(String.format("%s\\parent", dir));
        File parent1 = new File(String.format("%s\\parent1", parent.getPath()));
        File parent2 = new File(String.format("%s\\parent2", parent.getPath()));
        File child = new File(String.format("%s\\child", parent2.getPath()));

        parent.mkdir();
        parent1.mkdir();
        parent2.mkdir();
        child.mkdir();

        File file = new File(String.format("%s\\file.xml", parent.getPath()));
        File file1 = new File(String.format("%s\\file1.txt", parent1.getPath()));
        File file2 = new File(String.format("%s\\file2.doc", parent2.getPath()));
        File childf = new File(String.format("%s\\childf.txt", child.getPath()));

        file.createNewFile();
        file1.createNewFile();
        file2.createNewFile();
        childf.createNewFile();

        List<String> ext = new ArrayList<>();
        ext.add("xml");
        ext.add("txt");
        List<File> list = search.files(parent, ext);
        assertThat(list, is(new ArrayList<>(Arrays.asList(file, file1, childf))));
    }

}