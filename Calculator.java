import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Calculator implements ActionListener {
    static JFrame frame;
    JPanel[] panel;
    JTextField textfield;
    JButton[] button_num;
    JButton[] button_oper;
    ImageIcon icon;
    String[] symbol = {"+", "-", "*", "/", "=", ".", "Delete"};

    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    Calculator() {
        frame = new JFrame();
        icon = new ImageIcon("Calculator.png");
        panel = new JPanel[2];
        frame.setSize(450, 600);
        frame.setTitle("Simple Calculator");
        frame.setIconImage(icon.getImage());
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.createB();
        cal.createP(18, 100, 400, 350, Color.white, 0);
        cal.createP(18, 20, 400, 75, Color.red, 1);
        cal.createTF();
        frame.setVisible(true);
    }

    public void createB() {
        button_num = new JButton[10];
        button_oper = new JButton[7];
        for (byte i = 0; i <= 9; i++) {
            button_num[i] = new JButton(Integer.toString(i));
            button_num[i].addActionListener(this);
            if (i <= 6) {
                button_oper[i] = new JButton(symbol[i]);
                button_oper[i].addActionListener(this);
            }
        }
    }

    void createP(int x, int y, int height, int width, Color color, int i) {
        panel[i] = new JPanel();
        panel[i].setBounds(x, y, height, width);
        panel[i].setBackground(color);

        if (i == 0) {
            panel[i].setLayout(new GridLayout(4, 4, 5, 5));
            for (byte j = 7; j <= 9; j++) panel[i].add(button_num[j]);
            panel[i].add(button_oper[0]);

            for (byte j = 4; j <= 6; j++) panel[i].add(button_num[j]);
            panel[i].add(button_oper[1]);

            for (byte j = 1; j <= 3; j++) panel[i].add(button_num[j]);
            panel[i].add(button_oper[2]);

            panel[i].add(button_oper[5]); // .
            panel[i].add(button_num[0]);
            panel[i].add(button_oper[6]); // Delete
            panel[i].add(button_oper[3]); // /
        } else {
            panel[i].setLayout(new BorderLayout());
        }

        button_oper[4].setBounds(18, 453, 400, 75); // =
        frame.add(button_oper[4]);
        frame.add(panel[i]);
    }

    void createTF() {
        textfield = new JTextField();
        textfield.setFont(new Font("Arial", Font.PLAIN, 24));
        panel[1].add(textfield, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == button_num[i]) {
                textfield.setText(textfield.getText().concat(Integer.toString(i)));
                return;
            }
        }

        if (e.getSource() == button_oper[5]) {
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText().concat("."));
            }
            return;
        }

        if (e.getSource() == button_oper[6]) {
            String current = textfield.getText();
            if (!current.isEmpty()) {
                textfield.setText(current.substring(0, current.length() - 1));
            }
            return;
        }

        if (e.getSource() == button_oper[0] || e.getSource() == button_oper[1] ||
            e.getSource() == button_oper[2] || e.getSource() == button_oper[3]) {

            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());
                operator = ((JButton) e.getSource()).getText();
                textfield.setText("");
            }
            return;
        }

        if (e.getSource() == button_oper[4]) {
            if (!textfield.getText().isEmpty()) {
                num2 = Double.parseDouble(textfield.getText());

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            textfield.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        return;
                }

                textfield.setText(Double.toString(result));
                num1 = result;
                operator = "";
            }
        }
    }
}
