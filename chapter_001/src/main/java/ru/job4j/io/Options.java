package ru.job4j.io;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class Options extends OptionsBase {
    @Option(
            name = "help",
            abbrev = 'h',
            help = "Print usage info.",
            defaultValue = "true"
    )
    public boolean help;

    @Option(
            name = "dir",
            abbrev = 'd',
            help = "Source directory",
            defaultValue = ""
    )
    public String dir;

    @Option(
            name = "ext",
            abbrev = 'e',
            help = "File extensions",
            defaultValue = ""
    )
    public String ext;

    @Option(
            name = "output",
            abbrev = 'o',
            help = "Output zip name.",
            defaultValue = ""
    )
    public String output;
}
