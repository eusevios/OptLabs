package mathUtils;

public class FibonacciNumbers{
    private double fn;
    private double fn1;

    public int iter;

    public double getFn() {
        return fn;
    }

    public double getFn1() {
        return fn1;
    }

    public int getIter(){return iter;}


    public FibonacciNumbers(double ratio){
        int iter = 0;
        this.fn = 1; this.fn1 = 1;
        double tmp;
        while (this.fn1 < ratio) {
            iter++;
            tmp = this.fn;
            this.fn = this.fn1;
            this.fn1 += tmp;
        }
        this.iter = iter;
    }
    public void updatePair(){
        double tmp = this.fn;
        this.fn = this.fn1 - this.fn;
        this.fn1 = tmp;
    }
}