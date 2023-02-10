package Benchmarks.Tools;

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
}
