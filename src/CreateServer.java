import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;

public class CreateServer {

    static JFrame frame;
    static JPanel createServerFrame;
    static JLabel serverNameLabel;
    static JLabel serverPortLabel;
    static JTextField serverNameField;
    static JTextField serverPortField;
    static JButton startServerButton;

    String serverName;
    int serverPort;

    public void init() throws Exception {

        Server server = new Server();
        ServerConsole serverConsole = new ServerConsole();

        initComponents();
        frame = new JFrame("Create server");
        frame.add(createServerFrame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(new Dimension(328, 115));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        startServerButton.addActionListener(e -> {
            if(check()){
                try {
                    hide();
                    serverConsole.show();
                    serverConsole.SetLabel("Server Output | " + serverName + " @port:" + serverPort);
                    server.StartServer(serverName, serverPort);
                } catch (Exception e1) {
                    StringWriter errors = new StringWriter();
                    e1.printStackTrace(new PrintWriter(errors));
                    serverConsole.WriteToConsole("[E] " + errors.toString());
                    serverConsole.WriteToConsole("[E] CANNOT BIND PORT");
                }
            }
        });

    }

    private void initComponents() {

        createServerFrame = new JPanel();
        serverNameLabel = new JLabel();
        serverPortLabel = new JLabel();
        serverNameField = new JTextField();
        serverPortField = new JTextField();
        startServerButton = new JButton();

        //======== createServerFrame ========
        {

            // JFormDesigner evaluation mark
            createServerFrame.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), createServerFrame.getBorder())); createServerFrame.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

            createServerFrame.setLayout(null);

            //---- serverNameLabel ----
            serverNameLabel.setText("Server Name");
            serverNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            createServerFrame.add(serverNameLabel);
            serverNameLabel.setBounds(10, 10, 95, 30);

            //---- serverPortLabel ----
            serverPortLabel.setText("Server Port");
            serverPortLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            createServerFrame.add(serverPortLabel);
            serverPortLabel.setBounds(10, 45, 95, 30);


            serverNameField.setBackground(new Color(231, 231, 231));
            serverNameField.setBorder(new LineBorder(new Color(215, 215, 215), 2));
            serverNameField.setForeground(Color.black);
            serverNameField.setFont(serverNameField.getFont().deriveFont(serverNameField.getFont().getStyle() & ~Font.BOLD, 16f));
            createServerFrame.add(serverNameField);
            serverNameField.setBounds(110, 10, 200, 30);


            serverPortField.setBackground(new Color(231, 231, 231));
            serverPortField.setBorder(new LineBorder(new Color(215, 215, 215), 2));
            serverPortField.setForeground(Color.black);
            serverPortField.setFont(serverPortField.getFont().deriveFont(serverPortField.getFont().getStyle() & ~Font.BOLD, 16f));
            createServerFrame.add(serverPortField);
            serverPortField.setBounds(110, 45, 60, 30);

            //---- startServerButton ----
            startServerButton.setText("Start Server");
            startServerButton.setBorder(null);
            startServerButton.setBackground(new Color(204, 204, 204));
            startServerButton.setForeground(Color.black);
            startServerButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            createServerFrame.add(startServerButton);
            startServerButton.setBounds(175, 45, 135, 30);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < createServerFrame.getComponentCount(); i++) {
                    Rectangle bounds = createServerFrame.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = createServerFrame.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                createServerFrame.setMinimumSize(preferredSize);
                createServerFrame.setPreferredSize(preferredSize);
            }
        }
    }


    public void show(){
        frame.setVisible(true);
    }

    public void hide(){
        frame.setVisible(false);
    }

    public boolean check(){
        boolean name = false;
        boolean port = false;

        String srvName = serverNameField.getText();

        if(srvName.length() > 0){
            name = true;
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

        if(name && port){
            serverName = serverNameField.getText();
            serverPort = Integer.parseInt(serverPortField.getText());
            return true;
        }else{

            if(!name){
                JOptionPane.showMessageDialog(null, "Enter Server Name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!port){
                JOptionPane.showMessageDialog(null, "Enter Server Port! (1 - 65535)", "Error", JOptionPane.ERROR_MESSAGE);
            }

            return false;
        }

    }

}