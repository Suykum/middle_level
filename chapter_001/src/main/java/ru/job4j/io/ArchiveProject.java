package ru.job4j.io;

import com.google.devtools.common.options.OptionsParser;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveProject {
    private static final Logger LOGGER = Logger.getLogger(ArchiveProject.class);

    public void createZip(String srcDirName, String desDirName, List<String> ext) {
        File srcDir = Paths.get(srcDirName).toFile();
        File desDir = Paths.get(desDirName).toFile();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(desDir))) {
            if (srcDir.isDirectory()) {
                addFolderToZip(srcDir, srcDir.getName(), zipOutputStream, ext);
            } else {
                throw new IllegalArgumentException("Source is not folder");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void addFolderToZip(File folder, String parentFolder, ZipOutputStream zipOutputStream, List<String> ext) {

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                addFolderToZip(file, parentFolder + "/" + file.getName(), zipOutputStream, ext);
            } else {
                if (checkExt(file, ext)) {
                    addFileToZip(file, parentFolder, zipOutputStream);
                }
            }
        }
    }

    private void addFileToZip(File file, String parentFolder, ZipOutputStream zipOutputStream) {
        try (FileInputStream inputStream = new FileInputStream(file)) {

            ZipEntry entry = new ZipEntry(parentFolder + "/" + file.getName());
            entry.setLastModifiedTime(FileTime.fromMillis(file.lastModified()));
            zipOutputStream.putNextEntry(entry);

            byte[] readBuffer = new byte[2048];
            int amountRead;
            int written = 0;

            while ((amountRead = inputStream.read(readBuffer)) > 0) {
                zipOutputStream.write(readBuffer, 0, amountRead);
                written += amountRead;
            }
            LOGGER.info("Stored " + written + " bytes to " + file);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
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

    public static void main(String[] args) {
        OptionsParser parser = OptionsParser.newOptionsParser(Options.class);
        parser.parseAndExitUponError(args);
        Options options = parser.getOptions(Options.class);

        if (options.dir.isEmpty() || options.ext.isEmpty() || options.output.isEmpty()) {
            printUsage(parser);
            return;
        }

        ArchiveProject archiveProject = new ArchiveProject();
        String sourceDir = options.dir;
        String desDir = options.output;
        List<String> extensions = Arrays.asList(options.ext.split(","));
        archiveProject.createZip(sourceDir, desDir, extensions);
        //archiveProject.createZip("C:\\projects\\middle_level", "C:\\Users\\Toshiba\\AppData\\Local\\Temp\\output.zip", ext);

    }

    private static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar pack.jar -d SourceDirectoryPath -e ext1,ext2 -o DestinationZipFilePath");
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(), OptionsParser.HelpVerbosity.LONG));
    }
}
