import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMInterface::new);
    }

    public ATMInterface() {
        // Create the main frame
        JFrame frame = new JFrame("ATM Interface");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        // Panels for different screens
        JPanel loginPanel = createLoginPanel(frame);
        JPanel menuPanel = createMenuPanel(frame);

        // Add panels to the frame
        frame.add(loginPanel, "Login");
        frame.add(menuPanel, "Menu");

        // Show the login screen
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "Login");

        frame.setVisible(true);
    }

    private JPanel createLoginPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230)); // Light blue
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to the ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 30, 200, 30);
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(100, 100, 100, 30);
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(200, 100, 200, 30);
        panel.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(100, 150, 100, 30);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(200, 150, 200, 30);
        panel.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 200, 100, 30);
        loginButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            // For simplicity, no actual validation
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "Menu");
        });

        return panel;
    }

    private JPanel createMenuPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 239, 213)); // Light peach
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel menuLabel = new JLabel("Select an Option", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(menuLabel);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBackground(new Color(100, 149, 237)); // Cornflower blue
        balanceButton.setForeground(Color.WHITE);
        panel.add(balanceButton);

        JButton depositButton = new JButton("Deposit Money");
        depositButton.setBackground(new Color(72, 209, 204)); // Medium turquoise
        depositButton.setForeground(Color.WHITE);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.setBackground(new Color(240, 128, 128)); // Light coral
        withdrawButton.setForeground(Color.WHITE);
        panel.add(withdrawButton);

        // Action Listeners for buttons
        balanceButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Your balance is $1000."));
        depositButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Deposit successful!"));
        withdrawButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Withdrawal successful!"));

        return panel;
    }
}
