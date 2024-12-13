// Author : Chillale Nagesh Rajendra
//Date : 25 Nov 2024
//Second Year IT Student
// Walchand college of engineering, Sangli
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ATMProject {

    // Map to store user accounts and balances
    private final Map<String, Double> accounts = new HashMap<>();
    private String currentUser; // Logged-in user

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMProject::new);
    }

    public ATMProject() {
        // Initialize accounts with sample data
        accounts.put("user1", 1000.0);
        accounts.put("user2", 500.0);
        accounts.put("1234", 5000.0);

        // Create main frame
        JFrame frame = new JFrame("BRAINWAVE ATM-Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new CardLayout());

        // Panels for different screens
        JPanel loginPanel = createLoginPanel(frame);
        JPanel menuPanel = createMenuPanel(frame);
        JPanel transactionPanel = createTransactionPanel(frame);

        // Add panels to the frame
        frame.add(loginPanel, "Login");
        frame.add(menuPanel, "Menu");
        frame.add(transactionPanel, "Transaction");

        // Show login panel
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "Login");

        frame.setVisible(true);
    }

    private JPanel createLoginPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(176, 224, 230)); // Light blue background

        JLabel titleLabel = new JLabel("BRAINWAVE ATM-Interface", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setBounds(100, 30, 400, 50);
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("Enter ATM Pin :");
        userLabel.setBounds(150, 120, 100, 30);
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(250, 120, 200, 30);
        panel.add(userField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 180, 100, 30);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            if (accounts.containsKey(username)) {
                currentUser = username;
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "Menu");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createMenuPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(135, 206, 250)); // Sky blue background

        JLabel menuLabel = new JLabel("ATM Menu", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        panel.add(menuLabel);

        JButton balanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit Money");
        JButton withdrawButton = new JButton("Withdraw Money");
        JButton logoutButton = new JButton("Logout");

        // Add action listeners
        balanceButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Current Balance: $" + accounts.get(currentUser), "Balance", JOptionPane.INFORMATION_MESSAGE));

        depositButton.addActionListener(e -> {
            TransactionPanel transaction = new TransactionPanel(frame, accounts, currentUser, "Deposit");
            transaction.showTransactionPanel();
        });

        withdrawButton.addActionListener(e -> {
            TransactionPanel transaction = new TransactionPanel(frame, accounts, currentUser, "Withdraw");
            transaction.showTransactionPanel();
        });

        logoutButton.addActionListener(e -> {
            currentUser = null;
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "Login");
        });

        panel.add(balanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(logoutButton);

        return panel;
    }

    private JPanel createTransactionPanel(JFrame frame) {
        // Placeholder for transaction panel; actual transaction handled in TransactionPanel class
        return new JPanel();
    }

    // Helper class for transaction handling
    static class TransactionPanel {
        private final JFrame frame;
        private final Map<String, Double> accounts;
        private final String currentUser;
        private final String transactionType;

        public TransactionPanel(JFrame frame, Map<String, Double> accounts, String currentUser, String transactionType) {
            this.frame = frame;
            this.accounts = accounts;
            this.currentUser = currentUser;
            this.transactionType = transactionType;
        }

        public void showTransactionPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(new Color(173, 216, 230)); // Light blue background

            JLabel transactionLabel = new JLabel(transactionType + " Money", SwingConstants.CENTER);
            transactionLabel.setFont(new Font("Verdana", Font.BOLD, 22));
            transactionLabel.setBounds(100, 30, 400, 50);
            panel.add(transactionLabel);

            JLabel amountLabel = new JLabel("Enter Amount:");
            amountLabel.setBounds(150, 120, 120, 30);
            panel.add(amountLabel);

            JTextField amountField = new JTextField();
            amountField.setBounds(280, 120, 150, 30);
            panel.add(amountField);

            JButton confirmButton = new JButton("Confirm");
            confirmButton.setBounds(200, 200, 100, 30);
            panel.add(confirmButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(320, 200, 100, 30);
            panel.add(cancelButton);

            confirmButton.addActionListener(e -> {
                try {
                    double amount = Double.parseDouble(amountField.getText().trim());
                    if (transactionType.equals("Deposit")) {
                        accounts.put(currentUser, accounts.get(currentUser) + amount);
                        JOptionPane.showMessageDialog(frame, "Deposit successful! New Balance: $" + accounts.get(currentUser), "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else if (transactionType.equals("Withdraw")) {
                        if (amount > accounts.get(currentUser)) {
                            JOptionPane.showMessageDialog(frame, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            accounts.put(currentUser, accounts.get(currentUser) - amount);
                            JOptionPane.showMessageDialog(frame, "Withdrawal successful! New Balance: $" + accounts.get(currentUser), "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                    cl.show(frame.getContentPane(), "Menu");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            cancelButton.addActionListener(e -> {
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "Menu");
            });

            frame.add(panel, "Transaction");
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "Transaction");
        }
    }
}
