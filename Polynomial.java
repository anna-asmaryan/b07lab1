class Polynomial {
    double[] coefficients;
    
    public Polynomial(){
        this.coefficients = new double[]{0};
    }
    public Polynomial(double[] coeffs){
        this.coefficients = coeffs;
    }
    
    public Polynomial add(Polynomial poly){
        
        int smaller = Math.min(this.coefficients.length, poly.coefficients.length);
        int bigger = Math.max(this.coefficients.length, poly.coefficients.length);
        double[] added = new double[bigger];
        
        for (int i = 0; i<smaller; i++){
            added[i] = this.coefficients[i] + poly.coefficients[i];
        }
        for (int i = smaller; i<bigger; i++){
            if (this.coefficients.length == bigger)
            added[i] = this.coefficients[i];
            else
            added[i] = poly.coefficients[i];
        }
        
        Polynomial sumpoly = new Polynomial(added);
        return sumpoly;
    }
    
    public double evaluate(double x){
        double ans = 0;

        for (int i = 0; i< this.coefficients.length; i++){
            ans += Math.pow(x, i)*(this.coefficients[i]);
            
        }
        return ans;
    }
    
    public boolean hasRoot(double x){
        return this.evaluate(x) == 0;
    }
    
    
}