import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.*;

public class ServerConsole {

    static JFrame frame;

    static JPanel serverConsoleWindow;

    static JLabel serverOutputLabel;
    static JScrollPane scrollPane1;
    static JTextPane serverOutputPane;
    static JTextPane connectedClientsPane;
    static JLabel connectedClientsLabel;

    Server server = new Server();

    public void init(){
        initComponents();

        frame = new JFrame("Server Console");
        frame.add(serverConsoleWindow);
        frame.setResizable(false);
        frame.setVisible(false);
        frame.setSize(new Dimension(620, 445));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                //WriteToConsole("[S] Stopping server...");
                //server.StopServer();
                //WriteToConsole("[S] Server stopped");
                //server.StopServer();
                hide();
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                server.stop();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }

    private void initComponents() {

        System.out.println("INIT START");

        serverConsoleWindow = new JPanel();
        serverOutputLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        serverOutputPane = new JTextPane();
        connectedClientsPane = new JTextPane();
        connectedClientsLabel = new JLabel();

        //INIT SETUP

        serverOutputPane.setEditable(false);
        connectedClientsPane.setEditable(false);

        //======== serverConsoleWindow ========
        {

            // JFormDesigner evaluation mark
            serverConsoleWindow.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), serverConsoleWindow.getBorder())); serverConsoleWindow.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            serverConsoleWindow.setLayout(null);

            //---- serverOutputLabel ----
            serverOutputLabel.setText("Server Output");
            serverOutputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            serverOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
            serverConsoleWindow.add(serverOutputLabel);
            serverOutputLabel.setBounds(15, 10, 360, 31);

            //======== scrollPane1 ========
            {
                scrollPane1.setBorder(null);

                //---- serverOutputPane ----
                serverOutputPane.setBackground(Color.white);
                serverOutputPane.setBorder(new LineBorder(new Color(224, 224, 224), 2));
                scrollPane1.setViewportView(serverOutputPane);
                scrollPane1.setAutoscrolls(true);
            }
            serverConsoleWindow.add(scrollPane1);
            scrollPane1.setBounds(15, 50, 360, 355);
            scrollPane1.setAutoscrolls(true);

            //---- connectedClientsPane ----
            connectedClientsPane.setBackground(Color.white);
            connectedClientsPane.setBorder(new LineBorder(new Color(224, 224, 224), 2));
            serverConsoleWindow.add(connectedClientsPane);
            connectedClientsPane.setBounds(385, 50, 210, 355);

            //---- connectedClientsLabel ----
            connectedClientsLabel.setText("Connected Clients");
            connectedClientsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            connectedClientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            serverConsoleWindow.add(connectedClientsLabel);
            connectedClientsLabel.setBounds(385, 10, 210, 31);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < serverConsoleWindow.getComponentCount(); i++) {
                    Rectangle bounds = serverConsoleWindow.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = serverConsoleWindow.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                serverConsoleWindow.setMinimumSize(preferredSize);
                serverConsoleWindow.setPreferredSize(preferredSize);
            }
        }

        System.out.println("INIT END");

    }


    public void WriteToConsole(String message){
       try{
           String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
           String content = serverOutputPane.getText();
           serverOutputPane.setText(content + "\n" + currentTime + " | " + message);
           serverOutputPane.setCaretPosition(serverOutputPane.getDocument().getLength());
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    public void hide(){
        frame.setVisible(false);
    }

    public void show(){
        frame.setVisible(true);
    }

    public void SetLabel(String text){
        serverOutputLabel.setText(text);
    }

}
