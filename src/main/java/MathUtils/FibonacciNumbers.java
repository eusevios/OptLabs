package MathUtils;

public class FibonacciNumbers{
    private double fn;
    private double fn1;

    public double getFn() {
        return fn;
    }

    public double getFn1() {
        return fn1;
    }

    public FibonacciNumbers(double ratio){
        this.fn = 1; this.fn1 = 1;
        double tmp;
        while (this.fn1 < ratio) {
            tmp = this.fn;
            this.fn = this.fn1;
            this.fn1 += tmp;
        }
    }
    public void updatePair(){
        double tmp = this.fn;
        this.fn = this.fn1 - this.fn;
        this.fn1 = tmp;
    }
}