package gym_system.repository;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SignUpThree extends JFrame implements ActionListener {
    String formNo;
    JButton cancel, submit;
    JRadioButton monthly, quarterly, semiAnnual, annual;
    JCheckBox personalTrainer, yogaClasses, zumbaClasses, dietPlan, lockerFacility, steamSauna, parking;
    JCheckBox declaration;

    SignUpThree(String formNo) {
        this.formNo = formNo;
        setLayout(null);
        setSize(850, 750);
        setLocation(285, 5);
        setTitle("MEMBERSHIP PLAN SELECTION");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        // Header
        JLabel header = new JLabel("Page 3: Membership Plan Selection");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBounds(190, 30, 600, 40);
        add(header);

        // Membership Plan Type
        JLabel planTypeLabel = new JLabel("Membership Plan:");
        planTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        planTypeLabel.setForeground(Color.BLACK);
        planTypeLabel.setBounds(50, 90, 400, 40);
        add(planTypeLabel);

        monthly = new JRadioButton("Monthly (\u20B91,500)");
        monthly.setBounds(90, 145, 220, 30);
        monthly.setBackground(Color.WHITE);
        monthly.setFont(new Font("Segoe UI", Font.BOLD, 15));

        quarterly = new JRadioButton("Quarterly (\u20B94,000)");
        quarterly.setBounds(340, 145, 230, 30);
        quarterly.setBackground(Color.WHITE);
        quarterly.setFont(new Font("Segoe UI", Font.BOLD, 15));

        semiAnnual = new JRadioButton("Semi-Annual (\u20B97,000)");
        semiAnnual.setBounds(90, 185, 250, 30);
        semiAnnual.setBackground(Color.WHITE);
        semiAnnual.setFont(new Font("Segoe UI", Font.BOLD, 15));

        annual = new JRadioButton("Annual (\u20B912,000)");
        annual.setBounds(340, 185, 230, 30);
        annual.setBackground(Color.WHITE);
        annual.setFont(new Font("Segoe UI", Font.BOLD, 15));

        ButtonGroup planGroup = new ButtonGroup();
        planGroup.add(monthly);
        planGroup.add(quarterly);
        planGroup.add(semiAnnual);
        planGroup.add(annual);
        add(monthly);
        add(quarterly);
        add(semiAnnual);
        add(annual);

        // Auto-generated Member ID
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        memberIdLabel.setForeground(Color.BLACK);
        memberIdLabel.setBounds(50, 240, 200, 40);
        add(memberIdLabel);

        JLabel memberIdSub = new JLabel("Your 10-Digit Member ID");
        memberIdSub.setFont(new Font("Segoe UI", Font.BOLD, 14));
        memberIdSub.setForeground(Color.GRAY);
        memberIdSub.setBounds(50, 275, 300, 30);
        add(memberIdSub);

        Random random = new Random();
        String memberId = "" + Math.abs((random.nextLong() % 9000000000L) + 1000000000L);
        JLabel memberIdDisplay = new JLabel(memberId);
        memberIdDisplay.setFont(new Font("Segoe UI", Font.BOLD, 30));
        memberIdDisplay.setForeground(new Color(255, 107, 53));
        memberIdDisplay.setBounds(280, 240, 300, 40);
        add(memberIdDisplay);

        // Auto-generated PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pinLabel.setForeground(Color.BLACK);
        pinLabel.setBounds(50, 310, 200, 40);
        add(pinLabel);

        JLabel pinSub = new JLabel("Your 4-Digit PIN");
        pinSub.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pinSub.setForeground(Color.GRAY);
        pinSub.setBounds(50, 345, 200, 30);
        add(pinSub);

        String pinNo = "" + Math.abs((random.nextLong() % 9000L) + 1000L);
        JLabel pinDisplay = new JLabel(pinNo);
        pinDisplay.setFont(new Font("Segoe UI", Font.BOLD, 30));
        pinDisplay.setForeground(new Color(255, 107, 53));
        pinDisplay.setBounds(280, 310, 150, 40);
        add(pinDisplay);

        // Additional Services
        JLabel servicesLabel = new JLabel("Additional Services (Multi-Select):");
        servicesLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        servicesLabel.setForeground(Color.BLACK);
        servicesLabel.setBounds(50, 385, 500, 40);
        add(servicesLabel);

        personalTrainer = new JCheckBox("Personal Trainer (\u20B93,000/mo)");
        personalTrainer.setBounds(50, 440, 300, 30);
        personalTrainer.setBackground(Color.WHITE);
        personalTrainer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(personalTrainer);

        yogaClasses = new JCheckBox("Yoga Classes (\u20B9500/mo)");
        yogaClasses.setBounds(50, 480, 280, 30);
        yogaClasses.setBackground(Color.WHITE);
        yogaClasses.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(yogaClasses);

        zumbaClasses = new JCheckBox("Zumba Classes (\u20B9500/mo)");
        zumbaClasses.setBounds(50, 520, 280, 30);
        zumbaClasses.setBackground(Color.WHITE);
        zumbaClasses.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(zumbaClasses);

        dietPlan = new JCheckBox("Diet Plan (\u20B91,000/mo)");
        dietPlan.setBounds(400, 440, 280, 30);
        dietPlan.setBackground(Color.WHITE);
        dietPlan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(dietPlan);

        lockerFacility = new JCheckBox("Locker Facility (\u20B9300/mo)");
        lockerFacility.setBounds(400, 480, 280, 30);
        lockerFacility.setBackground(Color.WHITE);
        lockerFacility.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(lockerFacility);

        steamSauna = new JCheckBox("Steam & Sauna (\u20B9400/mo)");
        steamSauna.setBounds(400, 520, 280, 30);
        steamSauna.setBackground(Color.WHITE);
        steamSauna.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(steamSauna);

        parking = new JCheckBox("Parking (\u20B9200/mo)");
        parking.setBounds(50, 560, 280, 30);
        parking.setBackground(Color.WHITE);
        parking.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(parking);

        // Declaration checkbox
        declaration = new JCheckBox("I hereby declare that all information provided is accurate and I agree to the gym's terms & conditions.");
        declaration.setBounds(50, 600, 700, 30);
        declaration.setBackground(Color.WHITE);
        declaration.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(declaration);

        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(530, 650, 100, 35);
        cancel.setBackground(Color.GRAY);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(cancel);
        cancel.addActionListener(this);

        // Submit Button
        submit = new JButton("Submit");
        submit.setBounds(650, 650, 100, 35);
        submit.setBackground(new Color(255, 107, 53));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(submit);
        submit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            // Get selected plan
            String planType = null;
            if (monthly.isSelected()) planType = "Monthly";
            else if (quarterly.isSelected()) planType = "Quarterly";
            else if (semiAnnual.isSelected()) planType = "Semi-Annual";
            else if (annual.isSelected()) planType = "Annual";

            // Build services string using INDEPENDENT if statements (NOT else-if)
            // This fixes the banking system bug where only the first checked service was captured
            StringBuilder services = new StringBuilder();
            if (personalTrainer.isSelected()) services.append("Personal Trainer ");
            if (yogaClasses.isSelected()) services.append("Yoga Classes ");
            if (zumbaClasses.isSelected()) services.append("Zumba Classes ");
            if (dietPlan.isSelected()) services.append("Diet Plan ");
            if (lockerFacility.isSelected()) services.append("Locker Facility ");
            if (steamSauna.isSelected()) services.append("Steam & Sauna ");
            if (parking.isSelected()) services.append("Parking ");
            String servicesStr = services.toString().trim();

            String declarationText = null;
            if (declaration.isSelected()) {
                declarationText = "Agreed";
            }

            // Validation
            if (planType == null) {
                JOptionPane.showMessageDialog(null, "Please select a Membership Plan.");
                return;
            }

            if (!declaration.isSelected()) {
                JOptionPane.showMessageDialog(null, "You must accept the terms & conditions to proceed.");
                return;
            }

            // Auto-generate Member ID and PIN
            Random random = new Random();
            String memberId = "" + Math.abs((random.nextLong() % 9000000000L) + 1000000000L);
            String pinNo = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            // Calculate join date and expiry date
            LocalDate joinDate = LocalDate.now();
            LocalDate expiryDate;
            switch (planType) {
                case "Monthly":
                    expiryDate = joinDate.plusDays(30);
                    break;
                case "Quarterly":
                    expiryDate = joinDate.plusDays(90);
                    break;
                case "Semi-Annual":
                    expiryDate = joinDate.plusDays(180);
                    break;
                case "Annual":
                    expiryDate = joinDate.plusDays(365);
                    break;
                default:
                    expiryDate = joinDate.plusDays(30);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String joinDateStr = joinDate.format(formatter);
            String expiryDateStr = expiryDate.format(formatter);

            try {
                Conn c = new Conn();
                if (c.connection == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed!");
                    return;
                }

                // Insert into membership table using PreparedStatement
                String query1 = "INSERT INTO membership VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt1 = c.connection.prepareStatement(query1);
                pstmt1.setString(1, formNo);
                pstmt1.setString(2, planType);
                pstmt1.setString(3, memberId);
                pstmt1.setString(4, pinNo);
                pstmt1.setString(5, servicesStr.isEmpty() ? "None" : servicesStr);
                pstmt1.setString(6, joinDateStr);
                pstmt1.setString(7, expiryDateStr);
                pstmt1.setString(8, declarationText);
                pstmt1.executeUpdate();

                // Insert into login table using PreparedStatement
                String query2 = "INSERT INTO login VALUES (?, ?, ?)";
                PreparedStatement pstmt2 = c.connection.prepareStatement(query2);
                pstmt2.setString(1, formNo);
                pstmt2.setString(2, memberId);
                pstmt2.setString(3, pinNo);
                pstmt2.executeUpdate();

                // Show generated credentials to user
                JOptionPane.showMessageDialog(null,
                        "Registration Successful!\n\n" +
                        "Member ID: " + memberId + "\n" +
                        "PIN: " + pinNo + "\n\n" +
                        "Please make an initial deposit to activate your account.",
                        "Registration Complete",
                        JOptionPane.INFORMATION_MESSAGE);

                setVisible(false);
                new AddFunds(memberId).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        } else if (ae.getSource() == cancel) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to cancel? All entered data will be lost.",
                    "Confirm Cancel",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login().setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        new SignUpThree("");
    }
}
