package businessLogic;

import DATA.Polynomial;
import GUI.CalculatorView;

public class Convertor {
    private CalculatorView view=new CalculatorView();

    Polynomial parsePolynomial(String input) {
        Polynomial polynomial = new Polynomial();
        if (input == null || input.trim().isEmpty()) {
            return polynomial;
        }

        // sterge spatiile
        input = input.replaceAll("\\s+", "");

        // imparte inputul cu + - pt a separa termenii
        String[] terms = input.split("(?=[-+])");

        for (String term : terms) {
            double coefficient = 1.0;
            int exponent = 0;

            //x^ semn - urmat de x^
            if (term.matches("-x\\^.*")) {
                term = "-1" + term.substring(1); //schimba -x^ cu -1x^
            }

            if (term.contains("x^")) {
                String[] parts = term.split("x\\^");
                try {
                    coefficient = parts[0].isEmpty() ? 1.0 : Double.parseDouble(parts[0]);
                    exponent = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    // caz termen invalid
                    view.setResultPolynomial("Error: Invalid term");
                    throw new IllegalArgumentException("Invalid term: " + term);
                }
            } else if (term.contains("x")) {
                // exponent implicit
                try {
                    if (term.equals("x") || term.equals("-x") || term.equals("+x")) {
                        coefficient = term.startsWith("-") ? -1.0 : 1.0;
                    } else {
                        coefficient = Double.parseDouble(term.substring(0, term.indexOf("x")));
                    }
                    exponent = 1;
                } catch (NumberFormatException e) {
                    // caz termen invalid
                    view.setResultPolynomial("Error: Invalid term");
                    throw new IllegalArgumentException("Invalid term: " + term);
                }
            } else {
                // Constant term (e.g., "4" or "-5")
                try {
                    coefficient = Double.parseDouble(term);
                } catch (NumberFormatException e) {
                    // caz termen invalid
                    view.setResultPolynomial("Error: Invalid term");
                    throw new IllegalArgumentException("Invalid term: " + term);
                }
            }

            polynomial.addTerms(exponent, coefficient);
        }

        return polynomial;
    }
}
