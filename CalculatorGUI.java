//import the following packages and classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Inheritance JFrame is the Parent class
//CalculatorGUI is the child
public class CalculatorGUI extends JFrame {
    //Class level variables
    //Accessible to only methods in this class
    private JTextField display;
    private double num1 = 0;
    private String operator = "";
    private boolean startNewInput = true;

    //Constructor method for this class
    public CalculatorGUI() {
        setTitle("Calculator");  //Set the window frame text title bar
        setSize(300, 400);       //Set the window frame dimensions 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Set the window frame for what happens when closed
        setLocationRelativeTo(null); //Set the window frame location

        display = new JTextField(20);  //Display (object) which is field for numbers selected
        display.setEditable(false);    //set the display field for display only
        display.setHorizontalAlignment(JTextField.RIGHT); //set the horiz align
        add(display, BorderLayout.NORTH); //Add display(object) to the window frame

        JPanel buttonPanel = new JPanel(); //New button Panel Object
        buttonPanel.setLayout(new GridLayout(4, 4)); //Set the button Panel dimensions

        //Array that hold the collection of button string labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        //For each looping structure 
        //which goes through the collection one object at a time
        //Adding the button to the button panel object
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonListener());
            buttonPanel.add(button);
        }
        //Add the Button Panel object to the window frame
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (startNewInput) {
                display.setText("");
                startNewInput = false;
            }

            if (command.matches("[0-9]|\\.")) {
                display.setText(display.getText() + command);
            } else if (command.equals("=")) {
                double num2 = Double.parseDouble(display.getText());
                double result = performOperation(num1, num2, operator);
                display.setText(String.valueOf(result));
                num1 = result;
                startNewInput = true;
            } else {
                operator = command;
                num1 = Double.parseDouble(display.getText());
                startNewInput = true;
            }
        }
    }

    private double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            default:
                return num2;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
