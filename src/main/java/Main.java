import LAB1.Bisection;
import LAB1.Fibonacci;
import LAB1.GoldenRatio;
import mathUtils.DoubleVector;
import mathUtils.NumericCommon;
import mathUtils.NumericUtils;
import mathUtils.PenaltyFunction;

import java.util.ArrayList;

public class Main {
    public static double testFunction(DoubleVector doubles) {
        return -doubles.get(0) * doubles.get(0) * doubles.get(0) + doubles.get(1)*doubles.get(1) + 3 * doubles.get(0) * doubles.get(1);
    }
    public static void main(String[] args) {
        System.out.println(ThirdLaboratoryWork.gradientDescend(NumericUtils.testFunc2d, new DoubleVector(2.0, 2.0),  1.5e-10, 1000));
        System.out.println(ThirdLaboratoryWork.conjGradientDescend(NumericUtils.testFunc2d, new DoubleVector(2.0, 2.0), 1.5e-10, 1000));
        System.out.println(ThirdLaboratoryWork.newtoneRaphson(NumericUtils.testFunc2d, new DoubleVector(2.0, 2.0), 1.5e-10, 1000));
        PenaltyFunction penaltyFunction = new PenaltyFunction();
        penaltyFunction.target(NumericUtils.testFunc2d);
        System.out.println(SecondLaboratoryWork.perCordDescend(penaltyFunction, new DoubleVector(2.0, 2.0), 1.5e-10, 1000));
    }
}