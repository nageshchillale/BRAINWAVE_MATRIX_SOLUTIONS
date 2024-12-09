import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

// Class to represent a Patient
class Patient {
    private int id;
    private String name;
    private int age;
    private String ailment;

    public Patient(int id, String name, int age, String ailment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// Class to represent a Doctor
class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// Main class for the Hospital Management System
public class HospitalManagementSystem {
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BRAINWAVE HOSPITALS ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);

            JLabel title = new JLabel("- BRAINWAVE HOSPITALS - ", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setForeground(Color.BLACK);

            JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
            buttonPanel.setBackground(Color.WHITE);

            JButton addPatientButton = createButton("Add Patient", Color.BLACK);
            JButton addDoctorButton = createButton("Add Doctor", Color.GRAY);
            JButton listPatientsButton = createButton("List Patients", Color.BLACK);
            JButton listDoctorsButton = createButton("List Doctors", Color.GRAY);
            JButton exitButton = createButton("Exit", Color.BLACK);

            buttonPanel.add(addPatientButton);
            buttonPanel.add(addDoctorButton);
            buttonPanel.add(listPatientsButton);
            buttonPanel.add(listDoctorsButton);
            buttonPanel.add(exitButton);

            mainPanel.add(title, BorderLayout.NORTH);
            mainPanel.add(buttonPanel, BorderLayout.CENTER);
            frame.add(mainPanel);

            frame.setVisible(true);

            // Event Listeners
            addPatientButton.addActionListener(e -> addPatient());
            addDoctorButton.addActionListener(e -> addDoctor());
            listPatientsButton.addActionListener(e -> listPatients());
            listDoctorsButton.addActionListener(e -> listDoctors());
            exitButton.addActionListener(e -> System.exit(0));
        });
    }

    private static JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        return button;
    }

    public static void addPatient() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField ailmentField = new JTextField();

        Object[] message = {
            "Patient ID:", idField,
            "Name:", nameField,
            "Age:", ageField,
            "Ailment:", ailmentField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Patient", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String ailment = ailmentField.getText().trim();

                if (name.isEmpty() || ailment.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive number.");
                }

                patients.add(new Patient(id, name, age, ailment));
                JOptionPane.showMessageDialog(null, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Enter valid numbers for ID and Age.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void addDoctor() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField specializationField = new JTextField();

        Object[] message = {
            "Doctor ID:", idField,
            "Name:", nameField,
            "Specialization:", specializationField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Doctor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String specialization = specializationField.getText().trim();

                if (name.isEmpty() || specialization.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                doctors.add(new Doctor(id, name, specialization));
                JOptionPane.showMessageDialog(null, "Doctor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Enter a valid number for ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void listPatients() {
        if (patients.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No patients found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder patientList = new StringBuilder("Patients:\n");
            for (Patient patient : patients) {
                patientList.append("ID: ").append(patient.getId()).append(", Name: ").append(patient.getName()).append("\n");
            }

            JTextArea textArea = new JTextArea(patientList.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Patient List", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void listDoctors() {
        if (doctors.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No doctors found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder doctorList = new StringBuilder("Doctors:\n");
            for (Doctor doctor : doctors) {
                doctorList.append("ID: ").append(doctor.getId()).append(", Name: ").append(doctor.getName()).append("\n");
            }

            JTextArea textArea = new JTextArea(doctorList.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Doctor List", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
