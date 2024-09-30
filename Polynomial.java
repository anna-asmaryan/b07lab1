package demo;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;



class Polynomial {
	
	
	double[] coefficients;
    int[] exponents;
    
    public Polynomial(File file)throws FileNotFoundException {
    	Scanner scanner = new Scanner(file);
        String polystr = scanner.nextLine();
        scanner.close();
        
        
        ArrayList<Double> coefs = new ArrayList<>();
        ArrayList<Integer> exps = new ArrayList<>();
        String[] parts = polystr.split("+-()[]?=");

        for (String part : parts) {
        	part = part.trim();

            double coefficient;
            int exponent = 0;

            if (part.contains("x")) {
                String[] sides = part.split("x");

                if (sides[0].equals("") || sides[0].equals("+")) 
                    coefficient = 1;
                
                else if (sides[0].equals("-")) 
                    coefficient = -1;
                
                
                else 
                    coefficient = Double.parseDouble(sides[0]);
                

                if (sides.length == 2) 
                    exponent = Integer.parseInt(sides[1]);
                
                else 
                    exponent = 1;
                
            } 
            else {
                coefficient = Double.parseDouble(part);
                exponent = 0;
            }

            coefs.add(coefficient);
            exps.add(exponent);
        }

        this.coefficients = new double[coefs.size()];
        this.exponents = new int[exps.size()];

        
        for (int i = 0; i < coefs.size(); i++) {
            this.coefficients[i] = coefs.get(i);
            this.exponents[i] = exps.get(i);
        }
    }
        
        
        
    	
    	
    
    public Polynomial(){
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }
    
    public Polynomial(double[] coeffs, int[] exps){
        this.coefficients = coeffs;
        this.exponents = exps;
    }
    
    public Polynomial add(Polynomial poly){
        
        double[] newco = new double [this.coefficients.length + poly.coefficients.length];
        int[] newex = new int [this.exponents.length + poly.exponents.length];

        int ind = 0;
        
        for (int i = 0; i < this.coefficients.length; i++) {
        	
            newco[ind] = this.coefficients[i];
            newex[ind] = this.exponents[i];
            
            ind++;
        }

        for (int i = 0; i < poly.coefficients.length; i++) {
        	
            int flag= 1;
            
            for (int j = 0; j < this.exponents.length; j++) {
                if (poly.exponents[i] == this.exponents[j]) {
                    newco[j] += poly.coefficients[i]; 
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) { 
                newco[ind] = poly.coefficients[i];
                newex[ind] = poly.exponents[i];
                ind++;
            }
            
        }

        double[] lastco = new double[ind];
        int[] lastex = new int[ind];
        for (int i = 0; i<ind; i++) {
        	lastco[i] = newco[i];
        	lastex[i] = newex[i];
        }
        
       
        return new Polynomial(lastco, lastex);
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
    
    
    public Polynomial multiply (Polynomial poly) {

        double[] newco = new double [this.coefficients.length*poly.coefficients.length];
        int[] newex = new int [this.exponents.length*poly.exponents.length];

        int ind = 0;
        
        double coef;
        int exp;

        for (int i = 0; i < poly.coefficients.length; i++) {
        	
            
            
            for (int j = 0; j <this.exponents.length; j++) {
            	int flag= 1;
            	coef = this.coefficients[j] *poly.coefficients[i];
            	exp = this.exponents[j] *poly.exponents[i];
            	for(int k = 0;k<ind; k++) {
	            	if (newex[k] == exp) {
	                    newco[k] += coef;
	                    flag = 0;
	                    break;
	            	}
                }
            
	            if (flag == 1) { 
	                newco[ind] = coef;
	                newex[ind] = exp;
	                ind++;
	            }
            }
        }

        double[] lastco = new double[ind];
        int[] lastex = new int[ind];
        for (int i = 0; i<ind; i++) {
        	lastco[i] = newco[i];
        	lastex[i] = newex[i];
        }
        
       
        return new Polynomial(lastco, lastex);
    	
    	
    }
    
    public void saveToFile(String fileName) throws IOException {
        StringBuilder polystr = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++) {
        	
            double coef = coefficients[i];
            int exp = exponents[i];

            if ( i != 0&& coef >= 0) 
            	polystr.append("+");
            
            if (exp != 0 && coef == -1 ) 
            	polystr.append("-");
            
            else if ( exp == 0 || coef != 1 ) 
            	polystr.append(coef);
            

            if (exp != 0) {
            	polystr.append("x");
                if (exp != 1) 
                	polystr.append(exp);
                
            }
        }

        FileWriter writer = new FileWriter(fileName);
        writer.write(polystr.toString());
        writer.close();
    }


	
    

}
