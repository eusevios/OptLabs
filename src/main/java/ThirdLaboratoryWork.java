import mathUtils.TemplateVector;
import mathUtils.functionalInterfaces.IFunctionND;
import mathUtils.NumericCommon;
import mathUtils.DoubleVector;
import mathUtils.DoubleMatrix;
import java.util.Objects;
public class ThirdLaboratoryWork {
    public static DoubleVector gradientDescend(IFunctionND function, DoubleVector xStart, double eps, int maxIterations) {
        DoubleVector x0;
        DoubleVector x1 = new DoubleVector(xStart);
        int iter = 0;
        do{
            iter++;
            x0 = x1;
            x1 = DoubleVector.sub(x0, DoubleVector.gradient(function, x0, eps));
            x1 = SecondLaboratoryWork.fibonacci(function, x0, x1, eps);
        }
        while (DoubleVector.distance(x0, x1) > 2 * eps && iter < maxIterations);
        System.out.println("iters" + iter);
        System.out.println("eps: " + DoubleVector.distance(x0, x1));
        return x1;

    }
    public static DoubleVector conjGradientDescend(IFunctionND function, DoubleVector xStart, double eps, int maxIterations) {
        DoubleVector x0 = new DoubleVector(xStart);
        DoubleVector s0 = DoubleVector.gradient(function, xStart, eps).mul(-1.0);
        DoubleVector x1, s1;
        int iter = 0;
        do{
            x1 = DoubleVector.add(x0, s0);
            x1 = SecondLaboratoryWork.fibonacci(function, x0, x1, eps);
            iter++;
            if (DoubleVector.distance(x1, x0) > 2 * eps) {
                s1 = DoubleVector.gradient(function, x1, eps);
                s0.mul(Math.pow(s1.magnitude() / s0.magnitude(), 2)).sub(s1);
                x0 = x1;
            }
            else break;
        }
        while (iter < maxIterations);
        System.out.println("iters: " + iter);
        System.out.println("eps: "  + DoubleVector.distance(x0, x1));
        return x1;
    }
    public static DoubleVector newtoneRaphson(IFunctionND function, DoubleVector xStart, double eps, int maxIterations) {
        DoubleVector x0 = new DoubleVector(xStart);
        DoubleVector x1, grad;
        DoubleMatrix invHessian;
        int iter = 0;
        do{
            iter++;
            invHessian = DoubleMatrix.invert(DoubleMatrix.hessian(function, x0));
            grad = DoubleVector.gradient(function, x0, eps);
            x1 = DoubleVector.sub(x0, DoubleMatrix.mul(invHessian, grad));
            if (DoubleVector.distance(x1, x0) > 2 * eps) x0 = x1;
            else break;
        }
        while (iter < maxIterations);
        System.out.println("iters: " + iter);
        System.out.println("eps: "  + DoubleVector.distance(x0, x1));
        return x1;
    }
}
