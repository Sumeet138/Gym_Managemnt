package banking_system.repository;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SignUpThree extends JFrame implements ActionListener {
    String formNo;
    JButton clear1, submit;
    JRadioButton savingAcc, fixedDeposit, currentAcc, recurringDeposit;
    JCheckBox atmCard, internetBank, mobileBank, emailSMSalert, chequeBook, eStatement ,reqStatement;
    SignUpThree(String formNo)
    {
        this.formNo = formNo;
        setLayout(null);
        setSize(850,750);
        setLocation(285,5);
        setTitle("ACCOUNT DETAILS");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        //add JLable

        JLabel textAccount = new JLabel("Page 3: Account Details");
        textAccount.setFont(new Font("Arial", Font.BOLD,28));
        textAccount.setForeground(Color.black);
        textAccount.setBounds(250,30,700,40);
        add(textAccount);

        JLabel accountType = new JLabel("Account Type:");
        accountType.setFont(new Font("Arial", Font.BOLD,20));
        accountType.setForeground(Color.black);
        accountType.setBounds(50,100,700,40);
        add(accountType);

        savingAcc = new JRadioButton("Saving Account");
        savingAcc.setBounds(90,160,170,30);
        savingAcc.setBackground(Color.white);
        savingAcc.setFont(new Font("Arial",Font.BOLD,15));

        fixedDeposit = new JRadioButton("Fixed Deposit Account");
        fixedDeposit.setBounds(300,160,290,30);
        fixedDeposit.setBackground(Color.white);
        fixedDeposit.setFont(new Font("Arial",Font.BOLD,15));

        currentAcc = new JRadioButton("Current Account");
        currentAcc.setBounds(90,200,170,30);
        currentAcc.setBackground(Color.white);
        currentAcc.setFont(new Font("Arial",Font.BOLD,15));

        recurringDeposit = new JRadioButton("Recurring Deposit Account");
        recurringDeposit.setBounds(300,200,290,30);
        recurringDeposit.setBackground(Color.white);
        recurringDeposit.setFont(new Font("Arial",Font.BOLD,15));

        ButtonGroup accountTypebtn = new ButtonGroup();
        accountTypebtn.add(savingAcc);
        accountTypebtn.add(fixedDeposit);
        accountTypebtn.add(currentAcc);
        accountTypebtn.add(recurringDeposit);
        add(savingAcc);
        add(fixedDeposit);
        add(currentAcc);
        add(recurringDeposit);

        JLabel textCard = new JLabel("Card Number :");
        textCard.setFont(new Font("Arial", Font.BOLD,20));
        textCard.setForeground(Color.black);
        textCard.setBounds(50,250,700,40);
        add(textCard);

        JLabel stm1 =new JLabel("Your 14 Digit Card Number");
        stm1.setFont(new Font("Arial", Font.BOLD,15));
        stm1.setForeground(Color.black);
        stm1.setBounds(50,290,700,40);
        add(stm1);

        JLabel cardNo = new JLabel("XXXX-XXXX-XXXX-2608");
        cardNo.setFont(new Font("Arial", Font.BOLD,30));
        cardNo.setForeground(Color.BLUE);
        cardNo.setBounds(280,250,700,40);
        add(cardNo);

        JLabel textPin = new JLabel("PIN:");
        textPin.setFont(new Font("Arial", Font.BOLD,20));
        textPin.setForeground(Color.black);
        textPin.setBounds(50,340,700,40);
        add(textPin);

        JLabel stm2 =new JLabel("Your 4 Digit PIN");
        stm2.setFont(new Font("Arial", Font.BOLD,15));
        stm2.setForeground(Color.black);
        stm2.setBounds(50,380,700,40);
        add(stm2);

        JLabel pin = new JLabel("XXXX");
        pin.setFont(new Font("Arial", Font.BOLD,30));
        pin.setForeground(Color.BLUE);
        pin.setBounds(280,340,700,40);
        add(pin);

        JLabel services = new JLabel("Services Required :");
        services.setFont(new Font("Arial", Font.BOLD,20));
        services.setForeground(Color.black);
        services.setBounds(50,430,700,40);
        add(services);

        atmCard = new JCheckBox("ATM CARD");
        atmCard.setBounds(50,490,170,30);
        atmCard.setBackground(Color.WHITE);
        atmCard.setFont(new Font("Arial",Font.BOLD,15));
        add(atmCard);

        internetBank = new JCheckBox("Internet Banking");
        internetBank.setBounds(260,490,200,30);
        internetBank.setBackground(Color.white);
        internetBank.setFont(new Font("Arial",Font.BOLD,15));
        add(internetBank);

        mobileBank = new JCheckBox("Mobile Banking");
        mobileBank.setBounds(530,490,170,30);
        mobileBank.setBackground(Color.white);
        mobileBank.setFont(new Font("Arial",Font.BOLD,15));
        add(mobileBank);

        emailSMSalert= new JCheckBox("EMAIL AND SMS Alerts");
        emailSMSalert.setBounds(50,530,200,30);
        emailSMSalert.setBackground(Color.white);
        emailSMSalert.setFont(new Font("Arial",Font.BOLD,15));
        add(emailSMSalert);

        chequeBook =  new JCheckBox("Cheque Book");
        chequeBook.setBounds(260,530,170,30);
        chequeBook.setBackground(Color.white);
        chequeBook.setFont(new Font("Arial",Font.BOLD,15));
        add(chequeBook);

        eStatement = new JCheckBox("E-Statement");
        eStatement.setBounds(530,530,300,30);
        eStatement.setBackground(Color.white);
        eStatement.setFont(new Font("Arial",Font.BOLD,15));
        add(eStatement);

        reqStatement = new JCheckBox("I hearby declares that above entered details are correct to the best of my knowledge");
        reqStatement.setBounds(50, 600,700,30);
        reqStatement.setBackground(Color.PINK);
        reqStatement.setFont(new Font("Arial",Font.BOLD,15));
        add(reqStatement);

        clear1 = new JButton("Cancel");
        clear1.setBounds(530,650,100,30);
        clear1.setBackground(Color.BLACK);
        clear1.setForeground(Color.WHITE);
        clear1.setFont(new Font("Arial",Font.BOLD,15));
        add(clear1);
        clear1.addActionListener(this);

        submit = new JButton("Submit");
        submit.setBounds(650,650,100,30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Arial",Font.BOLD,15));
        add(submit);
        submit.addActionListener(this);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource()==submit) {
            String accountType = null;
            if (savingAcc.isSelected()) {
                accountType = "SavingAccount";
            } else if (fixedDeposit.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (currentAcc.isSelected()) {
                accountType = "Current Account";
            } else if (recurringDeposit.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            //auto generate pin no. and account no.
            Random random = new Random();
            
            //here next long total 16 digit for card no  so inital to be same so 5040936 
            // added 8 more 0 infront to change randomly till 90000000L digit long
            //we want absoulte / positive value only so use MAth.abs()
            //convert long value to String using concatenation
            
            String cardNo = ""+Math.abs((random.nextLong() %90000000L)+5040936000000000L);
            String pinNo = ""+Math.abs((random.nextLong() %9000L)+1000L);
            
            // to get check box value
            String facility ="";
            if(atmCard.isSelected()) {
                facility= facility+" ATM CARD";
            }
            if (internetBank.isSelected()) {
                facility = facility+" Internet Banking";
            }
            if (mobileBank.isSelected()) {
                facility=facility+" Mobile Banking";
            }
            if (emailSMSalert.isSelected()) {
                facility= facility+" EMAIL AND SMS Alerts";
            }
            if (chequeBook.isSelected()) {
                facility=facility+" Cheque Book";
            }
            if (eStatement.isSelected()) {
                facility=facility+" E-Statement";
            }

            String reqState = null;
            if(reqStatement.isSelected())
            {
                reqState= "Agreed";
            }

            try
            {
                if(accountType == null || facility.equals("") || reqState == null)
                {
                 JOptionPane.showMessageDialog(null, "Account Type, Service Type, and the declaration checkbox are all required.");
                }
                else {

                    Conn c = new Conn();
                    String query = "INSERT INTO signupthree VALUES ('"+formNo+"', '"+accountType+"', '"+cardNo+"', '"
                            +pinNo+"', '" +facility+ "', '"+reqState +"')";

                    String query2 ="INSERT INTO login VALUES ('"+formNo+"','"+cardNo+"', '" +pinNo+"')";

                    c.statement.executeUpdate(query);
                    c.statement.executeUpdate(query2);

                    //give generated pin and atm no. to user

                    JOptionPane.showMessageDialog(null,"Card Number : "+cardNo+"\nPin : "+pinNo);

                    setVisible(false);
                    new Deposit(pinNo).setVisible(true);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        else if (ae.getSource()== clear1) {
            setVisible(false);
            new Login().setVisible(true);
        }


    }

    public static void main(String[] args) {
        new SignUpThree("");
    }
}
