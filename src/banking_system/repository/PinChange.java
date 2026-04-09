package banking_system.repository;
import  banking_system.repository.Conn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class PinChange extends JFrame implements ActionListener {
    String pinNo;
    JPasswordField pinTextField, repinTextField;
    JButton change,back;

    PinChange(String pinNo)
    {

        this.pinNo = pinNo;  //takes value from local and set it to global
        //frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/banksystem.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300,0);
        setTitle("PIN CHANGE");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set frame size to accommodate the image plus title bar
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel pinText =new JLabel("CHANGE YOUR PIN: ");
        pinText.setBounds(170,210,700,35);
        pinText.setForeground(Color.BLACK);
        pinText.setFont(new Font("System",Font.BOLD,15));
        image.add(pinText);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(170,250,300,25);
        pinTextField.setBackground(Color.lightGray);        // Background color of text field
        pinTextField.setForeground(Color.BLACK);
        pinTextField.setFont(new Font("Arial",Font.BOLD,15));
        image.add(pinTextField);


        JLabel repinText =new JLabel("RE-ENTER YOUR PIN: ");
        repinText.setBounds(170,290,700,35);
        repinText.setForeground(Color.BLACK);
        repinText.setFont(new Font("System",Font.BOLD,15));
        image.add(repinText);

        repinTextField = new JPasswordField();
        repinTextField.setBounds(170,330,300,25);
        repinTextField.setBackground(Color.lightGray);        // Background color of text field
        repinTextField.setForeground(Color.BLACK);
        repinTextField.setFont(new Font("Arial",Font.BOLD,15));
        image.add(repinTextField);

        change= new JButton("Change");
        change.setBounds(170,370,150,25);
        change.addActionListener(this);
        image.add(change);

        back= new JButton("Exit");
        back.setBounds(330,370,150,24);
        back.addActionListener(this);
        image.add(back);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource()==change) {
            try {
                String newPin = pinTextField.getText();
                String rePin = repinTextField.getText();

                if (newPin.equals("") || rePin.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter a PIN in both fields.");
                    return;
                }
                if (newPin.length() != 4 || !newPin.matches("\\d{4}"))
                {
                    JOptionPane.showMessageDialog(null,"PIN must be exactly 4 digits.");
                    return;
                }
                if (!newPin.equals(rePin)) {
                    JOptionPane.showMessageDialog(null, "Entered PINs do not match.");
                    return;
                }
                Conn conn = new Conn();

                //update pin in all table that uses pin
                String query = "UPDATE bank SET pin = '"+rePin+"' WHERE pin = '"+pinNo+"'";
                String query1 = "UPDATE login SET pin = '"+rePin+"' WHERE pin = '"+pinNo+"'";
                String query2 = "UPDATE signUpThree SET pin = '"+rePin+"' WHERE pin = '"+pinNo+"'";

                conn.statement.executeUpdate(query);
                conn.statement.executeUpdate(query1);
                conn.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Pin Changed Successfully.");
                setVisible(false);

                //now put updated pin
                new Transaction(rePin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else
        {
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
