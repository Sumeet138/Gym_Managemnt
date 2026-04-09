package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton loginButton, clearButton, signUpButton;
    JTextField memberIdField;
    JPasswordField pinTextField;

    public static void startApplication() {
        new Login();
    }

    Login() {
        setLayout(null);
        setTitle("FITZONE GYM & FITNESS");

        ImageIcon i1;
        try {
            i1 = new ImageIcon(ClassLoader.getSystemResource("icons/gym_logo.png"));
            Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(50, 40, 100, 100);
            add(label);
        } catch (Exception e) {
            // Logo not available, skip gracefully
            JLabel placeholder = new JLabel("🏋️ FITZONE");
            placeholder.setFont(new Font("Segoe UI", Font.BOLD, 28));
            placeholder.setForeground(new Color(255, 107, 53));
            placeholder.setBounds(50, 40, 200, 50);
            add(placeholder);
        }

        getContentPane().setBackground(new Color(26, 26, 46));
        setSize(700, 400);
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel text = new JLabel("WELCOME TO FITZONE");
        text.setFont(new Font("Segoe UI", Font.BOLD, 38));
        text.setForeground(Color.WHITE);
        text.setBounds(170, 80, 450, 40);
        add(text);

        JLabel memberIdLabel = new JLabel("Member ID: ");
        memberIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        memberIdLabel.setForeground(Color.WHITE);
        memberIdLabel.setBounds(60, 180, 150, 30);
        add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(290, 180, 300, 30);
        memberIdField.setBackground(new Color(64, 64, 64));
        memberIdField.setForeground(Color.WHITE);
        memberIdField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(memberIdField);

        JLabel pinLabel = new JLabel("PIN: ");
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setBounds(60, 230, 150, 30);
        add(pinLabel);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(290, 230, 300, 30);
        pinTextField.setBackground(new Color(64, 64, 64));
        pinTextField.setForeground(Color.WHITE);
        pinTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(pinTextField);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(80, 292, 150, 30);
        loginButton.setBackground(new Color(255, 107, 53));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.addActionListener(this);
        add(loginButton);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(260, 292, 150, 30);
        clearButton.setBackground(new Color(255, 107, 53));
        clearButton.setForeground(Color.BLACK);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.addActionListener(this);
        add(clearButton);

        signUpButton = new JButton("NEW MEMBER");
        signUpButton.setBounds(440, 292, 150, 30);
        signUpButton.setBackground(new Color(255, 107, 53));
        signUpButton.setForeground(Color.BLACK);
        signUpButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signUpButton.addActionListener(this);
        add(signUpButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == clearButton) {
                memberIdField.setText("");
                pinTextField.setText("");

            } else if (ae.getSource() == loginButton) {
                String memberId = memberIdField.getText().trim();
                String pinNo = new String(pinTextField.getPassword()).trim();

                if (memberId.isEmpty() || pinNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Member ID and PIN.");
                    return;
                }

                Conn c = new Conn();
                if (c.connection == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed! Please check configuration.");
                    return;
                }

                // Using PreparedStatement to prevent SQL injection
                String query = "SELECT * FROM login WHERE member_id = ? AND pin = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, memberId);
                pstmt.setString(2, pinNo);
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    setVisible(false);
                    new Dashboard(memberId).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Member ID or PIN.");
                }

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
