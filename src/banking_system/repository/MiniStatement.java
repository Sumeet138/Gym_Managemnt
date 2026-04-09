package banking_system.repository;
import  java.sql.*;
import javax.swing.*;
import java.awt.*;

public class MiniStatement extends JFrame {
    String pinNo;
    MiniStatement(String pinNo)
    {
        this.pinNo = pinNo;
        setLayout(null);
        setLocation(20,20);
        setTitle("MINI STATEMENT");
        setResizable(false);
        setSize(400, 600);
        getContentPane().setBackground(Color.WHITE);

        JLabel bankText = new JLabel("SILVER VALLEY BANK");
        bankText.setBounds(100,20,300,30);
        bankText.setFont(new Font("Railway",Font.BOLD,16));
        bankText.setForeground(Color.BLACK);
        add(bankText);

        JLabel card = new JLabel();
        card.setBounds(20,60,350,20);
        card.setFont(new Font("System",Font.BOLD,12));
        card.setForeground(Color.BLACK);
        add(card);

        JLabel mini = new JLabel();
        mini.setBounds(20,100,350,400);
        mini.setFont(new Font("System",Font.BOLD,12));
        mini.setForeground(Color.BLACK);
        mini.setVerticalAlignment(JLabel.TOP);
        add(mini);

        JLabel balance = new JLabel();
        balance.setBounds(20,520,350,20);
        balance.setFont(new Font("System",Font.BOLD,12));
        balance.setForeground(Color.BLACK);
        add(balance);

        try
        {
            Conn conn = new Conn();
            ResultSet rs = conn.statement.executeQuery("SELECT * FROM login WHERE pin = '"+pinNo+"'" );
            while(rs.next())
            {
                card.setText("Card Number: "+rs.getString("card_no").substring(0,4) +"XXXXXXXX"+rs.getString("card_no").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try
        {
            Conn conn = new Conn();
            int totalBalance = 0;
            ResultSet rs = conn.statement.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNo+"'");
            String miniText = "<html>";
            while(rs.next())
            {
                miniText += rs.getString("date") + "&nbsp;&nbsp;&nbsp;" + rs.getString("transaction_type") + "&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br>";

                if(rs.getString("transaction_type").equals("Deposit"))
                {
                    totalBalance += Integer.parseInt(rs.getString("amount"));
                }
                else
                {
                    totalBalance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            miniText += "</html>";
            mini.setText(miniText);
            balance.setText("Your Account balance: Rs. "+totalBalance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}