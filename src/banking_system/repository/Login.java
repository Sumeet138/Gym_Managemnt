package banking_system.repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton loginButton, clearButton, signUpButton;
    JTextField cardTextField;
    JPasswordField pinTextField;

    public static void startApplication() {
        new Login();
    }

    Login() {
        // Set constraints and functionality for the frame
        setLayout(null);
        setTitle("AUTOMATED TELLER MACHINE");

        // Class to get image on the screen
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atmicon.jpg"));

        // To scale the image for perfect size
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        // Convert i2 to ImageIcon
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);

        // Change image location
        label.setBounds(50, 40, 100, 100);
        add(label);

        // Change color of frame
        getContentPane().setBackground(Color.BLACK);
        setSize(700, 400);
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // To add label or text on frame use JLabel
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setForeground(Color.WHITE);
        text.setBounds(190, 80, 400, 40);
        add(text);

        JLabel cardNo = new JLabel("Card No: ");
        cardNo.setFont(new Font("Railway", Font.BOLD, 20));
        cardNo.setForeground(Color.WHITE);
        cardNo.setBounds(60, 180, 150, 30);
        add(cardNo);

        // Add text field to let user input values
        cardTextField = new JTextField();
        cardTextField.setBounds(290, 180, 300, 30);
        cardTextField.setBackground(Color.DARK_GRAY);
        cardTextField.setForeground(Color.WHITE);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 15));
        add(cardTextField);

        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Railway", Font.BOLD, 20));
        pin.setForeground(Color.WHITE);
        pin.setBounds(60, 230, 150, 30);
        add(pin);

        // Add password field
        pinTextField = new JPasswordField();
        pinTextField.setBounds(290, 230, 300, 30);
        pinTextField.setBackground(Color.DARK_GRAY);
        pinTextField.setForeground(Color.WHITE);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 15));
        add(pinTextField);

        // Buttons
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(80, 292, 150, 30);
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(this);
        add(loginButton);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(260, 292, 150, 30);
        clearButton.setBackground(Color.ORANGE);
        clearButton.setForeground(Color.BLACK);
        clearButton.addActionListener(this);
        add(clearButton);

        signUpButton = new JButton("SIGN UP");
        signUpButton.setBounds(440, 292, 150, 30);
        signUpButton.setBackground(Color.ORANGE);
        signUpButton.setForeground(Color.BLACK);
        signUpButton.addActionListener(this);
        add(signUpButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == clearButton) {
                // Clear the text fields
                cardTextField.setText("");
                pinTextField.setText("");

            } else if (ae.getSource() == loginButton) {


                String cardNo = cardTextField.getText().trim();
                String pinNo = new String(pinTextField.getPassword()).trim();

                // Check if fields are empty
                if (cardNo.isEmpty() || pinNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Card Number and PIN.");
                    return;
                }

                // Create database connection

                Conn c = new Conn();

                if (c.connection == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed!");

                    return;
                }

                String query = "SELECT * FROM login WHERE card_no = '" + cardNo + "' AND pin = '" + pinNo + "'";

                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()) {

                    setVisible(false);
                    new Transaction(pinNo).setVisible(true);
                } else {

                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN.");
                }

                // Close the connection
                c.connection.close();

            } else if (ae.getSource() == signUpButton) {

                setVisible(false);
                new SignUp().setVisible(true);
            }

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}