package banking_system.repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.ResultSet;

public class Withdrawal extends JFrame implements ActionListener {
    JTextField amtTextField;
    JButton exit, withdraw;
    String pinNo;

    Withdrawal(String pinNo)
    {
        this.pinNo= pinNo;
        //frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/banksystem.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300,0);
        setTitle("WITHDRAWAL");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set frame size to accommodate the image plus title bar
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text =new JLabel("Enter the amount you want to withdraw: ");
        text.setBounds(190,210,700,35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System",Font.BOLD,15));
        image.add(text);

        amtTextField = new JTextField();
        amtTextField.setBounds(170,250,300,25);
        amtTextField.setBackground(Color.lightGray);        // Background color of text field
        amtTextField.setForeground(Color.black);
        amtTextField.setFont(new Font("Arial",Font.BOLD,15));
        image.add(amtTextField);

        withdraw= new JButton("Withdraw");
        withdraw.setBounds(330,348,150,25);
        withdraw.addActionListener(this);
        image.add(withdraw);

        exit= new JButton("Exit");
        exit.setBounds(330,376,150,24);
        exit.addActionListener(this);
        image.add(exit);

        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource()==withdraw)
        {
            String withdraw_amount = amtTextField.getText();
            Date date = new Date();
            if(withdraw_amount.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to withdraw.");
            }
            else if(!withdraw_amount.matches("\\d+") || Integer.parseInt(withdraw_amount) <= 0)
            {
                JOptionPane.showMessageDialog(null,"Please enter a valid positive numeric amount.");
            }
            else {
                try{
                    Conn conn = new Conn();
                    // Check available balance before withdrawing
                    ResultSet rs = conn.statement.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNo+"'");
                    int balance = 0;
                    while(rs.next()) {
                        if(rs.getString("transaction_type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if(balance < Integer.parseInt(withdraw_amount)) {
                        JOptionPane.showMessageDialog(null,"Insufficient Balance. Available: Rs. "+balance);
                        return;
                    }
                    String query = "INSERT INTO bank VALUES('"+pinNo+"','"+date+"','Withdraw','"+withdraw_amount+"')";
                    conn.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Amount Rs. "+withdraw_amount+" Withdrawn Successfully");
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
        new Withdrawal("");
    }
}
