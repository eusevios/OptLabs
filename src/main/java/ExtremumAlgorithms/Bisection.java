package ExtremumAlgorithms;

public class Bisection {
    public static double getMinimum(IFunction1D func, double lhs, double rhs, double eps, int maxIters){
        double xc = 0;
        for(int iter = 0; iter <= maxIters && (rhs-lhs)>=2*eps; iter++){
            xc = (rhs + lhs)/2;
            if(func.apply(xc-eps) > func.apply(xc+eps)) lhs = xc;
            else rhs = xc;
        }
        return xc;
    }

}
