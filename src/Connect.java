import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Connect {

    static int usernameMaxLength = 30;

    static JFrame frame;
    static JLabel serverIPLabel;
    static JLabel usernameLabel;
    static JTextField serverIPField;
    static JTextField usernameField;
    static JTextField serverPortField;
    static JButton connectButton;

    public void init(){

        Client client = new Client();
        Game game = new Game();

        initComponents();
        serverPortField.setText("8099");

        connectButton.addActionListener(e -> {
            if(check()){
                client.Connect(serverIPField.getText(), Integer.parseInt(serverPortField.getText()), usernameField.getText());
                hide();
                game.show();
            }
        }
        );

    }

    private void initComponents() {

        serverIPLabel = new JLabel();
        usernameLabel = new JLabel();
        serverIPField = new JTextField();
        usernameField = new JTextField();
        serverPortField = new JTextField();
        connectButton = new JButton();

        JPanel panel;

        frame = new JFrame("Connect to a server");
        frame.setSize(new Dimension(340, 160));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);

        //======== this ========
        panel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.setBackground(Color.white);

        // JFormDesigner evaluation mark
        panel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), panel.getBorder()));
        panel.addPropertyChangeListener(e -> {
            if ("border".equals(e.getPropertyName())) throw new RuntimeException();
        });

        panel.setLayout(null);
        //---- serverIPLabel ----
        serverIPLabel.setText("Server IP");
        serverIPLabel.setToolTipText("Server IP and Port");
        serverIPLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        serverIPLabel.setForeground(Color.black);
        panel.add(serverIPLabel);
        serverIPLabel.setBounds(10, 10, 80, 30);

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        usernameLabel.setToolTipText("Player name");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.black);
        panel.add(usernameLabel);
        usernameLabel.setBounds(10, 45, 80, 30);

        //---- serverIPField ----
        serverIPField.setBackground(new Color(231, 231, 231));
        serverIPField.setBorder(new LineBorder(new Color(215, 215, 215), 2));
        serverIPField.setForeground(Color.black);
        serverIPField.setFont(serverIPField.getFont().deriveFont(serverIPField.getFont().getStyle() & ~Font.BOLD, 16f));
        serverIPField.setToolTipText("IP Address");
        panel.add(serverIPField);
        serverIPField.setBounds(95, 10, 160, 30);

        //---- usernameField ----
        usernameField.setBackground(new Color(231, 231, 231));
        usernameField.setBorder(new LineBorder(new Color(215, 215, 215), 2));
        usernameField.setForeground(Color.black);
        usernameField.setFont(usernameField.getFont().deriveFont(usernameField.getFont().getStyle() & ~Font.BOLD, 16f));
        panel.add(usernameField);
        usernameField.setBounds(95, 45, 225, 30);

        //---- serverPortField ----
        serverPortField.setBackground(new Color(231, 231, 231));
        serverPortField.setBorder(new LineBorder(new Color(215, 215, 215), 2));
        serverPortField.setForeground(Color.black);
        serverPortField.setFont(serverPortField.getFont().deriveFont(serverPortField.getFont().getStyle() & ~Font.BOLD, 16f));
        serverPortField.setToolTipText("Port");
        panel.add(serverPortField);
        serverPortField.setBounds(260, 10, 60, 30);

        //---- connectButton ----
        connectButton.setText("Connect");
        connectButton.setBorder(null);
        connectButton.setBackground(new Color(204, 204, 204));
        connectButton.setForeground(Color.black);
        connectButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        connectButton.setToolTipText("Connect to server");
        panel.add(connectButton);
        connectButton.setBounds(10, 85, 310, 35);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < panel.getComponentCount(); i++) {
                Rectangle bounds = panel.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = panel.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            panel.setMinimumSize(preferredSize);
            panel.setMaximumSize(preferredSize);
            panel.setPreferredSize(preferredSize);
        }
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public void show(){
        frame.setVisible(true);
    }

    public void hide(){
        frame.setVisible(false);
    }

    public boolean check(){
        boolean ip = false;
        boolean port = false;
        boolean username = false;

        String serverIP = serverIPField.getText();
        String usernameString = usernameField.getText();

        if(serverIP.length() > 0){
            ip = true;
        }

        try{
            int temp = Integer.parseInt(serverPortField.getText());
            if(temp > 0 && temp < 65535 ){
                port = true;
            }else{
                JOptionPane.showMessageDialog(null, "Port out of range!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            port = false;
        }

        if(usernameString.length() > 0 && usernameString.length() <= usernameMaxLength){
            username = true;
        }

        if(ip && port && username){
            return true;
        }else{

            if(!ip){
                JOptionPane.showMessageDialog(null, "Enter Server IP!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!port){
                JOptionPane.showMessageDialog(null, "Enter Server Port! (1 - 65535)", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!username){
                JOptionPane.showMessageDialog(null, "Enter Username! (Max length: " + usernameMaxLength + ")", "Error", JOptionPane.ERROR_MESSAGE);
            }

            return false;
        }

    }

}

