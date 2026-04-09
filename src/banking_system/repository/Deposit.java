package banking_system.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
JTextField amountTextField;
JButton exit, deposit;
String pinNo;
    Deposit(String pinNo)
    {
        this.pinNo = pinNo;
        setLayout(null);

        //frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/banksystem.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300,0);
        setTitle("DEPOSIT");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set frame size to accommodate the image plus title bar
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text =new JLabel("Enter the amount you want to deposit: ");
        text.setBounds(190,210,700,35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System",Font.BOLD,15));
        image.add(text);

        amountTextField = new JTextField();
        amountTextField.setBounds(170,250,300,25);
        amountTextField.setBackground(Color.lightGray);        // Background color of text field
        amountTextField.setForeground(Color.black);
        amountTextField.setFont(new Font("Arial",Font.BOLD,15));
        image.add(amountTextField);

        deposit= new JButton("Deposit");
        deposit.setBounds(330,348,150,25);
        deposit.addActionListener(this);
        image.add(deposit);

        exit= new JButton("Exit");
        exit.setBounds(330,376,150,24);
        exit.addActionListener(this);
        image.add(exit);

        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed (ActionEvent ae)
    {
        if (ae.getSource()==deposit)
        {
            String deposited_amount = amountTextField.getText();
            Date date = new Date();
            if(deposited_amount.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit.");
            }
            else if(!deposited_amount.matches("\\d+") || Integer.parseInt(deposited_amount) <= 0)
            {
                JOptionPane.showMessageDialog(null,"Please enter a valid positive numeric amount.");
            }
            else {
                try{
                    Conn conn = new Conn();
                    String query = "INSERT INTO bank VALUES('"+pinNo+"','"+date+"','Deposit','"+deposited_amount+"')";
                    //actual deposit
                    conn.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Amount "+deposited_amount+" Deposited Successfully");
                    setVisible(false);
                    new Transaction(pinNo).setVisible(true);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (ae.getSource()==exit) {
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
