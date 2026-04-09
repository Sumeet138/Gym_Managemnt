package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class RequestRefund extends JFrame implements ActionListener {
    JTextField amountTextField;
    JButton requestRefund, back;
    String memberId;
    JLabel balanceLabel;

    RequestRefund(String memberId) {
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
        setTitle("REQUEST REFUND");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text = new JLabel("Enter refund amount:");
        text.setBounds(190, 210, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Segoe UI", Font.BOLD, 15));
        image.add(text);

        // Calculate and display available balance
        double availableBalance = calculateBalance();
        balanceLabel = new JLabel("Available Balance: Rs. " + String.format("%.2f", availableBalance));
        balanceLabel.setBounds(190, 245, 400, 25);
        balanceLabel.setForeground(new Color(80, 80, 80));
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        image.add(balanceLabel);

        amountTextField = new JTextField();
        amountTextField.setBounds(170, 280, 300, 25);
        amountTextField.setBackground(Color.lightGray);
        amountTextField.setForeground(Color.black);
        amountTextField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        image.add(amountTextField);

        requestRefund = new JButton("Request Refund");
        requestRefund.setBounds(330, 348, 150, 25);
        requestRefund.setBackground(new Color(255, 107, 53));
        requestRefund.setForeground(Color.WHITE);
        requestRefund.setFont(new Font("Segoe UI", Font.BOLD, 13));
        requestRefund.setFocusPainted(false);
        requestRefund.addActionListener(this);
        image.add(requestRefund);

        back = new JButton("Back");
        back.setBounds(330, 376, 150, 24);
        back.setFont(new Font("Segoe UI", Font.BOLD, 13));
        back.addActionListener(this);
        image.add(back);

        setUndecorated(true);
        setVisible(true);
    }

    private double calculateBalance() {
        try {
            Conn conn = new Conn();
            if (conn.connection == null) {
                return 0.0;
            }
            String query = "SELECT COALESCE(SUM(CASE WHEN transaction_type='Credit' THEN amount ELSE 0 END), 0) - " +
                           "COALESCE(SUM(CASE WHEN transaction_type='Debit' THEN amount ELSE 0 END), 0) AS balance " +
                           "FROM payments WHERE member_id = ?";
            java.sql.PreparedStatement pstmt = conn.connection.prepareStatement(query);
            pstmt.setString(1, memberId);
            java.sql.ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (Exception e) {
            System.out.println("Error calculating balance: " + e.getMessage());
        }
        return 0.0;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == requestRefund) {
            String amountStr = amountTextField.getText();
            Date date = new Date();

            if (amountStr.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the refund amount.");
            } else {
                try {
                    int amount = Integer.parseInt(amountStr);
                    double availableBalance = calculateBalance();

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Amount must be positive.");
                    } else if (amount > availableBalance) {
                        JOptionPane.showMessageDialog(null, "Insufficient balance. Available: Rs. " + String.format("%.2f", availableBalance));
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
                        pstmt.setString(3, "Debit");
                        pstmt.setString(4, "Refund Request");
                        pstmt.setInt(5, amount);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Rs." + amount + " Refund Requested Successfully");
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
        new RequestRefund("");
    }
}
