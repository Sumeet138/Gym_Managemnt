package gym_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class QuickBook extends JFrame implements ActionListener {
    String memberId;
    JButton exit, yoga, zumba, hiit, personalTraining, crossFit, cardio;

    QuickBook(String memberId) {
        this.memberId = memberId;

        // Frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/gym_background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300, 0);
        setTitle("QUICK BOOK CLASS");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text = new JLabel("Select a class to book:");
        text.setBounds(190, 210, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Segoe UI", Font.BOLD, 18));
        image.add(text);

        // Row 1
        yoga = new JButton("Yoga - ₹300");
        yoga.setBounds(170, 290, 200, 30);
        yoga.setBackground(new Color(255, 165, 0));
        yoga.setForeground(Color.WHITE);
        yoga.setFont(new Font("Segoe UI", Font.BOLD, 14));
        yoga.setFocusPainted(false);
        yoga.addActionListener(this);
        image.add(yoga);

        zumba = new JButton("Zumba - ₹400");
        zumba.setBounds(410, 290, 200, 30);
        zumba.setBackground(new Color(255, 165, 0));
        zumba.setForeground(Color.WHITE);
        zumba.setFont(new Font("Segoe UI", Font.BOLD, 14));
        zumba.setFocusPainted(false);
        zumba.addActionListener(this);
        image.add(zumba);

        // Row 2
        hiit = new JButton("HIIT Workout - ₹500");
        hiit.setBounds(170, 330, 200, 30);
        hiit.setBackground(new Color(255, 165, 0));
        hiit.setForeground(Color.WHITE);
        hiit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        hiit.setFocusPainted(false);
        hiit.addActionListener(this);
        image.add(hiit);

        personalTraining = new JButton("Personal Training - ₹600");
        personalTraining.setBounds(410, 330, 200, 30);
        personalTraining.setBackground(new Color(255, 165, 0));
        personalTraining.setForeground(Color.WHITE);
        personalTraining.setFont(new Font("Segoe UI", Font.BOLD, 14));
        personalTraining.setFocusPainted(false);
        personalTraining.addActionListener(this);
        image.add(personalTraining);

        // Row 3
        crossFit = new JButton("CrossFit - ₹350");
        crossFit.setBounds(170, 370, 200, 30);
        crossFit.setBackground(new Color(255, 165, 0));
        crossFit.setForeground(Color.WHITE);
        crossFit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        crossFit.setFocusPainted(false);
        crossFit.addActionListener(this);
        image.add(crossFit);

        cardio = new JButton("Cardio Session - ₹250");
        cardio.setBounds(410, 370, 200, 30);
        cardio.setBackground(new Color(255, 165, 0));
        cardio.setForeground(Color.WHITE);
        cardio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cardio.setFocusPainted(false);
        cardio.addActionListener(this);
        image.add(cardio);

        // Back button
        exit = new JButton("Back");
        exit.setBounds(330, 430, 120, 30);
        exit.setBackground(new Color(255, 165, 0));
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exit.setFocusPainted(false);
        exit.addActionListener(this);
        image.add(exit);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new Dashboard(memberId).setVisible(true);
        } else {
            String buttonText = ((JButton) ae.getSource()).getText();

            // Parse class name and amount from button text
            // Format: "ClassName - ₹Amount"
            String[] parts = buttonText.split("- ₹");
            String classType = parts[0].trim();
            int classAmount = Integer.parseInt(parts[1].trim());

            Conn conn = new Conn();
            try {
                // Calculate wallet balance using PreparedStatement
                String balanceQuery = "SELECT SUM(CASE WHEN transaction_type = 'Credit' THEN amount ELSE 0 END) AS total_credits, " +
                        "SUM(CASE WHEN transaction_type = 'Debit' THEN amount ELSE 0 END) AS total_debits " +
                        "FROM payments WHERE member_id = ?";
                PreparedStatement pstmt = conn.connection.prepareStatement(balanceQuery);
                pstmt.setString(1, memberId);
                ResultSet rs = pstmt.executeQuery();

                int balance = 0;
                if (rs.next()) {
                    int credits = rs.getInt("total_credits");
                    int debits = rs.getInt("total_debits");
                    balance = credits - debits;
                }

                if (balance < classAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Wallet Balance. Please add funds first.");
                    return;
                }

                // Insert booking using PreparedStatement
                Date bookingDate = new Date();
                String bookingQuery = "INSERT INTO bookings (member_id, booking_date, class_type, amount, status) VALUES (?, ?, ?, ?, 'Confirmed')";
                PreparedStatement bookingStmt = conn.connection.prepareStatement(bookingQuery);
                bookingStmt.setString(1, memberId);
                bookingStmt.setDate(2, new java.sql.Date(bookingDate.getTime()));
                bookingStmt.setString(3, classType);
                bookingStmt.setInt(4, classAmount);
                bookingStmt.executeUpdate();

                // Insert payment record using PreparedStatement
                String paymentQuery = "INSERT INTO payments (member_id, transaction_date, transaction_type, description, amount) VALUES (?, ?, 'Debit', ?, ?)";
                PreparedStatement paymentStmt = conn.connection.prepareStatement(paymentQuery);
                paymentStmt.setString(1, memberId);
                paymentStmt.setDate(2, new java.sql.Date(bookingDate.getTime()));
                paymentStmt.setString(3, "Class Booking: " + classType);
                paymentStmt.setInt(4, classAmount);
                paymentStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Class Booked Successfully!");
                setVisible(false);
                new Dashboard(memberId).setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error booking class: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new QuickBook("");
    }
}
