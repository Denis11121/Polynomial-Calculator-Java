package businessLogic;

import DATA.Polynomial;
import GUI.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorView view;

    public CalculatorController(CalculatorView view) {
        this.view = view;
        this.view.addAddListener(new AddListener());
        this.view.addSubListener(new SubListener());
        this.view.addMulListener(new MulListener());
        this.view.addDerListener(new DerListener());
        this.view.addIntListener(new IntListener());
        this.view.addDivListener(new DivListener());
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = parsePolynomial(secondPolynomialStr);
            Polynomial result = polynomial1.add(polynomial2);
            view.setResultPolynomial(result.toString());
        }
    }

    class SubListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = parsePolynomial(secondPolynomialStr);
            Polynomial result = polynomial1.sub(polynomial2);
            view.setResultPolynomial(result.toString());
        }
    }

    class MulListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = parsePolynomial(secondPolynomialStr);
            Polynomial result = polynomial1.multiply(polynomial2);
            view.setResultPolynomial(result.toString());
        }
    }

    class DivListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = parsePolynomial(secondPolynomialStr);
            if (polynomial2.degree() == 0 && polynomial2.getCoefficient(0) == 0.0) {
                view.setResultPolynomial("Error: Division by zero");
                throw new IllegalArgumentException("Dividing by zero polynomial is not allowed.");
            }
            else if(polynomial2.degree()>polynomial1.degree())
            {
                view.setResultPolynomial("Error: Numerator bigger than denominator");
                throw new IllegalArgumentException("Denominator can not have a bigger exponent then numinator");
            }
            Polynomial[] divisionResult = polynomial1.divideWithRemainder(polynomial2);
            Polynomial quotient = divisionResult[0];
            Polynomial remainder = divisionResult[1];

            view.setResultPolynomial("Quotient: " + quotient + ", Remainder: " + remainder);

        }
    }

    class DerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstPolynomialStr = view.getFirstPolynomial();
            Polynomial polynomial1 = parsePolynomial(firstPolynomialStr);
            Polynomial result = polynomial1.derivative();
            view.setResultPolynomial(result.toString());
        }
    }

    class IntListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstPolynomialStr = view.getFirstPolynomial();
            Polynomial polynomial1 = parsePolynomial(firstPolynomialStr);
            Polynomial result = polynomial1.integral();
            view.setResultPolynomial(result.toString()+"+C");
        }
    }

    private Polynomial parsePolynomial(String input) {
        Polynomial polynomial = new Polynomial();
        if (input == null || input.trim().isEmpty()) {
            return polynomial;
        }

        // Eliminăm spațiile
        input = input.replaceAll("\\s+", "");

        // Dacă începe cu un semn "+", îl eliminăm
        if (input.startsWith("+")) {
            input = input.substring(1);
        }

        // Procesăm fiecare termen separat
        int index = 0;
        while (index < input.length()) {
            char currentChar = input.charAt(index);
            double coefficient = 0.0; // Coeficient implicit 1
            int exponent = 0;

            // Verificăm semnul termenului
            boolean negative = false;
            if (currentChar == '-') {
                negative = true;
                index++;
            } else if (currentChar == '+') {
                index++;
            }

            // Găsim coeficientul
            StringBuilder coefficientBuilder = new StringBuilder();
            while (index < input.length() && (Character.isDigit(input.charAt(index)) || input.charAt(index) == '.')) {
                coefficientBuilder.append(input.charAt(index));
                index++;
            }
            if (coefficientBuilder.length() == 0) {
                coefficient = 1.0; // Implicit coeficientul este 1 pentru termenii care conțin "x"
            } else {
                coefficient = Double.parseDouble(coefficientBuilder.toString());
            }

            // Dacă mai sunt caractere și ele reprezintă "x"
            if (index < input.length() && input.charAt(index) == 'x') {
                index++; // trecem peste 'x'
                // Dacă următorul caracter este '^', atunci extragem exponentul
                if (index < input.length() && input.charAt(index) == '^') {
                    index++; // trecem peste '^'
                    // Găsim exponentul
                    StringBuilder exponentBuilder = new StringBuilder();
                    while (index < input.length() && Character.isDigit(input.charAt(index))) {
                        exponentBuilder.append(input.charAt(index));
                        index++;
                    }
                    if (exponentBuilder.length() > 0) {
                        exponent = Integer.parseInt(exponentBuilder.toString());
                    } else {
                        exponent = 1; // Exponent implicit este 1
                    }
                } else {
                    exponent = 1; // Dacă nu există '^', atunci exponentul este 1
                }
            }

            // Aplicăm semnul negativ dacă este cazul
            if (negative) {
                coefficient = -coefficient;
            }

            // Adăugăm termenul la polinom
            polynomial.addTerms(exponent, coefficient);
        }

        return polynomial;
    }




/*    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(view);
        view.setVisible(true);
    }*/
}
