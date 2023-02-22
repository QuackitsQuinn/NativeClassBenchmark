package Benchmarks.Tools;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Utils {
    private static class Dummy {
        private void dummy() {
            // DUMB METHOD
        }
    }
    public static void warmJVM() {
        System.out.println("Warming up JVM");
        for (int i = 0; i < 100000; i++) {
             Dummy d = new Dummy();
                d.dummy();
        }
    }

    public static String getLibString(String libname){
        StringBuilder sb = new StringBuilder("bin/lib/");
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            sb.append(libname).append(".dll");
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            sb.append("lib").append(libname).append(".dylib");
        } else {
            sb.append("lib").append(libname).append(".so");
        }
        System.out.println(sb.toString());
        String absolutePath = new File(sb.toString()).getAbsolutePath();
        System.out.println(absolutePath);
        return absolutePath;

    }
    public static String formatTime(long time) {
        if (time < 1000) {
            return time + "ns";
        } else if (time < 1000000000) {
            return time / 1000000 + "ms";
        } else {
            return time / 1000000000 + "s";
        }
    }
    public static String formatTime(double time) {
        if (time < 1000) {
            return time + "ns";
        } else if (time < 1000000000) {
            return time / 1000000 + "ms";
        } else {
            return time / 1000000000 + "s";
        }
    }
}
