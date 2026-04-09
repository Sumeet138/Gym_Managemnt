package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AddFunds extends JFrame implements ActionListener {
    JTextField amountTextField;
    JButton addFunds, back;
    String memberId;

    AddFunds(String memberId) {
        this.memberId = memberId;
        setLayout(null);

        // Frame setup with background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/gym_background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300, 0);
        setTitle("ADD FUNDS");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text = new JLabel("Enter amount to add to your wallet:");
        text.setBounds(190, 210, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Segoe UI", Font.BOLD, 15));
        image.add(text);

        amountTextField = new JTextField();
        amountTextField.setBounds(170, 250, 300, 25);
        amountTextField.setBackground(Color.lightGray);
        amountTextField.setForeground(Color.black);
        amountTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        image.add(amountTextField);

        addFunds = new JButton("Add Funds");
        addFunds.setBounds(330, 348, 150, 25);
        addFunds.setBackground(new Color(255, 107, 53));
        addFunds.setForeground(Color.WHITE);
        addFunds.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addFunds.setFocusPainted(false);
        addFunds.addActionListener(this);
        image.add(addFunds);

        back = new JButton("Back");
        back.setBounds(330, 376, 150, 24);
        back.setFont(new Font("Segoe UI", Font.BOLD, 13));
        back.addActionListener(this);
        image.add(back);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addFunds) {
            String amountStr = amountTextField.getText();
            Date date = new Date();

            if (amountStr.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount to add.");
            } else {
                try {
                    int amount = Integer.parseInt(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Amount must be positive.");
                    } else if (amount > 50000) {
                        JOptionPane.showMessageDialog(null, "Maximum limit is Rs.50,000 per transaction.");
                    } else {
                        Conn conn = new Conn();
                        if (conn.connection == null) {
                            JOptionPane.showMessageDialog(null, "Database connection failed. Please try again.");
                            return;
                        }
                        String query = "INSERT INTO payments (member_id, transaction_date, transaction_type, description, amount) VALUES (?, ?, ?, ?, ?)";
                        java.sql.PreparedStatement pstmt = conn.connection.prepareStatement(query);
                        pstmt.setString(1, memberId);
                        pstmt.setDate(2, new java.sql.Date(date.getTime()));
                        pstmt.setString(3, "Credit");
                        pstmt.setString(4, "Wallet Top-Up");
                        pstmt.setInt(5, amount);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Rs." + amount + " Added to Wallet Successfully");
                        setVisible(false);
                        new Dashboard(memberId).setVisible(true);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Dashboard(memberId).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AddFunds("");
    }
}
