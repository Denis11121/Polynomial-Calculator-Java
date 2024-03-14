package businessLogic;

import DATA.Polynomial;
import GUI.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorView view;
    private Convertor convertor=new Convertor();

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
            try{
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = convertor.parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = convertor.parsePolynomial(secondPolynomialStr);
            Polynomial result = polynomial1.add(polynomial2);
            view.setResultPolynomial(result.toString());
        }
        catch (IllegalArgumentException ex) {
            view.setResultPolynomial("Error: " + ex.getMessage());
        }
        }

    }

    class SubListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String firstPolynomialStr = view.getFirstPolynomial();
                String secondPolynomialStr = view.getSecondPolynomial();
                Polynomial polynomial1 = convertor.parsePolynomial(firstPolynomialStr);
                Polynomial polynomial2 = convertor.parsePolynomial(secondPolynomialStr);
                Polynomial result = polynomial1.sub(polynomial2);
                view.setResultPolynomial(result.toString());
            } catch (IllegalArgumentException ex) {
                view.setResultPolynomial("Error: " + ex.getMessage());
            }
        }
    }

    class MulListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = convertor.parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = convertor.parsePolynomial(secondPolynomialStr);
            Polynomial result = polynomial1.multiply(polynomial2);
            view.setResultPolynomial(result.toString());
        }
           catch (IllegalArgumentException ex) {
            view.setResultPolynomial("Error: " + ex.getMessage());
        }
    }
    }

    class DivListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
            String firstPolynomialStr = view.getFirstPolynomial();
            String secondPolynomialStr = view.getSecondPolynomial();
            Polynomial polynomial1 = convertor.parsePolynomial(firstPolynomialStr);
            Polynomial polynomial2 = convertor.parsePolynomial(secondPolynomialStr);
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
           catch (IllegalArgumentException ex) {
            view.setResultPolynomial("Error: " + ex.getMessage());
        }
    }
    }

    class DerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            String firstPolynomialStr = view.getFirstPolynomial();
            Polynomial polynomial1 = convertor.parsePolynomial(firstPolynomialStr);
            Polynomial result = polynomial1.derivative();
            view.setResultPolynomial(result.toString());
        }
           catch (IllegalArgumentException ex) {
            view.setResultPolynomial("Error: " + ex.getMessage());
        }
    }
    }

    class IntListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            String firstPolynomialStr = view.getFirstPolynomial();
            Polynomial polynomial1 = convertor.parsePolynomial(firstPolynomialStr);
            Polynomial result = polynomial1.integral();
            view.setResultPolynomial(result.toString()+"+C");
        }
           catch (IllegalArgumentException ex) {
            view.setResultPolynomial("Error: " + ex.getMessage());
        }
    }
    }








/*    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(view);
        view.setVisible(true);
    }*/
}
