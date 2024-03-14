package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {
    private JLabel firstPolynomialLabel = new JLabel("First Polynomial:");
    private JLabel secondPolynomialLabel = new JLabel("Second Polynomial:");
    private JLabel resultPolynomialLabel = new JLabel("Result:");

    private JTextField enterFirstPolynomialField = new JTextField(10);
    private JTextField enterSecondPolynomialField = new JTextField(10);
    private JTextField resultPolynomialField = new JTextField(10);

    private JButton addButton = new JButton("+");
    private JButton subButton = new JButton("-");
    private JButton mulButton = new JButton("*");
    private JButton divButton = new JButton("/");
    private JButton derButton = new JButton("d/dx");
    private JButton intButton = new JButton("∫");

    public CalculatorView() {
        setTitle("Polynomial Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        setLayout(new GridLayout(3, 1));

        // Adjust font size for labels
        Font labelFont = new Font(Font.DIALOG, Font.BOLD, 30);
        firstPolynomialLabel.setFont(labelFont);
        secondPolynomialLabel.setFont(labelFont);
        resultPolynomialLabel.setFont(labelFont);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        inputPanel.add(firstPolynomialLabel);
        inputPanel.add(enterFirstPolynomialField);
        inputPanel.add(secondPolynomialLabel);
        inputPanel.add(enterSecondPolynomialField);
        inputPanel.add(resultPolynomialLabel);
        inputPanel.add(resultPolynomialField);

        Font textFieldFont = new Font(Font.DIALOG, Font.PLAIN, 25);
        enterFirstPolynomialField.setFont(textFieldFont);
        enterSecondPolynomialField.setFont(textFieldFont);
        resultPolynomialField.setFont(textFieldFont);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(derButton);
        buttonPanel.add(intButton);

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        mulButton.setFont(buttonFont);
        divButton.setFont(buttonFont);
        derButton.setFont(buttonFont);


        add(inputPanel);
        add(buttonPanel);
    }

    public String getFirstPolynomial() {
        return enterFirstPolynomialField.getText();
    }

    public String getSecondPolynomial() {
        return enterSecondPolynomialField.getText();
    }

    public void setResultPolynomial(String result) {
        resultPolynomialField.setText(result);
    }

    public void addAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addSubListener(ActionListener listener) {
        subButton.addActionListener(listener);
    }

    public void addMulListener(ActionListener listener) {
        mulButton.addActionListener(listener);
    }

    public void addDivListener(ActionListener listener) {
        divButton.addActionListener(listener);
    }

    public void addDerListener(ActionListener listener) {
        derButton.addActionListener(listener);
    }

    public void addIntListener(ActionListener listener) {
        intButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorView calculatorView = new CalculatorView();
            calculatorView.setVisible(true);
        });
    }
}
