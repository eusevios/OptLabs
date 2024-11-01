import mathUtils.FibonacciNumbers;
import mathUtils.functionalInterfaces.IFunctionND;
import mathUtils.NumericCommon;
import mathUtils.DoubleVector;
public class SecondLaboratoryWork {
    public static DoubleVector biSect(IFunctionND function, DoubleVector left, DoubleVector right, double eps, int maxIterations) {
        DoubleVector lhs = new DoubleVector(left);
        DoubleVector rhs = new DoubleVector(right);
        DoubleVector x_c;
        DoubleVector dir = DoubleVector.direction(lhs, rhs).mul(eps);
        int iter = 0;
        for (; iter != maxIterations && DoubleVector.distance(rhs, lhs) > 2 * eps; iter++) {
            x_c = DoubleVector.add(rhs, lhs).mul(0.5);
            if (function.call(DoubleVector.add(x_c, dir)) > function.call(DoubleVector.sub(x_c, dir)))
                rhs = x_c;
            else
                lhs = x_c;
        }

        return DoubleVector.add(rhs, lhs).mul(0.5);
    }

    public static DoubleVector goldenRatio(IFunctionND function, DoubleVector left, DoubleVector right, double eps, int maxIterations) {
        DoubleVector lhs = new DoubleVector(left);
        DoubleVector rhs = new DoubleVector(right);
        DoubleVector x_l = DoubleVector.sub(rhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), NumericCommon.PSI));
        DoubleVector x_r = DoubleVector.add(lhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), NumericCommon.PSI));
        double f_l = function.call(x_l);
        double f_r = function.call(x_r);
        int iter = 0;
        for (; iter != maxIterations && DoubleVector.distance(rhs, lhs) > 2 * eps; iter++) {
            if (f_l > f_r) {
                lhs = x_l;
                x_l = x_r;
                f_l = f_r;
                x_r = DoubleVector.add(lhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), NumericCommon.PSI));
                f_r = function.call(x_r);
            } else {
                rhs = x_r;
                x_r = x_l;
                f_r = f_l;
                x_l = DoubleVector.sub(rhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), NumericCommon.PSI));
                f_l = function.call(x_l);
            }
        }
        if (NumericCommon.SHOW_ZERO_ORDER_METHODS_DEBUG_LOG) {
            System.out.printf("goldenRatio::function arg range    : %s\n", DoubleVector.distance(rhs, lhs));
            System.out.printf("goldenRatio::function probes count : %s\n", 2 + iter);
        }

        return DoubleVector.add(rhs, lhs).mul(0.5);
    }
    public static DoubleVector fibonacci(IFunctionND function, DoubleVector left, DoubleVector right, double eps) {

        DoubleVector lhs = new DoubleVector(left);
        DoubleVector rhs = new DoubleVector(right);

        FibonacciNumbers fibonacciNumbers = new FibonacciNumbers(DoubleVector.distance(rhs, lhs) / eps);
        DoubleVector xl = DoubleVector.add(lhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), (fibonacciNumbers.getFn1() - fibonacciNumbers.getFn()) / fibonacciNumbers.getFn1()));
        DoubleVector xr = DoubleVector.add(lhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), fibonacciNumbers.getFn() / fibonacciNumbers.getFn1()));

        double fl = function.call(xl);
        double fr = function.call(xr);

        int iter = fibonacciNumbers.getIter();

        for (int index = iter; index > 0; index--) {
            fibonacciNumbers.updatePair();
            if (fl > fr) {
                lhs = xl;
                xl = xr;
                fl = fr;
                xr = DoubleVector.add(lhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), fibonacciNumbers.getFn() / fibonacciNumbers.getFn1()));
                fr = function.call(xr);
            } else {
                rhs = xr;
                xr = xl;
                fr = fl;
                xl = DoubleVector.add(lhs, DoubleVector.mul(DoubleVector.sub(rhs, lhs), (fibonacciNumbers.getFn1() - fibonacciNumbers.getFn()) / fibonacciNumbers.getFn1()));
                fl = function.call(xl);
            }
        }

        if (NumericCommon.SHOW_ZERO_ORDER_METHODS_DEBUG_LOG) {
            System.out.printf("fibonacci::function arg range    : %s\n", DoubleVector.distance(rhs, lhs));
            System.out.printf("fibonacci::function probes count : %s\n", 2 + iter);
        }

        return DoubleVector.add(rhs, lhs).mul(0.5);
    }

    public static DoubleVector perCordDescend(IFunctionND function, DoubleVector xStart, double eps, int maxIterations) {
        DoubleVector x_0 = new DoubleVector(xStart);
        DoubleVector x_1 = new DoubleVector(xStart);
        DoubleVector orth = new DoubleVector(2, 0);
        double step = 1.0;
        int i = 0;
        int currentOrthCoordinate;
        do{
            currentOrthCoordinate = i%x_0.size();
            x_0 = x_1;
            orth = DoubleVector.sub(orth, orth);
            orth.set(currentOrthCoordinate, 1.0);
            DoubleVector xl = DoubleVector.sub(x_0, DoubleVector.mul(eps, orth));
            DoubleVector xr = DoubleVector.add(x_0, DoubleVector.mul(eps, orth));
            if(function.call(xl) > function.call(xr)){
                step = Math.abs(step);
            }
            else {
                step = -Math.abs(step);
            }
            x_1 = biSect(function, x_0, DoubleVector.add(x_0, DoubleVector.mul(step, orth)), eps, maxIterations);
            i++;
        }
        while (DoubleVector.distance(x_0, x_1) > 2 * eps && i < maxIterations);
        return x_1;
    }


}
