// Written By:
// Rayyan Kamil bin Khairuddin (22005640)
// Nik Muhammad Adam bin Mustafa (22005624)
// Khiedir Iqbal bin Abdul Hamid (22003852)
// Muhammad Adam Zafran bin Mohd Ridza (22005617)

package com.example.BFAC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GUI {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;    
    private JTextField carPlateField;
    private JTextField workerNameField;
    private JTextField workerIDField;
    private JCheckBox waterWaxCheckBox;
    private JCheckBox fullDetailingInteriorCheckBox;
    private JCheckBox fullDetailingExteriorCheckBox;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField statusField;
    private JTextArea outputTextArea;
    private Connection connection;

    // Database Connector
    private static final String URL = "jdbc:mysql://localhost:3306/bfac";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // empty string, no password

    public GUI() {
        // Set the look and feel to the system's native look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Bro Fresh And Clean");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panels
        JPanel clientPanel = createClientPanel();
        JPanel workerPanel = createWorkerPanel();
        JPanel washOptionsPanel = createWashOptionsPanel();
        JPanel sessionPanel = createSessionPanel();
        JPanel southPanel = createSouthPanel();

        // Layout
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(clientPanel, BorderLayout.NORTH);
        frame.add(workerPanel, BorderLayout.WEST);
        frame.add(washOptionsPanel, BorderLayout.CENTER);
        frame.add(sessionPanel, BorderLayout.EAST);
        frame.add(southPanel, BorderLayout.SOUTH);

        // Frame settings
        frame.setSize(800, 600); // Set the frame size
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Connect to the database
        connection = connectToDatabase();
    }

    private JPanel createClientPanel() {
        JPanel clientPanel = new JPanel(new BorderLayout(10, 10));
        clientPanel.setBorder(BorderFactory.createTitledBorder("Client Information"));
        clientPanel.setBackground(new Color(224, 255, 255)); // Light cyan

        JPanel clientInfoPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        clientInfoPanel.setBackground(new Color(224, 255, 255)); // Light cyan

        clientInfoPanel.add(createLabel("First Name:"));
        firstNameField = new JTextField();
        clientInfoPanel.add(firstNameField);

        clientInfoPanel.add(createLabel("Last Name:"));
        lastNameField = new JTextField();
        clientInfoPanel.add(lastNameField);

        clientInfoPanel.add(createLabel("Phone Number:"));
        phoneField = new JTextField();
        clientInfoPanel.add(phoneField);

        clientInfoPanel.add(createLabel("Car Plate Number:"));
        carPlateField = new JTextField();
        clientInfoPanel.add(carPlateField);

        clientPanel.add(clientInfoPanel, BorderLayout.CENTER);

        return clientPanel;
    }

    private JPanel createWorkerPanel() {
        JPanel workerPanel = new JPanel(new BorderLayout(10, 10));
        workerPanel.setBorder(BorderFactory.createTitledBorder("Worker Information"));
        workerPanel.setBackground(new Color(255, 228, 196)); // Light peach

        JPanel workerInfoPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        workerInfoPanel.setBackground(new Color(255, 228, 196)); // Light peach

        workerInfoPanel.add(createLabel("Worker Name:"));
        workerNameField = new JTextField();
        workerInfoPanel.add(workerNameField);

        workerInfoPanel.add(createLabel("Worker ID:"));
        workerIDField = new JTextField();
        workerInfoPanel.add(workerIDField);

        workerPanel.add(workerInfoPanel, BorderLayout.CENTER);

        return workerPanel;
    }

    private JPanel createWashOptionsPanel() {
        JPanel washOptionsPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        washOptionsPanel.setBorder(BorderFactory.createTitledBorder("Wash Options"));
        washOptionsPanel.setBackground(new Color(240, 255, 240)); // Light green

        washOptionsPanel.add(createLabel("Water Wax:"));
        waterWaxCheckBox = new JCheckBox();
        waterWaxCheckBox.setBackground(new Color(240, 255, 240)); // Light green
        washOptionsPanel.add(waterWaxCheckBox);

        washOptionsPanel.add(createLabel("Full Interior Detailing:"));
        fullDetailingInteriorCheckBox = new JCheckBox();
        fullDetailingInteriorCheckBox.setBackground(new Color(240, 255, 240)); // Light green
        washOptionsPanel.add(fullDetailingInteriorCheckBox);

        washOptionsPanel.add(createLabel("Full Exterior Detailing:"));
        fullDetailingExteriorCheckBox = new JCheckBox();
        fullDetailingExteriorCheckBox.setBackground(new Color(240, 255, 240)); // Light green
        washOptionsPanel.add(fullDetailingExteriorCheckBox);

        return washOptionsPanel;
    }

    private JPanel createSessionPanel() {
        JPanel sessionPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        sessionPanel.setBorder(BorderFactory.createTitledBorder("Wash Session"));
        sessionPanel.setBackground(new Color(255, 240, 245)); // Light pink

        sessionPanel.add(createLabel("Start Time:"));
        startTimeField = new JTextField();
        sessionPanel.add(startTimeField);

        sessionPanel.add(createLabel("End Time:"));
        endTimeField = new JTextField();
        sessionPanel.add(endTimeField);

        sessionPanel.add(createLabel("Status:"));
        statusField = new JTextField();
        sessionPanel.add(statusField);

        return sessionPanel;
    }

    private JPanel createSouthPanel() {
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(245, 245, 245)); // Light gray

        // Create the submit button panel
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(new Color(245, 245, 245)); // Light gray
        JButton submitButton = new JButton("Enter All Info");
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setBackground(new Color(173, 216, 230)); // Light blue
        submitButton.setForeground(Color.BLACK); // Set the text color to black
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set the border color to black
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setOpaque(true); // Make the button opaque
        submitButton.setContentAreaFilled(true); // Ensure the content area is filled with the background color
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phone = phoneField.getText();
                String carPlate = carPlateField.getText();
                String workerName = workerNameField.getText();
                String workerID = workerIDField.getText();
                boolean waterWax = waterWaxCheckBox.isSelected();
                boolean fullDetailingInterior = fullDetailingInteriorCheckBox.isSelected();
                boolean fullDetailingExterior = fullDetailingExteriorCheckBox.isSelected();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String status = statusField.getText();

                insertData(firstName, lastName, phone, carPlate, workerName, workerID, waterWax, fullDetailingInterior, fullDetailingExterior, startTime, endTime, status);

                // Create the output string
                String output = String.format(
                        "______________________________________________________________\n" +
                        "-----: Worker's In Charge :-----\n" +
                        "Worker's Name: %s\n" +
                        "Worker's ID: %s\n" +
                        "-----: Client :-----\n" +
                        "Client's Name: %s %s\n" +
                        "Client's Phone Number: %s\n" +
                        "Client's Car Plate Number: %s\n" +
                        "Wash Type: Basic Wash\n" +
                        "Rain Coating: %b\n" +
                        "Full Wax Interior: %b\n" +
                        "Full Wax Exterior: %b\n" +
                        "-----: Wash Session :-----\n" +
                        "Session Start: %s\n" +
                        "Session End: %s\n" +
                        "Session Status: %s\n" +
                        "______________________________________________________________\n",
                        workerName, workerID, firstName, lastName, phone, carPlate,
                        waterWax, fullDetailingInterior, fullDetailingExterior, startTime, endTime, status);

                // Display the output in the text area
                outputTextArea.setText(output);
            }
        });
        submitPanel.add(submitButton);

        // Create the output panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        outputPanel.setBackground(new Color(245, 245, 245)); // Light gray

        outputTextArea = new JTextArea(10, 50);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        outputPanel.add(scrollPane, BorderLayout.CENTER);

        // Add submit panel and output panel to the south panel
        southPanel.add(submitPanel, BorderLayout.NORTH);
        southPanel.add(outputPanel, BorderLayout.CENTER);

        return southPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }

    private void insertData(String firstName, String lastName, String phone, String carPlate, String workerName, String workerID, boolean waterWax, boolean fullDetailingInterior, boolean fullDetailingExterior, String startTime, String endTime, String status) {
        String query = "INSERT INTO bfacdb (First_Name, Last_Name, Phone_Num, Car_Plate, Worker_Name, Worker_ID, Water_Wax, Interior, Exterior, Start_Time, End_Time, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, carPlate);
            preparedStatement.setString(5, workerName);
            preparedStatement.setString(6, workerID);
            preparedStatement.setBoolean(7, waterWax);
            preparedStatement.setBoolean(8, fullDetailingInterior);
            preparedStatement.setBoolean(9, fullDetailingExterior);
            preparedStatement.setString(10, startTime);
            preparedStatement.setString(11, endTime);
            preparedStatement.setString(12, status);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data insertion failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
