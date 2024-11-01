import mathUtils.functionalInterfaces.IFunctionND;
import mathUtils.NumericCommon;
import mathUtils.DoubleVector;
import mathUtils.DoubleMatrix;
import java.util.Objects;
public class ThirdLaboratoryWork {
    public static DoubleVector gradientDescend(IFunctionND function, DoubleVector xStart, double eps, int maxIterations) {
        DoubleVector x_i = new DoubleVector(xStart);
        DoubleVector x_i_1 = new DoubleVector(xStart);
        int cntr = 0;
        for (; cntr != maxIterations; cntr++) {
            x_i_1 = DoubleVector.sub(x_i, DoubleVector.gradient(function, x_i, eps));
            x_i_1 = SecondLaboratoryWork.biSect(function, x_i, x_i_1, eps, maxIterations);
            if (DoubleVector.distance(x_i_1, x_i) < 2 * eps) break;
            x_i = x_i_1;
        }

        if (NumericCommon.SHOW_DEBUG_LOG) System.out.printf("gradient descend iterations number : %s\n", cntr + 1);

        return DoubleVector.add(x_i_1, x_i).mul(0.5);
    }
    public static DoubleVector conjGradientDescend(IFunctionND function, DoubleVector xStart, double eps, int maxIterations) {
        DoubleVector x_i = new DoubleVector(xStart);
        DoubleVector x_i_1 = new DoubleVector(xStart);
        DoubleVector s_i = DoubleVector.gradient(function, xStart, eps).mul(-1.0), s_i_1;
        double omega;
        int iteration = 0;
        for (; iteration != maxIterations; iteration++) {
            x_i_1 = DoubleVector.add(x_i, s_i);
            x_i_1 = SecondLaboratoryWork.biSect(function, x_i, x_i_1, eps, maxIterations);
            if (DoubleVector.distance(x_i_1, x_i) < 2 * eps) break;
            s_i_1 = DoubleVector.gradient(function, x_i_1, eps);
            omega = Math.pow((s_i_1).magnitude(), 2) / Math.pow((s_i).magnitude(), 2);
            s_i.mul(omega).sub(s_i_1);
            x_i = x_i_1;
        }

        if (NumericCommon.SHOW_DEBUG_LOG)
            System.out.printf("Conj gradient descend iterations number : %s\n", iteration + 1);
        return DoubleVector.add(x_i_1, x_i).mul(0.5);
    }
}
