package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {
    long ran;
    JTextField nameTextField, phoneTextField, emailTextField, emergencyContactTextField, 
               addressTextField, cityTextField, stateTextField, pincodeTextField;
    JButton next;
    JRadioButton male, female, other;
    JDateChooser dateChooser;

    SignUp() {
        setLayout(null);
        setSize(850, 750);
        getContentPane().setBackground(Color.WHITE);
        setLocation(285, 5);
        setTitle("NEW MEMBER REGISTRATION");
        setResizable(false);

        Random random = new Random();
        ran = Math.abs((random.nextLong() % 9000L) + 1000L);

        JLabel textSignup = new JLabel("NEW MEMBER REGISTRATION: " + ran);
        textSignup.setFont(new Font("Segoe UI", Font.BOLD, 28));
        textSignup.setForeground(Color.BLACK);
        textSignup.setBounds(200, 30, 700, 30);
        add(textSignup);

        JLabel page1 = new JLabel("Page 1: Personal & Contact Details");
        page1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        page1.setForeground(Color.BLACK);
        page1.setBounds(260, 70, 400, 30);
        add(page1);

        // Labels and fields setup
        JLabel[] labels = {
            new JLabel("Full Name: "),
            new JLabel("Phone Number: "),
            new JLabel("Date Of Birth: "),
            new JLabel("Gender: "),
            new JLabel("Email Address: "),
            new JLabel("Emergency Contact: "),
            new JLabel("Address: "),
            new JLabel("City: "),
            new JLabel("State: "),
            new JLabel("Pin Code: ")
        };

        int[] yPositions = {130, 180, 230, 280, 330, 380, 430, 480, 530, 580};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Segoe UI", Font.BOLD, 15));
            labels[i].setBounds(50, yPositions[i], 200, 30);
            labels[i].setForeground(Color.BLACK);
            add(labels[i]);
        }

        nameTextField = new JTextField();
        nameTextField.setBounds(250, 130, 520, 30);
        nameTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(nameTextField);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(250, 180, 520, 30);
        phoneTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(phoneTextField);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(250, 230, 520, 30);
        add(dateChooser);

        male = new JRadioButton("Male");
        male.setBounds(250, 280, 100, 30);
        male.setBackground(Color.WHITE);
        male.setFont(new Font("Segoe UI", Font.BOLD, 15));

        female = new JRadioButton("Female");
        female.setBounds(370, 280, 100, 30);
        female.setBackground(Color.WHITE);
        female.setFont(new Font("Segoe UI", Font.BOLD, 15));

        other = new JRadioButton("Other");
        other.setBounds(490, 280, 100, 30);
        other.setBackground(Color.WHITE);
        other.setFont(new Font("Segoe UI", Font.BOLD, 15));

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);
        add(male);
        add(female);
        add(other);

        emailTextField = new JTextField();
        emailTextField.setBounds(250, 330, 520, 30);
        emailTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(emailTextField);

        emergencyContactTextField = new JTextField();
        emergencyContactTextField.setBounds(250, 380, 520, 30);
        emergencyContactTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(emergencyContactTextField);

        addressTextField = new JTextField();
        addressTextField.setBounds(250, 430, 520, 30);
        addressTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(addressTextField);

        cityTextField = new JTextField();
        cityTextField.setBounds(250, 480, 520, 30);
        cityTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(cityTextField);

        stateTextField = new JTextField();
        stateTextField.setBounds(250, 530, 520, 30);
        stateTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(stateTextField);

        pincodeTextField = new JTextField();
        pincodeTextField.setBounds(250, 580, 520, 30);
        pincodeTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(pincodeTextField);

        next = new JButton("Next");
        next.setBounds(650, 630, 100, 30);
        next.setBackground(new Color(255, 107, 53));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(next);
        next.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String formNo = "" + ran;
        String name = nameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "Other");
        String email = emailTextField.getText().trim();
        String emergencyContact = emergencyContactTextField.getText().trim();
        String address = addressTextField.getText().trim();
        String city = cityTextField.getText().trim();
        String state = stateTextField.getText().trim();
        String pincode = pincodeTextField.getText().trim();

        // Validations
        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name and Address are required.");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit phone number.");
            return;
        }

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
            return;
        }

        if (!emergencyContact.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit emergency contact.");
            return;
        }

        if (!pincode.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid 6-digit pincode.");
            return;
        }

        try {
            Conn c = new Conn();
            if (c.connection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return;
            }

            String query = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, formNo);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, dob);
            pstmt.setString(5, gender);
            pstmt.setString(6, email);
            pstmt.setString(7, emergencyContact);
            pstmt.setString(8, address);
            pstmt.setString(9, city);
            pstmt.setString(10, state);
            pstmt.setString(11, pincode);

            pstmt.executeUpdate();
            setVisible(false);
            new SignUpTwo(formNo).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
