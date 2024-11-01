import LAB1.Bisection;
import LAB1.Fibonacci;
import LAB1.GoldenRatio;
import mathUtils.DoubleVector;
import mathUtils.NumericCommon;

import java.util.ArrayList;

public class Main {
    public static double testFunction(DoubleVector doubles) {
        return -doubles.get(0) * doubles.get(0) * doubles.get(0) + doubles.get(1)*doubles.get(1) + 3 * doubles.get(0) * doubles.get(1);
    }
    public static void main(String[] args) {
        System.out.println(SecondLaboratoryWork.biSect(Main::testFunction, new DoubleVector(2.0, 2.0), new DoubleVector(4.0, -4.0), 1e-6, 1000));
        System.out.println(SecondLaboratoryWork.goldenRatio(Main::testFunction, new DoubleVector(2.0, 2.0), new DoubleVector(4.0, -4.0), 1e-6, 1000));
        System.out.println(SecondLaboratoryWork.fibonacci(Main::testFunction, new DoubleVector(2.0, 2.0), new DoubleVector(4.0, -4.0), 1.5e-6));
        System.out.println(SecondLaboratoryWork.perCordDescend(Main::testFunction, new DoubleVector(2.0, 2.0), 1.5e-10, 1000));
    }
}