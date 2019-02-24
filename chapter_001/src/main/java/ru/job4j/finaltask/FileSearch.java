package ru.job4j.finaltask;

import com.google.devtools.common.options.OptionsParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import static java.nio.file.FileVisitResult.CONTINUE;

public class FileSearch {

    public static class FileVisitor extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private String logFile;
        private int numMatches = 0;

        public FileVisitor(String pattern, String logFile) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
            this.logFile = logFile;
        }

        // Сравнивает шаблон поиска с именем файлом
        private void find(Path file) throws IOException {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                System.out.println(file);
                writeLog(file);
            }
        }

        public int getNumMatches() {
            return numMatches;
        }

        //включает в себя метод, которое следует выполнить во время посещения текущего файла
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            find(file);
            return CONTINUE;
        }

        @Override
        //Данный метод может пригодиться при ошибке доступа к файлу (при отказе доступа продолжать обход)
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }

        private void writeLog(Path path) throws IOException {
            File searchLog = new File(logFile);
            BufferedWriter writer = new BufferedWriter(new FileWriter(searchLog, true));
            writer.write(path.toString() + System.lineSeparator());
            writer.flush();
            writer.close();
        }

    }

    public static void main(String[] args) throws IOException {
        OptionsParser parser = OptionsParser.newOptionsParser(SearchOptions.class);
        parser.parseAndExitUponError(args);
        SearchOptions options = parser.getOptions(SearchOptions.class);

        if (options.dir.isEmpty() || options.name.isEmpty() || options.output.isEmpty()) {
            printUsage(parser);
            return;
        }

        Path startDir = Paths.get(options.dir);
        String pattern = options.name;
        String logFile = options.output;
        FileVisitor visitor = new FileVisitor(pattern, logFile);
        Files.walkFileTree(startDir, visitor);
        System.out.println("Matches: " + visitor.getNumMatches());
    }
    private static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar find.jar -d StartDirectoryPath -n fileName(or pattern) -o LogFilePath");
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(), OptionsParser.HelpVerbosity.LONG));
    }
}
