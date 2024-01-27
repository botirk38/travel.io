import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginbutton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> logininfo = new HashMap<String, String>();

    LoginPage(HashMap<String, String> logininfoOriginal) {
        logininfo = logininfoOriginal;

        userIDLabel.setBounds(50, 50, 75, 25);
        userPasswordLabel.setBounds(50, 100, 75, 25);

        userIDField.setBounds(125, 50, 200, 25);
        userPasswordField.setBounds(125, 100, 200, 25);

        loginbutton.setBounds(125, 150, 100, 25);
        resetButton.setBounds(230, 150, 100, 25);

        messageLabel.setBounds(125, 200, 300, 25);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginbutton);
        frame.add(resetButton);
        frame.add(messageLabel);

        loginbutton.addActionListener(this);  // Register ActionListener
        resetButton.addActionListener(this);  // Register ActionListener

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
            messageLabel.setText("");  // Clear messageLabel
        }
    
        if (e.getSource() == loginbutton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    WelcomePage welcomePage = new WelcomePage();
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect Password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("User not found");
            }
        }
    }
}
    