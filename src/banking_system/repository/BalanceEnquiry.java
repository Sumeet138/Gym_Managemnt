package banking_system.repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class BalanceEnquiry extends JFrame implements ActionListener{
        String pinNo;
        JButton back;
    BalanceEnquiry(String pinNo)
    {
     this.pinNo = pinNo;
        //frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/banksystem.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300,0);
        setTitle("BALANCE ENQUIRY");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set frame size to accommodate the image plus title bar
        setSize(780, 737);
        setBackground(Color.WHITE);

        back= new JButton("Exit");
        back.setBounds(330,370,150,24);
        back.addActionListener(this);
        image.add(back);

        Conn con = new Conn();
        int balance = 0;

        try
        {
            ResultSet rs = con.statement.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNo+"'");
            while(rs.next())
            {
                if(rs.getString("transaction_type").equals("Deposit"))
                {
                    balance += Integer.parseInt(rs.getString("amount"));
                }
                else
                {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JLabel text = new JLabel("Your Account Balance : Rs. "+ balance);
        text.setBounds(170,210,300,25);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Arial",Font.BOLD,15));
        image.add(text);

        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Transaction(pinNo).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}