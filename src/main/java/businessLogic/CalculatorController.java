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

        // Separăm termenii folosind + sau -
        String[] terms = input.split("(?=[-+])");

        for (String term : terms) {
            double coefficient = 0.0; // Modificăm aici pentru a ține cont de coeficientul implicit 0
            int exponent = 0;

            // Dacă termenul începe cu -x^, îl convertim la -1x^
            if (term.matches("-x\\^.*")) {
                term = "-1" + term.substring(1);
            }

            // Încercăm să identificăm coeficientul și exponentul
            if (term.contains("x^")) {
                String[] parts = term.split("x\\^");
                // Verificăm și setăm coeficientul
                if (!parts[0].isEmpty()) {
                    coefficient = Double.parseDouble(parts[0]);
                } else {
                    coefficient = 1.0; // Coeficient implicit 1
                }
                exponent = Integer.parseInt(parts[1]);
            } else if (term.contains("x")) {
                // Dacă termenul conține "x" dar nu are exponent, este un x simplu sau -x simplu
                if (term.equals("x") || term.equals("-x")) {
                    coefficient = term.startsWith("-") ? -1.0 : 1.0;
                } else {
                    coefficient = Double.parseDouble(term.substring(0, term.indexOf("x")));
                }
                exponent = 1;
            } else {
                // Termen constant
                coefficient = Double.parseDouble(term);
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
