package gym_system.repository;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class SignUpTwo extends JFrame implements ActionListener {
    JButton next;
    JComboBox<String> bloodGroupBox, preferredTimeBox;
    JTextField heightField, weightField, previousInjuriesField, allergiesField;
    JLabel bmiLabel;
    JCheckBox diabetesCheck, asthmaCheck, heartIssueCheck, jointIssueCheck, noneCheck;
    JRadioButton weightLoss, muscleGain, endurance, generalFitness;
    JRadioButton beginner, intermediate, advanced;
    String formNo;

    SignUpTwo(String formNo) {
        this.formNo = formNo;
        setLayout(null);
        setSize(850, 750);
        setLocation(285, 5);
        setTitle("HEALTH & MEDICAL INFORMATION");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        // Header
        JLabel header = new JLabel("Page 2: Health & Medical Information");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.BLACK);
        header.setBounds(180, 30, 600, 40);
        add(header);

        // Blood Group
        JLabel bloodGroupLabel = new JLabel("Blood Group: ");
        bloodGroupLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bloodGroupLabel.setBounds(50, 100, 200, 30);
        bloodGroupLabel.setForeground(Color.BLACK);
        add(bloodGroupLabel);

        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodGroupBox = new JComboBox<>(bloodGroups);
        bloodGroupBox.setBounds(250, 100, 200, 30);
        bloodGroupBox.setBackground(Color.WHITE);
        add(bloodGroupBox);

        // Height
        JLabel heightLabel = new JLabel("Height (cm): ");
        heightLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        heightLabel.setBounds(50, 150, 200, 30);
        heightLabel.setForeground(Color.BLACK);
        add(heightLabel);

        heightField = new JTextField();
        heightField.setBounds(250, 150, 200, 30);
        heightField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        heightField.setBackground(Color.WHITE);
        add(heightField);

        // Weight
        JLabel weightLabel = new JLabel("Weight (kg): ");
        weightLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        weightLabel.setBounds(500, 150, 200, 30);
        weightLabel.setForeground(Color.BLACK);
        add(weightLabel);

        weightField = new JTextField();
        weightField.setBounds(600, 150, 200, 30);
        weightField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        weightField.setBackground(Color.WHITE);
        add(weightField);

        // BMI (auto-calculated, display only)
        JLabel bmiTitleLabel = new JLabel("BMI: ");
        bmiTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bmiTitleLabel.setBounds(50, 200, 200, 30);
        bmiTitleLabel.setForeground(Color.BLACK);
        add(bmiTitleLabel);

        bmiLabel = new JLabel("--");
        bmiLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        bmiLabel.setForeground(new Color(255, 107, 53));
        bmiLabel.setBounds(250, 200, 200, 30);
        add(bmiLabel);

        // Add focus listeners for auto BMI calculation
        heightField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateBMI();
            }
        });

        weightField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateBMI();
            }
        });

        // Medical Conditions
        JLabel medicalCondLabel = new JLabel("Medical Conditions: ");
        medicalCondLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        medicalCondLabel.setBounds(50, 250, 200, 30);
        medicalCondLabel.setForeground(Color.BLACK);
        add(medicalCondLabel);

        diabetesCheck = new JCheckBox("Diabetes");
        diabetesCheck.setBounds(250, 250, 100, 30);
        diabetesCheck.setBackground(Color.WHITE);
        diabetesCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(diabetesCheck);

        asthmaCheck = new JCheckBox("Asthma");
        asthmaCheck.setBounds(360, 250, 100, 30);
        asthmaCheck.setBackground(Color.WHITE);
        asthmaCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(asthmaCheck);

        heartIssueCheck = new JCheckBox("Heart Issue");
        heartIssueCheck.setBounds(460, 250, 120, 30);
        heartIssueCheck.setBackground(Color.WHITE);
        heartIssueCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(heartIssueCheck);

        jointIssueCheck = new JCheckBox("Joint Issue");
        jointIssueCheck.setBounds(590, 250, 120, 30);
        jointIssueCheck.setBackground(Color.WHITE);
        jointIssueCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(jointIssueCheck);

        noneCheck = new JCheckBox("None");
        noneCheck.setBounds(720, 250, 80, 30);
        noneCheck.setBackground(Color.WHITE);
        noneCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(noneCheck);

        // "None" checkbox deselects others
        noneCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (noneCheck.isSelected()) {
                    diabetesCheck.setSelected(false);
                    asthmaCheck.setSelected(false);
                    heartIssueCheck.setSelected(false);
                    jointIssueCheck.setSelected(false);
                }
            }
        });

        // Deselect "None" when any other checkbox is selected
        ActionListener medicalCheckListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (diabetesCheck.isSelected() || asthmaCheck.isSelected() ||
                    heartIssueCheck.isSelected() || jointIssueCheck.isSelected()) {
                    noneCheck.setSelected(false);
                }
            }
        };
        diabetesCheck.addActionListener(medicalCheckListener);
        asthmaCheck.addActionListener(medicalCheckListener);
        heartIssueCheck.addActionListener(medicalCheckListener);
        jointIssueCheck.addActionListener(medicalCheckListener);

        // Previous Injuries
        JLabel prevInjuriesLabel = new JLabel("Previous Injuries: ");
        prevInjuriesLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        prevInjuriesLabel.setBounds(50, 300, 200, 30);
        prevInjuriesLabel.setForeground(Color.BLACK);
        add(prevInjuriesLabel);

        previousInjuriesField = new JTextField();
        previousInjuriesField.setBounds(250, 300, 550, 30);
        previousInjuriesField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        previousInjuriesField.setBackground(Color.WHITE);
        add(previousInjuriesField);

        // Fitness Goal
        JLabel fitnessGoalLabel = new JLabel("Fitness Goal: ");
        fitnessGoalLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        fitnessGoalLabel.setBounds(50, 350, 200, 30);
        fitnessGoalLabel.setForeground(Color.BLACK);
        add(fitnessGoalLabel);

        weightLoss = new JRadioButton("Weight Loss");
        weightLoss.setBounds(250, 350, 130, 30);
        weightLoss.setBackground(Color.WHITE);
        weightLoss.setFont(new Font("Segoe UI", Font.BOLD, 14));

        muscleGain = new JRadioButton("Muscle Gain");
        muscleGain.setBounds(390, 350, 130, 30);
        muscleGain.setBackground(Color.WHITE);
        muscleGain.setFont(new Font("Segoe UI", Font.BOLD, 14));

        endurance = new JRadioButton("Endurance");
        endurance.setBounds(530, 350, 120, 30);
        endurance.setBackground(Color.WHITE);
        endurance.setFont(new Font("Segoe UI", Font.BOLD, 14));

        generalFitness = new JRadioButton("General Fitness");
        generalFitness.setBounds(660, 350, 150, 30);
        generalFitness.setBackground(Color.WHITE);
        generalFitness.setFont(new Font("Segoe UI", Font.BOLD, 14));

        ButtonGroup fitnessGoalGroup = new ButtonGroup();
        fitnessGoalGroup.add(weightLoss);
        fitnessGoalGroup.add(muscleGain);
        fitnessGoalGroup.add(endurance);
        fitnessGoalGroup.add(generalFitness);
        add(weightLoss);
        add(muscleGain);
        add(endurance);
        add(generalFitness);

        // Experience Level
        JLabel expLabel = new JLabel("Experience Level: ");
        expLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        expLabel.setBounds(50, 400, 200, 30);
        expLabel.setForeground(Color.BLACK);
        add(expLabel);

        beginner = new JRadioButton("Beginner");
        beginner.setBounds(250, 400, 120, 30);
        beginner.setBackground(Color.WHITE);
        beginner.setFont(new Font("Segoe UI", Font.BOLD, 14));

        intermediate = new JRadioButton("Intermediate");
        intermediate.setBounds(380, 400, 140, 30);
        intermediate.setBackground(Color.WHITE);
        intermediate.setFont(new Font("Segoe UI", Font.BOLD, 14));

        advanced = new JRadioButton("Advanced");
        advanced.setBounds(530, 400, 120, 30);
        advanced.setBackground(Color.WHITE);
        advanced.setFont(new Font("Segoe UI", Font.BOLD, 14));

        ButtonGroup expGroup = new ButtonGroup();
        expGroup.add(beginner);
        expGroup.add(intermediate);
        expGroup.add(advanced);
        add(beginner);
        add(intermediate);
        add(advanced);

        // Preferred Time
        JLabel prefTimeLabel = new JLabel("Preferred Time: ");
        prefTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        prefTimeLabel.setBounds(50, 450, 200, 30);
        prefTimeLabel.setForeground(Color.BLACK);
        add(prefTimeLabel);

        String[] times = {"Morning (6-10 AM)", "Afternoon (12-4 PM)", "Evening (5-9 PM)"};
        preferredTimeBox = new JComboBox<>(times);
        preferredTimeBox.setBounds(250, 450, 300, 30);
        preferredTimeBox.setBackground(Color.WHITE);
        add(preferredTimeBox);

        // Allergies
        JLabel allergiesLabel = new JLabel("Allergies: ");
        allergiesLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        allergiesLabel.setBounds(50, 500, 200, 30);
        allergiesLabel.setForeground(Color.BLACK);
        add(allergiesLabel);

        allergiesField = new JTextField();
        allergiesField.setBounds(250, 500, 550, 30);
        allergiesField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        allergiesField.setBackground(Color.WHITE);
        add(allergiesField);

        // Next Button
        next = new JButton("Next");
        next.setBounds(650, 620, 100, 40);
        next.setBackground(new Color(255, 107, 53));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(next);
        next.addActionListener(this);

        setVisible(true);
    }

    private void calculateBMI() {
        try {
            String heightStr = heightField.getText().trim();
            String weightStr = weightField.getText().trim();

            if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
                double heightCm = Double.parseDouble(heightStr);
                double weightKg = Double.parseDouble(weightStr);

                // Validate ranges
                if (heightCm < 100 || heightCm > 250) {
                    bmiLabel.setText("Invalid height");
                    return;
                }
                if (weightKg < 20 || weightKg > 300) {
                    bmiLabel.setText("Invalid weight");
                    return;
                }

                double heightM = heightCm / 100.0;
                double bmi = weightKg / (heightM * heightM);
                bmiLabel.setText(String.format("%.2f", bmi));

                // Color code BMI
                if (bmi < 18.5) {
                    bmiLabel.setForeground(new Color(52, 152, 219)); // Underweight - Blue
                } else if (bmi < 25) {
                    bmiLabel.setForeground(new Color(46, 204, 113)); // Normal - Green
                } else if (bmi < 30) {
                    bmiLabel.setForeground(new Color(241, 196, 15)); // Overweight - Yellow
                } else {
                    bmiLabel.setForeground(new Color(231, 76, 60)); // Obese - Red
                }
            }
        } catch (NumberFormatException ex) {
            bmiLabel.setText("--");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String bloodGroup = (String) bloodGroupBox.getSelectedItem();
        String height = heightField.getText().trim();
        String weight = weightField.getText().trim();
        String bmi = bmiLabel.getText();
        String previousInjuries = previousInjuriesField.getText().trim();
        String allergies = allergiesField.getText().trim();

        // Build medical conditions string
        StringBuilder medicalConditions = new StringBuilder();
        if (diabetesCheck.isSelected()) medicalConditions.append("Diabetes ");
        if (asthmaCheck.isSelected()) medicalConditions.append("Asthma ");
        if (heartIssueCheck.isSelected()) medicalConditions.append("Heart Issue ");
        if (jointIssueCheck.isSelected()) medicalConditions.append("Joint Issue ");
        if (noneCheck.isSelected() || medicalConditions.length() == 0) medicalConditions.append("None");
        String medicalCondStr = medicalConditions.toString().trim();

        // Fitness goal
        String fitnessGoal = null;
        if (weightLoss.isSelected()) fitnessGoal = "Weight Loss";
        else if (muscleGain.isSelected()) fitnessGoal = "Muscle Gain";
        else if (endurance.isSelected()) fitnessGoal = "Endurance";
        else if (generalFitness.isSelected()) fitnessGoal = "General Fitness";

        // Experience level
        String experienceLevel = null;
        if (beginner.isSelected()) experienceLevel = "Beginner";
        else if (intermediate.isSelected()) experienceLevel = "Intermediate";
        else if (advanced.isSelected()) experienceLevel = "Advanced";

        // Preferred time
        String preferredTime = (String) preferredTimeBox.getSelectedItem();

        // Validation
        if (height.isEmpty() || weight.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Height and Weight are required.");
            return;
        }

        try {
            double h = Double.parseDouble(height);
            if (h < 100 || h > 250) {
                JOptionPane.showMessageDialog(null, "Height must be between 100-250 cm.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid height.");
            return;
        }

        try {
            double w = Double.parseDouble(weight);
            if (w < 20 || w > 300) {
                JOptionPane.showMessageDialog(null, "Weight must be between 20-300 kg.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid weight.");
            return;
        }

        if (fitnessGoal == null) {
            JOptionPane.showMessageDialog(null, "Please select a Fitness Goal.");
            return;
        }

        if (experienceLevel == null) {
            JOptionPane.showMessageDialog(null, "Please select an Experience Level.");
            return;
        }

        try {
            Conn c = new Conn();
            if (c.connection == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return;
            }

            String query = "INSERT INTO member_health VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, formNo);
            pstmt.setString(2, bloodGroup);
            pstmt.setString(3, height);
            pstmt.setString(4, weight);
            pstmt.setString(5, bmi.equals("--") ? "" : bmi);
            pstmt.setString(6, medicalCondStr);
            pstmt.setString(7, previousInjuries.isEmpty() ? "None" : previousInjuries);
            pstmt.setString(8, fitnessGoal);
            pstmt.setString(9, experienceLevel);
            pstmt.setString(10, preferredTime);
            pstmt.setString(11, allergies.isEmpty() ? "None" : allergies);

            pstmt.executeUpdate();
            setVisible(false);
            new SignUpThree(formNo).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUpTwo("");
    }
}
