package ru.job4j.finaltask;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class SearchOptions extends OptionsBase {
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
            help = "Start directory",
            defaultValue = ""
    )
    public String dir;

    @Option(
            name = "name",
            abbrev = 'n',
            help = "File name or search pattern",
            defaultValue = ""
    )
    public String name;

    @Option(
            name = "output",
            abbrev = 'o',
            help = "Search result output log file",
            defaultValue = ""
    )
    public String output;
}
