package org.example;

public class Main {
    public static void main(String[] args) {
        Polynomial polynomial1=new Polynomial();
        polynomial1.addTerms(2,3.0);
        polynomial1.addTerms(1,-1.0);
        polynomial1.addTerms(0,5.0);

        Polynomial polynomial2=new Polynomial();
        polynomial2.addTerms(3, 2.0);
        polynomial2.addTerms(1, 4.0);
        polynomial2.addTerms(0, -7.0);

        Polynomial resultAdd=polynomial1.add(polynomial2);

        Polynomial resultSub=polynomial1.sub(polynomial2);

        Polynomial resultMultiply=polynomial1.multiply(polynomial2);
        Polynomial resultDerivative=polynomial1.derivative();

        Polynomial resultIntegral=polynomial1.integral();

        Polynomial[] divisionResult = polynomial2.divideWithRemainder(polynomial1);
        Polynomial quotient = divisionResult[0];
        Polynomial remainder = divisionResult[1];

        System.out.println("Polynomial 1: "+polynomial1);
        System.out.println("Polynomial 2: "+polynomial2);
        System.out.println("Add result: " +resultAdd);
        System.out.println("Sub result: "+resultSub);
        System.out.println("Multiply result: "+resultMultiply);
        System.out.println("Derivative result: "+resultDerivative);
        System.out.println("Integral result: "+resultIntegral);
        System.out.println("Polynomial 1: " + polynomial1);
        System.out.println("Polynomial 2: " + polynomial2);
        System.out.println();
        System.out.println("Quotient (Polynomial 1 / Polynomial 2): " + quotient);
        System.out.println("Remainder (Polynomial 1 / Polynomial 2): " + remainder);
    }
}