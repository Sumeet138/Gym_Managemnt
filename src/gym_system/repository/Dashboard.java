package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class Dashboard extends JFrame implements ActionListener {

    JButton addFunds, requestRefund, quickBook, paymentHistory, changePassword, walletBalance, viewProfile, exit;
    String memberId;

    Dashboard(String memberId) {
        this.memberId = memberId;
        setLayout(null);

        // Try to load background, fallback to plain color
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/gym_background.jpg"));
            Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0, 0, 780, 737);
            add(image);
        } catch (Exception e) {
            // Background not available, use solid color
            getContentPane().setBackground(new Color(240, 240, 240));
        }

        setLocation(300, 0);
        setTitle("FITZONE - MEMBER DASHBOARD");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel welcomeText = new JLabel("Welcome to FitZone - Select Your Action");
        welcomeText.setBounds(200, 210, 500, 35);
        welcomeText.setForeground(Color.BLACK);
        welcomeText.setFont(new Font("Segoe UI", Font.BOLD, 18));
        image.add(welcomeText);

        JLabel memberIdLabel = new JLabel("Member ID: " + memberId);
        memberIdLabel.setBounds(200, 245, 300, 25);
        memberIdLabel.setForeground(new Color(80, 80, 80));
        memberIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        image.add(memberIdLabel);

        addFunds = new JButton("Add Funds");
        addFunds.setBounds(170, 290, 150, 25);
        addFunds.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addFunds.addActionListener(this);
        image.add(addFunds);

        requestRefund = new JButton("Request Refund");
        requestRefund.setBounds(330, 290, 150, 25);
        requestRefund.setFont(new Font("Segoe UI", Font.BOLD, 13));
        requestRefund.addActionListener(this);
        image.add(requestRefund);

        quickBook = new JButton("Quick Book Class");
        quickBook.setBounds(170, 320, 150, 25);
        quickBook.setFont(new Font("Segoe UI", Font.BOLD, 13));
        quickBook.addActionListener(this);
        image.add(quickBook);

        paymentHistory = new JButton("Payment History");
        paymentHistory.setBounds(330, 320, 150, 25);
        paymentHistory.setFont(new Font("Segoe UI", Font.BOLD, 13));
        paymentHistory.addActionListener(this);
        image.add(paymentHistory);

        changePassword = new JButton("Change Password");
        changePassword.setBounds(170, 350, 150, 25);
        changePassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        changePassword.addActionListener(this);
        image.add(changePassword);

        walletBalance = new JButton("Wallet Balance");
        walletBalance.setBounds(330, 350, 150, 25);
        walletBalance.setFont(new Font("Segoe UI", Font.BOLD, 13));
        walletBalance.addActionListener(this);
        image.add(walletBalance);

        exit = new JButton("Exit");
        exit.setBounds(170, 379, 150, 24);
        exit.setFont(new Font("Segoe UI", Font.BOLD, 13));
        exit.addActionListener(this);
        image.add(exit);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == addFunds) {
            setVisible(false);
            new AddFunds(memberId).setVisible(true);
        } else if (ae.getSource() == requestRefund) {
            setVisible(false);
            new RequestRefund(memberId).setVisible(true);
        } else if (ae.getSource() == quickBook) {
            setVisible(false);
            new QuickBook(memberId).setVisible(true);
        } else if (ae.getSource() == paymentHistory) {
            new PaymentHistory(memberId).setVisible(true);
        } else if (ae.getSource() == changePassword) {
            setVisible(false);
            new ChangePassword(memberId).setVisible(true);
        } else if (ae.getSource() == walletBalance) {
            setVisible(false);
            new WalletBalance(memberId).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Dashboard("");
    }
}
