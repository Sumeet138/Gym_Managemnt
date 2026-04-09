package banking_system.repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;

public class SignUp extends JFrame implements ActionListener {
    long ran;
    JTextField nameTextField, fatherTextField, birthDateTextField, emailTextField, cityTextField, stateTextField, pincodeTextField, addressTextField;
    JButton next;
    JRadioButton married, unmarried, other, male, female;
    JDateChooser dateChooser;


    SignUp ()
    {
        //Frame Layout and all
        setLayout(null);
        setSize(850,750);
        getContentPane().setBackground(Color.WHITE);
        setLocation(285,5);
        setTitle("NEW ACCOUNT APPLICATION FORM");
        setResizable(false);

        //heading using JLabel

        //to get random form no
        Random random = new Random();         //create Random class obj of package util
        ran = Math.abs((random.nextLong() % 9000L)+1000L);  //apply constraint to obj and store in var

        JLabel textSignup = new JLabel("NEW APPLICATION FORM: "+ran);    //print var
        textSignup.setFont(new Font("Railway", Font.BOLD,28));
        textSignup.setForeground(Color.black);
        textSignup.setBounds(200,30,700,30);
        add(textSignup);

        JLabel page1 = new JLabel("Page 1: Personal Details");    //print var
        page1.setFont(new Font("Arial", Font.BOLD,20));
        page1.setForeground(Color.black);
        page1.setBounds(290,70,700,30);
        add(page1);

        JLabel name = new JLabel("Full Name: ");
        name.setFont(new Font("Arial",Font.BOLD,15));
        name.setBounds(50,130,300,30);
        name.setForeground(Color.BLACK);
        add(name);

        JLabel fatherName = new JLabel("Father's Name: ");
        fatherName.setFont(new Font("Arial",Font.BOLD,15));
        fatherName.setBounds(50,180,300,30);
        fatherName.setForeground(Color.BLACK);
        add(fatherName);

        JLabel birthDate = new JLabel("Date Of Birth: ");
        birthDate.setFont(new Font("Arial",Font.BOLD,15));
        birthDate.setBounds(50,230,300,30);
        birthDate.setForeground(Color.BLACK);
        add(birthDate);

        JLabel gender = new JLabel("Gender: ");
        gender.setFont(new Font("Arial",Font.BOLD,15));
        gender.setBounds(50,280,300,30);
        gender.setForeground(Color.BLACK);
        add(gender);

        JLabel email = new JLabel("Email Address: ");
        email.setFont(new Font("Arial",Font.BOLD,15));
        email.setBounds(50,330,300,30);
        email.setForeground(Color.BLACK);
        add(email);

        JLabel maritalStatus = new JLabel("Marital Status: ");
        maritalStatus.setFont(new Font("Arial",Font.BOLD,15));
        maritalStatus.setBounds(50,380,300,30);
        maritalStatus.setForeground(Color.BLACK);
        add(maritalStatus);

        JLabel address = new JLabel("Address: ");
        address.setFont(new Font("Arial",Font.BOLD,15));
        address.setBounds(50,430,300,30);
        address.setForeground(Color.BLACK);
        add(address);

        JLabel city = new JLabel("City: ");
        city.setFont(new Font("Arial",Font.BOLD,15));
        city.setBounds(50,480,300,30);
        city.setForeground(Color.BLACK);
        add(city);

        JLabel state = new JLabel("State: ");
        state.setFont(new Font("Arial",Font.BOLD,15));
        state.setBounds(50,530,300,30);
        state.setForeground(Color.BLACK);
        add(state);

        JLabel pinCode = new JLabel("Pin Code: ");
        pinCode.setFont(new Font("Arial",Font.BOLD,15));
        pinCode.setBounds(50,580,300,30);
        pinCode.setForeground(Color.BLACK);
        add(pinCode);

        //set text field
         nameTextField = new JTextField();
        nameTextField.setBounds(200,130,520,30);
        nameTextField.setBackground(Color.white);        // Background color of text field
        nameTextField.setForeground(Color.black);
        nameTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(nameTextField);

        fatherTextField = new JTextField();
        fatherTextField.setBounds(200,180,520,30);
        fatherTextField.setBackground(Color.white);        // Background color of text field
        fatherTextField.setForeground(Color.black);
        fatherTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(fatherTextField);

        // Date Chooser for Date of Birth
        dateChooser = new JDateChooser();
        dateChooser.setBounds(200,230,520,30);
        dateChooser.setBackground(Color.white);
        dateChooser.setForeground(Color.black);
        dateChooser.setFont(new Font("Arial",Font.BOLD,15));
        add(dateChooser);

        // Gender Radio Buttons
        male = new JRadioButton("Male");
        male.setBounds(200,280,100,30);
        male.setBackground(Color.white);
        male.setFont(new Font("Arial",Font.BOLD,15));

        female = new JRadioButton("Female");
        female.setBounds(320,280,100,30);
        female.setBackground(Color.white);
        female.setFont(new Font("Arial",Font.BOLD,15));

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        add(male);
        add(female);

        emailTextField = new JTextField();
        emailTextField.setBounds(200, 330,520,30);
        emailTextField.setBackground(Color.white);        // Background color of text field
        emailTextField.setForeground(Color.black);
        emailTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(emailTextField);

        // Marital Status Radio Buttons
        married = new JRadioButton("Married");
        married.setBounds(200,380,100,30);
        married.setBackground(Color.white);
        married.setFont(new Font("Arial",Font.BOLD,15));

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(320,380,120,30);
        unmarried.setBackground(Color.white);
        unmarried.setFont(new Font("Arial",Font.BOLD,15));


        other = new JRadioButton("Other");
        other.setBounds(460,380,100,30);
        other.setBackground(Color.white);
        other.setFont(new Font("Arial",Font.BOLD,15));

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);
        add(married);
        add(unmarried);
        add(other);

         addressTextField = new JTextField();
        addressTextField.setBounds(200,430,520,30);
        addressTextField.setBackground(Color.white);        // Background color of text field
        addressTextField.setForeground(Color.black);
        addressTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(addressTextField);

         cityTextField = new JTextField();
        cityTextField.setBounds(200,480,520,30);
        cityTextField.setBackground(Color.white);        // Background color of text field
        cityTextField.setForeground(Color.black);
        cityTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(cityTextField);

        stateTextField = new JTextField();
        stateTextField.setBounds(200,530,520,30);
        stateTextField.setBackground(Color.white);        // Background color of text field
        stateTextField.setForeground(Color.black);
        stateTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(stateTextField);

        pincodeTextField = new JTextField();
        pincodeTextField.setBounds(200,580,520,30);
        pincodeTextField.setBackground(Color.white);        // Background color of text field
        pincodeTextField.setForeground(Color.black);
        pincodeTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(pincodeTextField);

        // Next Button
        next = new JButton("Next");
        next.setBounds(620,630,100,30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial",Font.BOLD,15));
        add(next);
        next.addActionListener(this);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae)
    {
        String formNo =""+ ran;  // long concated in string hence directly converted to string
        String name = nameTextField.getText();
        String fname = fatherTextField.getText();
        String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected())
        {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }
        String email = emailTextField.getText();
        String martial = null;
        if(unmarried.isSelected())
        {
            martial ="Unmarried";
        } else if (married.isSelected()) {
            martial = "Married";
        } else if (other.isSelected()) {
            martial = "Other";
        }
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pincode = pincodeTextField.getText();

        //database entry
        try
        {
         if(name.equals("") || address.equals(""))
         {
             JOptionPane.showMessageDialog(null,"Name and Address is Required");
         }
         else
         {
             //create connection with database and insert values in db
             Conn c = new Conn();
             String query = "INSERT INTO signup VALUES ('"+formNo+"', '"+name+"', '"+fname+"', '"+dob+"', '"+gender+ "', '"+email +"', '" + martial+"', '" + address+"','" + city+"','" + state+"','" + pincode+"')";

             c.statement.executeUpdate(query);

             setVisible(false);

             //create obj of signup-2 to open next frame for that particular form no.
             new SignUpTwo(formNo).setVisible(true);
         }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args)
    {
        new SignUp();
    }
}