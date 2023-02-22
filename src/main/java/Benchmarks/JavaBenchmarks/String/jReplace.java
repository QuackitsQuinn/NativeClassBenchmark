package Benchmarks.JavaBenchmarks.String;

import Benchmarks.Tools.AbstractBenchmark;

public class jReplace extends AbstractBenchmark {
    private String string;
    private String replace;
    private String with;
    @Override
    protected void setup() {
        string = "Hello World";
        replace = "World";
        with = "Universe";
    }

    @Override
    protected void execute() {
        string.replace(replace,with);
    }
}

