package banking_system.repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Transaction extends JFrame implements ActionListener {

    JButton deposit, withdrawal, fastcash, miniStatement, pinChange, balanceEnquiry, exit;
    String pinNo;
    Transaction(String pinNo)
    {
        this.pinNo =pinNo;
        setLayout(null);

        //frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/banksystem.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300,0);
        setTitle("TRANSACTIONS");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set frame size to accommodate the image plus title bar
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text =new JLabel("Please select your Transaction");
        text.setBounds(210,210,700,35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Arial",Font.BOLD,15));
        image.add(text);

         deposit= new JButton("Deposit");
        deposit.setBounds(170,290,150,25);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawal= new JButton("Cash Withdraw");
        withdrawal.setBounds(330,290,150,25);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

         fastcash= new JButton("Fast Cash");
        fastcash.setBounds(170,320,150,25);
        fastcash.addActionListener(this);
        image.add(fastcash);

        miniStatement= new JButton("Mini Statement");
        miniStatement.setBounds(330,320,150,25);
        miniStatement.addActionListener(this);
        image.add(miniStatement);

         pinChange= new JButton("Pin Change");
        pinChange.setBounds(170,350,150,25);
        pinChange.addActionListener(this);
        image.add(pinChange);

         balanceEnquiry= new JButton("Balance Enquiry");
        balanceEnquiry.setBounds(330,350,150,25);
        balanceEnquiry.addActionListener(this);
        image.add(balanceEnquiry);

        exit= new JButton("Exit");
        exit.setBounds(170,379,150,24);
        exit.addActionListener(this);
        image.add(exit);

         setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae)
    {
        if (ae.getSource()==exit)
        {
            System.exit(0);
        } else if (ae.getSource()==deposit) {
            setVisible(false);
            new Deposit(pinNo).setVisible(true);
        } else if (ae.getSource()==withdrawal) {
            setVisible(false);
            new Withdrawal(pinNo).setVisible(true);
        } else if (ae.getSource()==fastcash) {
            setVisible(false);
            new FastCash(pinNo).setVisible(true);
        } else if (ae.getSource()==pinChange) {
            setVisible(false);
            new PinChange(pinNo).setVisible(true);
        } else if (ae.getSource()==balanceEnquiry) {
            setVisible(false);
            new BalanceEnquiry(pinNo).setVisible(true);
        } else if (ae.getSource()==miniStatement) {
            new MiniStatement(pinNo).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Transaction("");
    }
}