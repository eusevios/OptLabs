package LAB1;

import mathUtils.functionalInterfaces.IFunction1D;

public class GoldenRatio {
    public static double getMinimum(IFunction1D func, double lhs, double rhs, double eps, int maxIters) {
        final double PSI = 2/(1+Math.sqrt(5));
        double xr = lhs + (rhs-lhs) * PSI;
        double xl = rhs - (rhs-lhs) * PSI;
        double fr = func.call(xr);
        double fl = func.call(xl);
        int iter = 0;
        for(; (rhs-lhs)>=2*eps && iter < maxIters; iter++){
            if(fl>fr){
                lhs = xl;
                xl = xr;
                fl = fr;
                xr = lhs + (rhs-lhs) * PSI;
                fr = func.call(xr);
            }
            else{
                rhs = xr;
                xr = xl;
                fr = fl;
                xl = rhs - (rhs-lhs) * PSI;
                fl = func.call(xl);
            }
        }

        return (rhs+lhs)/2;
    }
}
