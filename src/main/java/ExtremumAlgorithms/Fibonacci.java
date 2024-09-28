package ExtremumAlgorithms;

public class Fibonacci {
    public static double getMinimum(IFunction1D func, double lhs, double rhs, double eps) {
        final double PHI = 2/(1+Math.sqrt(5));
        double xr = lhs + (rhs-lhs) * PHI;
        double xl = rhs - (rhs-lhs) * PHI;
        double fr = func.apply(xr);
        double fl = func.apply(xl);
        for(int iter = 0; (rhs-lhs)>=2*eps; iter++){
            if(fl>fr){
                lhs = xl;
                xl = xr;
                fl = fr;
                xr = lhs + (rhs-lhs) * PHI;
                fr = func.apply(xr);
            }
            else{
                rhs = xr;
                xr = xl;
                fr = fl;
                xl = rhs - (rhs-lhs) * PHI;
                fl = func.apply(xl);
            }
        }
        return (rhs+lhs)/2;
    }

    class FibonacciNumbers{
        private double fn;
        private double fn1;
        FibonacciNumbers(double fn, double fn1){
            this.fn = fn;
            this.fn1 = fn1;
        }
    }
}
