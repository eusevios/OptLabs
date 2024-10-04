package LAB1;

import MathUtils.IFunction1D;

public class GoldenRatio {
    public static double getMinimum(IFunction1D func, double lhs, double rhs, double eps, int maxIters) {
        final double PSI = 2/(1+Math.sqrt(5));
        double xr = lhs + (rhs-lhs) * PSI;
        double xl = rhs - (rhs-lhs) * PSI;
        double fr = func.apply(xr);
        double fl = func.apply(xl);
        int iter = 0;
        for(; (rhs-lhs)>=2*eps && iter < maxIters; iter++){
            if(fl>fr){
                lhs = xl;
                xl = xr;
                fl = fr;
                xr = lhs + (rhs-lhs) * PSI;
                fr = func.apply(xr);
            }
            else{
                rhs = xr;
                xr = xl;
                fr = fl;
                xl = rhs - (rhs-lhs) * PSI;
                fl = func.apply(xl);
            }
        }

        return (rhs+lhs)/2;
    }
}
