package gym_system.repository;
import gym_system.repository.Conn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChangePassword extends JFrame implements ActionListener {
    String memberId;
    JPasswordField pinTextField, repinTextField;
    JButton update, back;

    ChangePassword(String memberId)
    {

        this.memberId = memberId;
        //frame setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/gym_background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(780, 737, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 780, 737);
        add(image);

        setLocation(300,0);
        setTitle("CHANGE PASSWORD");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 737);
        setBackground(Color.WHITE);

        JLabel pinText =new JLabel("Enter New PIN (4 digits):");
        pinText.setBounds(170,210,700,35);
        pinText.setForeground(Color.BLACK);
        pinText.setFont(new Font("Segoe UI",Font.BOLD,15));
        image.add(pinText);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(170,250,300,25);
        pinTextField.setBackground(Color.lightGray);
        pinTextField.setForeground(Color.BLACK);
        pinTextField.setFont(new Font("Segoe UI",Font.BOLD,15));
        image.add(pinTextField);


        JLabel repinText =new JLabel("Re-Enter PIN:");
        repinText.setBounds(170,290,700,35);
        repinText.setForeground(Color.BLACK);
        repinText.setFont(new Font("Segoe UI",Font.BOLD,15));
        image.add(repinText);

        repinTextField = new JPasswordField();
        repinTextField.setBounds(170,330,300,25);
        repinTextField.setBackground(Color.lightGray);
        repinTextField.setForeground(Color.BLACK);
        repinTextField.setFont(new Font("Segoe UI",Font.BOLD,15));
        image.add(repinTextField);

        update= new JButton("Update");
        update.setBounds(170,370,150,25);
        update.setFont(new Font("Segoe UI",Font.BOLD,13));
        update.addActionListener(this);
        image.add(update);

        back= new JButton("Back");
        back.setBounds(330,370,150,24);
        back.setFont(new Font("Segoe UI",Font.BOLD,13));
        back.addActionListener(this);
        image.add(back);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource()==update) {
            try {
                String newPin = pinTextField.getText();
                String confirmPin = repinTextField.getText();

                if (newPin.equals("") || confirmPin.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a PIN");
                    return;
                }

                if (!newPin.equals(confirmPin))
                {
                    JOptionPane.showMessageDialog(null, "PINs do not match");
                    return;
                }

                if (newPin.length() != 4 || !newPin.matches("\\d{4}"))
                {
                    JOptionPane.showMessageDialog(null, "PIN must be exactly 4 digits");
                    return;
                }

                Conn conn = new Conn();
                Connection connection = conn.connection;
                connection.setAutoCommit(false);

                try {
                    PreparedStatement pstmt1 = connection.prepareStatement("UPDATE login SET pin = ? WHERE member_id = ?");
                    pstmt1.setString(1, confirmPin);
                    pstmt1.setString(2, memberId);
                    pstmt1.executeUpdate();

                    PreparedStatement pstmt2 = connection.prepareStatement("UPDATE membership SET pin = ? WHERE member_id = ?");
                    pstmt2.setString(1, confirmPin);
                    pstmt2.setString(2, memberId);
                    pstmt2.executeUpdate();

                    connection.commit();
                    JOptionPane.showMessageDialog(null, "Password Updated Successfully");
                    setVisible(false);
                    new Dashboard(memberId).setVisible(true);
                } catch (Exception e) {
                    connection.rollback();
                    throw e;
                } finally {
                    connection.setAutoCommit(true);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            setVisible(false);
            new Dashboard(memberId).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new ChangePassword("").setVisible(true);
    }
}
