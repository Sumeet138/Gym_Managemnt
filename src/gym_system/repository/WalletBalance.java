package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WalletBalance extends JFrame implements ActionListener {
    String memberId;
    JButton back;

    WalletBalance(String memberId) {
        this.memberId = memberId;

        // Frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/gym_background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300, 0);
        setTitle("WALLET BALANCE");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 737);
        setBackground(Color.WHITE);

        int totalCredits = 0;
        int totalDebits = 0;

        Conn conn = new Conn();
        try {
            String query = "SELECT SUM(CASE WHEN transaction_type = 'Credit' THEN amount ELSE 0 END) AS total_credits, " +
                    "SUM(CASE WHEN transaction_type = 'Debit' THEN amount ELSE 0 END) AS total_debits " +
                    "FROM payments WHERE member_id = ?";
            PreparedStatement pstmt = conn.connection.prepareStatement(query);
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCredits = rs.getInt("total_credits");
                totalDebits = rs.getInt("total_debits");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int balance = totalCredits - totalDebits;

        JLabel text = new JLabel("Your Wallet Balance: Rs. " + balance);
        text.setBounds(150, 210, 500, 40);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Segoe UI", Font.BOLD, 28));
        image.add(text);

        JLabel creditsLabel = new JLabel("Total Credits: Rs. " + totalCredits);
        creditsLabel.setBounds(250, 280, 300, 25);
        creditsLabel.setForeground(new Color(0, 128, 0));
        creditsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        image.add(creditsLabel);

        JLabel debitsLabel = new JLabel("Total Debits: Rs. " + totalDebits);
        debitsLabel.setBounds(250, 310, 300, 25);
        debitsLabel.setForeground(new Color(204, 0, 0));
        debitsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        image.add(debitsLabel);

        back = new JButton("Back");
        back.setBounds(330, 380, 120, 30);
        back.setBackground(new Color(255, 165, 0));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.addActionListener(this);
        image.add(back);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Dashboard(memberId).setVisible(true);
    }

    public static void main(String[] args) {
        new WalletBalance("");
    }
}
