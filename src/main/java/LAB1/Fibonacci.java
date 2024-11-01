package LAB1;
import mathUtils.FibonacciNumbers;
import mathUtils.functionalInterfaces.IFunction1D;

public class Fibonacci {
    public static double getMinimum(IFunction1D func, double lhs, double rhs, double eps) {
        FibonacciNumbers fibonacciNumbers = new FibonacciNumbers((rhs-lhs)/eps);
        double xl = lhs + (fibonacciNumbers.getFn1() - fibonacciNumbers.getFn()) / fibonacciNumbers.getFn1() * (rhs - lhs);
        double xr = lhs + fibonacciNumbers.getFn() / fibonacciNumbers.getFn1() * (rhs - lhs);
        double fr = func.call(xr);
        double fl = func.call(xl);
        while ((rhs - lhs) > 2 * eps) {
            if(fl>fr){
                lhs = xl;
                xl = xr;
                fl = fr;
                xr = lhs + (rhs-lhs) * fibonacciNumbers.getFn() / fibonacciNumbers.getFn1();
                fr = func.call(xr);
            }
            else{
                rhs = xr;
                xr = xl;
                fr = fl;
                xl = lhs + (rhs-lhs) * (fibonacciNumbers.getFn1() - fibonacciNumbers.getFn()) / fibonacciNumbers.getFn1();
                fl = func.call(xl);
            }
            fibonacciNumbers.updatePair();
        }
        return (rhs+lhs)/2;
    }


}
