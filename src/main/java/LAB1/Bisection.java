package LAB1;

import mathUtils.functionalInterfaces.IFunction1D;

public class Bisection {
    public static double getMinimum(IFunction1D func, double lhs, double rhs, double eps, int maxIters){
        double xc = (rhs-lhs)/2;
        int iter = 0;
        for(; iter <= maxIters && (rhs-lhs)>=2*eps; iter++){
            xc = (rhs + lhs)/2;
            if(func.call(xc-eps) > func.call(xc+eps)) lhs = xc;
            else rhs = xc;
        }
        return xc;
    }

}
