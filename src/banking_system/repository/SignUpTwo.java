package banking_system.repository;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignUpTwo extends JFrame implements ActionListener{
    JButton next1;
    JRadioButton seniorYes, seniorNo, accountYes, accountNo;
    JTextField panTextField, aadharTextField;
    JComboBox religionBox, categoryBox, incomeBox,educationBox,occupationBox;
    String formNo;

    SignUpTwo(String formNo)
    {
        this.formNo = formNo;
        setLayout(null);
        setSize(850,750);
        setLocation(285,5);
        setTitle("APPLICATION FORM");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        //add JLable

        JLabel textApplication = new JLabel("Page 2: Additional Details");
        textApplication.setFont(new Font("Arial", Font.BOLD,28));
        textApplication.setForeground(Color.black);
        textApplication.setBounds(240,30,700,40);
        add(textApplication);

        JLabel religion = new JLabel("Religion: ");
        religion.setFont(new Font("Arial",Font.BOLD,15));
        religion.setBounds(50,150,300,30);
        religion.setForeground(Color.BLACK);
        add(religion);

        JLabel category = new JLabel("Category: ");
        category.setFont(new Font("Arial",Font.BOLD,15));
        category.setBounds(50,200,300,30);
        category.setForeground(Color.BLACK);
        add(category);

        JLabel income = new JLabel("Income: ");
        income.setFont(new Font("Arial",Font.BOLD,15));
        income.setBounds(50,250,300,30);
        income.setForeground(Color.BLACK);
        add(income);

        JLabel education = new JLabel("Education Qualification: ");
        education.setFont(new Font("Arial",Font.BOLD,15));
        education.setBounds(50,300,300,30);
        education.setForeground(Color.BLACK);
        add(education);

        JLabel occupation = new JLabel("Occupation: ");
        occupation.setFont(new Font("Arial",Font.BOLD,15));
        occupation.setBounds(50,350,300,30);
        occupation.setForeground(Color.BLACK);
        add(occupation);

        JLabel pan = new JLabel("Pan Number: ");
        pan.setFont(new Font("Arial",Font.BOLD,15));
        pan.setBounds(50,400,300,30);
        pan.setForeground(Color.BLACK);
        add(pan);

        JLabel aadhar = new JLabel("Aadhar Number: ");
        aadhar.setFont(new Font("Arial",Font.BOLD,15));
        aadhar.setBounds(50,450,300,30);
        aadhar.setForeground(Color.BLACK);
        add(aadhar);

        JLabel senior = new JLabel("Senior Citizen: ");
        senior.setFont(new Font("Arial",Font.BOLD,15));
        senior.setBounds(50,500,300,30);
        senior.setForeground(Color.BLACK);
        add(senior);

        JLabel account = new JLabel(" Existing Account: ");
        account.setFont(new Font("Arial",Font.BOLD,15));
        account.setBounds(50,550,300,30);
        account.setForeground(Color.BLACK);
        add(account);


        //textField

        String valReligion [] = {"Hindu","Muslim","Sikh","Jain","Other"};
        religionBox =new JComboBox(valReligion);
        religionBox.setBounds(370,150,400,30);
        religionBox.setBackground(Color.WHITE);
        add(religionBox);

        String valCategory [] = {"Open","OBC","ST","SC","Other"};
        categoryBox =new JComboBox (valCategory);
        categoryBox.setBounds(370,200,400,30);
        categoryBox.setBackground(Color.WHITE);
        add(categoryBox);

        String valIncome [] = {"Salaried","Non-Salary","Service","Business","Other"};
        incomeBox =new JComboBox (valIncome);
        incomeBox .setBounds(370,250,400,30);
        incomeBox .setBackground(Color.WHITE);
        add(incomeBox);

        String valEducation [] = {"Graduate","Higher-Secondary","Secondary","Other"};
        educationBox =new JComboBox (valEducation);
        educationBox.setBounds(370,300,400,30);
        educationBox.setBackground(Color.WHITE);
        add(educationBox);

        String valOccupation [] = {"Business","Service","Government-Service","Other"};
        occupationBox =new JComboBox (valOccupation);
        occupationBox.setBounds(370,350,400,30);
        occupationBox.setBackground(Color.WHITE);
        add(occupationBox);

        panTextField = new JTextField();
        panTextField.setBounds(370,400,400,30);
        panTextField.setFont(new Font("Arial",Font.BOLD,15));
        panTextField.setBackground(Color.WHITE);
        panTextField.setForeground(Color.BLACK);
        add(panTextField);

        aadharTextField = new JTextField();
        aadharTextField.setBounds(370,450,400,30);
        aadharTextField.setFont(new Font("Arial",Font.BOLD,15));
        aadharTextField.setBackground(Color.WHITE);
        aadharTextField.setForeground(Color.BLACK);
        add(aadharTextField);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(370,500,100,30);
        seniorYes.setBackground(Color.white);
        seniorYes.setFont(new Font("Arial",Font.BOLD,15));

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(480,500,100,30);
        seniorNo.setBackground(Color.white);
        seniorNo.setFont(new Font("Arial",Font.BOLD,15));

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);
        add(seniorYes);
        add(seniorNo);

        accountYes = new JRadioButton("Yes");
        accountYes.setBounds(370,550,100,30);
        accountYes.setBackground(Color.white);
        accountYes.setFont(new Font("Arial",Font.BOLD,15));

        accountNo = new JRadioButton("No");
        accountNo.setBounds(480,550,100,30);
        accountNo.setBackground(Color.white);
        accountNo.setFont(new Font("Arial",Font.BOLD,15));

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(accountYes);  // Fixed: changed from seniorGroup to accountGroup
        accountGroup.add(accountNo);   // Fixed: changed from seniorGroup to accountGroup
        add(accountYes);
        add(accountNo);

        // Next Button
        next1 = new JButton("Next");
        next1.setBounds(650,620,100,40);
        next1.setBackground(Color.BLACK);
        next1.setForeground(Color.WHITE);
        next1.setFont(new Font("Arial",Font.BOLD,15));
        add(next1);

        next1.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String religion = (String) religionBox.getSelectedItem();
        String category = (String) categoryBox.getSelectedItem();
        String income = (String) incomeBox.getSelectedItem();
        String education = (String) educationBox.getSelectedItem();
        String occupation = (String) occupationBox.getSelectedItem();
        String pan = panTextField.getText();
        String aadhar = aadharTextField.getText();
        String senior = null;
        if(seniorYes.isSelected())
        {
            senior = "Yes";
        } else if (seniorNo.isSelected()) {
            senior = "No";
        }
        String account = null;
        if(accountYes.isSelected())
        {
            account="Yes";
        } else if (accountNo.isSelected()) {
            account = "No";
        }

        //database connectivity
        try
        {
            if(aadhar.equals("") || pan.equals("") || religion.equals("") || account.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Aadhar No., PAN No, Religion, Existing Account Fields are Required.");
            }
            else {
                Conn c = new Conn();
                String query = "INSERT INTO signuptwo VALUES ('"+formNo+"', '"+religion+"', '"+category+"', '"+income+"', '"
                        +education+ "', '"+occupation +"', '" + pan+"', '" + aadhar+"','" +senior+"','" +account+"')";

                c.statement.executeUpdate(query);
                setVisible(false);
                //create obj of signUp3 to goto frame after clicking next
                new SignUpThree(formNo).setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUpTwo("");
    }
}