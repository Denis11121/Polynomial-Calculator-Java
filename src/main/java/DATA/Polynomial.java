package DATA;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private Map<Integer,Double> terms;

    public Polynomial(){
        terms=new HashMap<>();
    }

    public void addTerms(Integer exponent, Double coefficient){
        terms.put(exponent,coefficient);
    }

    public Polynomial add(Polynomial polynomial){
        Polynomial result=new Polynomial();
        for(Map.Entry<Integer,Double> term: terms.entrySet()){
            Integer exponent=term.getKey();
            Double coefficient=term.getValue();
            result.addTerms(exponent,coefficient);
        }
        for(Map.Entry<Integer,Double> term: polynomial.terms.entrySet()) {
            Integer exponent=term.getKey();
            Double coefficient=term.getValue();
            if(result.terms.containsKey(exponent)){
                coefficient+=result.terms.get(exponent);
            }
            result.addTerms(exponent,coefficient);
        }
        return result;
    }

    public Polynomial sub(Polynomial polynomial){
        Polynomial result=new Polynomial();
        for(Map.Entry<Integer,Double> term: terms.entrySet()){
            Integer exponent=term.getKey();
            Double coefficient=term.getValue();
            result.addTerms(exponent,coefficient);
        }
        for(Map.Entry<Integer,Double> term: polynomial.terms.entrySet()) {
            Integer exponent=term.getKey();
            Double coefficient=-term.getValue();
            if(result.terms.containsKey(exponent)){
                coefficient+=result.terms.get(exponent);
            }
            result.addTerms(exponent,coefficient);
        }
        return result;
    }

    public Polynomial multiply(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Double> term1 : terms.entrySet()) {
            for (Map.Entry<Integer, Double> term2 : polynomial.terms.entrySet()) {
                Integer exponent = term1.getKey() + term2.getKey();
                Double coefficient = term1.getValue() * term2.getValue();
                if (result.terms.containsKey(exponent)) {
                    coefficient += result.terms.get(exponent);
                }
                result.addTerms(exponent, coefficient);
            }
        }
        return result;
    }

    public Polynomial[] divideWithRemainder(Polynomial divisor) {
        if(divisor.degree()>this.degree()) {
            throw new IllegalArgumentException("Denominator can not have a bigger exponent than numinator");
        }
        if (divisor.degree() == 0 && divisor.getCoefficient(0) == 0.0) {
            throw new IllegalArgumentException("Dividing by zero polynomial is not allowed.");
        }

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        for (Map.Entry<Integer, Double> term : this.terms.entrySet()) {
            Integer thisExponent = term.getKey();
            Double thisCoefficient = term.getValue();
            remainder.addTerms(thisExponent, thisCoefficient); // copiaza termenii in remainder
        }
        while (remainder.degree() >= divisor.degree()) {
            int expDiff = remainder.degree() - divisor.degree();
            double coeff = remainder.leadingCoefficient() / divisor.leadingCoefficient();
            Map<Integer, Double> termMap = new HashMap<>();
            termMap.put(expDiff, coeff);
            Polynomial termPoly = new Polynomial();
            termPoly.terms=termMap;
            quotient = quotient.add(termPoly);
            Polynomial termProduct = divisor.multiply(termPoly);
            remainder = remainder.sub(termProduct);

            // sterge termenii cu coeficient zero
            for (Map.Entry<Integer, Double> term : remainder.terms.entrySet()) {
                if (term.getValue() == 0.0) {
                    remainder.terms.remove(term.getKey());
                }
            }
        }
        return new Polynomial[] { quotient, remainder };
    }




    public int degree() {
        if (terms.isEmpty()) return -1;
        return terms.keySet().stream().mapToInt(Integer::intValue).max().getAsInt();
    }
    public boolean isZero() {
        return terms.isEmpty();
    }
    public double leadingCoefficient() {
        if (terms.isEmpty()) return 0.0;
        return terms.get(degree());
    }
    public double getCoefficient(int exponent) {
        return terms.getOrDefault(exponent, 0.0); //returneaza 0,0 pt exponenti neexistenti
    }

    public Polynomial derivative() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Double> term : terms.entrySet()) {
            Integer exponent = term.getKey();
            Double coefficient = term.getValue();
            if (exponent > 0) {
                result.addTerms(exponent - 1, coefficient * exponent);
            }
        }
        return result;
    }

    public Polynomial integral() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Double> term : terms.entrySet()) {
            Integer exponent = term.getKey();
            Double coefficient = term.getValue();
            result.addTerms(exponent + 1, coefficient / (exponent + 1));
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        boolean isFirstTerm = true;
        for (Map.Entry<Integer, Double> term : terms.entrySet()) {
            Integer exponent = term.getKey();
            Double coefficient = term.getValue();



            if (coefficient > 0) {
                if (!isFirstTerm) {
                    string.append(" + ");
                }
            } else if (coefficient < 0) {
                string.append(" - ");
                coefficient = -coefficient;
            }

            if (exponent == 0) {
                string.append(coefficient);
            } else {
                if (coefficient != 1) {
                    string.append(coefficient);
                }
                string.append("x");
                if (exponent != 1) {
                    string.append("^").append(exponent);
                }
            }

            if (isFirstTerm) {
                isFirstTerm = false;
            }
        }


        String pattern="0\\.0x(\\^\\d+)?";
        if(string.toString().matches(pattern)){
            return "0.0";
        }
        return string.toString();
    }



}
