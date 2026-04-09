package gym_system.repository;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class PaymentHistory extends JFrame {
    String memberId;
    PaymentHistory(String memberId)
    {
        this.memberId = memberId;
        setLayout(null);
        setLocation(20,20);
        setTitle("PAYMENT HISTORY");
        setResizable(false);
        setSize(400, 600);
        getContentPane().setBackground(Color.WHITE);

        JLabel gymText = new JLabel("FITZONE GYM & FITNESS");
        gymText.setBounds(100,20,200,30);
        gymText.setFont(new Font("Segoe UI",Font.BOLD,16));
        gymText.setForeground(Color.BLACK);
        add(gymText);

        JLabel memberIdLabel = new JLabel();
        memberIdLabel.setBounds(20,60,350,20);
        memberIdLabel.setFont(new Font("Segoe UI",Font.BOLD,12));
        memberIdLabel.setForeground(Color.BLACK);
        add(memberIdLabel);

        JLabel history = new JLabel();
        history.setBounds(20,100,350,400);
        history.setFont(new Font("Segoe UI",Font.BOLD,12));
        history.setForeground(Color.BLACK);
        history.setVerticalAlignment(JLabel.TOP);
        add(history);

        JLabel balance = new JLabel();
        balance.setBounds(20,520,350,20);
        balance.setFont(new Font("Segoe UI",Font.BOLD,12));
        balance.setForeground(Color.BLACK);
        add(balance);

        try
        {
            Conn conn = new Conn();
            PreparedStatement pstmt = conn.connection.prepareStatement("SELECT member_id FROM membership WHERE member_id = ?");
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                String fullId = rs.getString("member_id");
                String maskedId;
                if(fullId.length() >= 8)
                {
                    maskedId = "MEMBER ID: " + fullId.substring(0,4) + "\u2022\u2022\u2022\u2022" + fullId.substring(fullId.length()-4);
                }
                else
                {
                    maskedId = "MEMBER ID: " + fullId;
                }
                memberIdLabel.setText(maskedId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try
        {
            Conn conn = new Conn();
            int totalBalance = 0;
            PreparedStatement pstmt = conn.connection.prepareStatement("SELECT * FROM payments WHERE member_id = ? ORDER BY transaction_date DESC");
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            String historyText = "<html>";
            while(rs.next())
            {
                String date = rs.getString("transaction_date");
                String description = rs.getString("description");
                String amount = rs.getString("amount");
                String type = rs.getString("transaction_type");

                String typeColor = type.equals("Credit") ? "green" : "red";

                historyText += date + "&nbsp;&nbsp;" + description + "&nbsp;&nbsp;Rs." + amount + "&nbsp;&nbsp;<font color='" + typeColor + "'>" + type + "</font><br>";

                if(type.equals("Credit"))
                {
                    totalBalance += Integer.parseInt(amount);
                }
                else
                {
                    totalBalance -= Integer.parseInt(amount);
                }
            }
            historyText += "</html>";
            history.setText(historyText);
            balance.setText("Current Balance: Rs. " + totalBalance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new PaymentHistory("");
    }
}
