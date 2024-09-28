package ExtremumAlgorithms;

public class Main {
    public static double testFunction(final double x) {
        return x*(x-1);
    }
    public static void main(String[] args) {
        System.out.println(Bisection.getMinimum(Main::testFunction, -50, 50, 1e-6, 1000));
        System.out.println(GoldenRatio.getMinimum(Main::testFunction, -50, 50, 1e-6, 1000));
    }
}