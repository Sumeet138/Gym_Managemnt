package banking_system.repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
    String pinNo;
    JTextField amtTextField;
    JButton exit, withdraw , rs100,rs500,rs1000, rs2000,rs5000, rs10000;
    FastCash(String pinNo)
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
        setTitle("FAST CASH");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set frame size to accommodate the image plus title bar
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel text =new JLabel("Withdraw the Amount you want : ");
        text.setBounds(190,210,700,35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System",Font.BOLD,15));
        image.add(text);

        rs100= new JButton("Rs. 100");
        rs100.setBounds(170,290,150,25);
        rs100.addActionListener(this);
        image.add(rs100);

        rs500= new JButton("Rs. 500");
        rs500.setBounds(330,290,150,25);
        rs500.addActionListener(this);
        image.add(rs500);

        rs1000= new JButton("Rs. 1000");
        rs1000.setBounds(170,320,150,25);
        rs1000.addActionListener(this);
        image.add(rs1000);

        rs2000= new JButton("Rs. 2000");
        rs2000.setBounds(330,320,150,25);
        rs2000.addActionListener(this);
        image.add(rs2000);

        rs5000= new JButton("Rs. 5000");
        rs5000.setBounds(170,350,150,25);
        rs5000.addActionListener(this);
        image.add(rs5000);

        rs10000= new JButton("Rs. 10000");
        rs10000.setBounds(330,350,150,25);
        rs10000.addActionListener(this);
        image.add(rs10000);

        exit= new JButton("Exit");
        exit.setBounds(330,379,150,24);
        exit.addActionListener(this);
        image.add(exit);

        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== exit)
        {
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }
        else
        {
            //get the written amount on the button click
            String amount = ((JButton)ae.getSource()).getText().substring(4);
            Conn conn = new Conn();
            try
            {
                // to check balance
                ResultSet rs = conn.statement.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNo+"'");
                int balance = 0;
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

                //if exit button is not clicked and the balance is less than that of amount

                if(ae.getSource()!=exit && balance < Integer.parseInt(amount))
                {
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }

                //but if the there is sufficient balance then
                Date date = new Date();
                String query = "INSERT INTO bank VALUES ('"+pinNo+"','"+date+"','Withdraw','"+amount+"')";
                conn.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs. "+amount+" debited successfully");
                setVisible(false);
                new Transaction(pinNo).setVisible(true);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args)
    {
        new FastCash("");
    }
}