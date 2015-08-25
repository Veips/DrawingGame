import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Game {

    static JPanel gamePanel;
    static JScrollPane scrollPane1;
    static JScrollPane drawArea;
    static JLabel brushSizeLabel;
    static JSlider brushSizeSlider;
    static JLabel brushSizeIndicatorLabel;
    static JLabel brushColorLabel;
    static JButton button1;
    static JButton button2;
    static JButton button3;
    static JButton button4;
    static JButton button5;
    static JButton button6;
    static JButton button7;
    static JButton button8;
    static JButton button9;
    static JButton button10;
    static JButton button11;
    static JButton button12;
    static JScrollPane scrollPane3;
    static JTextField chatTextField;
    static JScrollPane scrollPane4;
    static JTextArea chatArea;
    static JFrame frame;

    public void init() {

        Client client = new Client();

        initComponents();

        frame = new JFrame("Drawing Game");
        frame.setSize(new Dimension(1115, 715));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(gamePanel);

        chatTextField.addActionListener(e -> {
            if (chatTextField.getText().length() > 0) {
                client.sendMessage(client.getUsername() + ": " + chatTextField.getText());
                chatTextField.setText("");
            }
        });

        }


    public void chatText(String message){
        String currentText = chatArea.getText();
        chatArea.setText(currentText + "\n" + message);
    }

    public void show(){
        frame.setVisible(true);
    }

    private void initComponents() {
        gamePanel = new JPanel();
        scrollPane1 = new JScrollPane();
        drawArea = new JScrollPane();
        brushSizeLabel = new JLabel();
        brushSizeSlider = new JSlider();
        brushSizeIndicatorLabel = new JLabel();
        brushColorLabel = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        scrollPane3 = new JScrollPane();
        chatTextField = new JTextField();
        scrollPane4 = new JScrollPane();
        chatArea = new JTextArea();

        //======== gamePanel ========
        {
            gamePanel.setBackground(new Color(225, 225, 225));

            // JFormDesigner evaluation mark
            gamePanel.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), gamePanel.getBorder())); gamePanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            gamePanel.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(drawArea);
            }
            gamePanel.add(scrollPane1);
            scrollPane1.setBounds(20, 60, 720, 520);

            //---- brushSizeLabel ----
            brushSizeLabel.setText("Brush Size");
            brushSizeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            gamePanel.add(brushSizeLabel);
            brushSizeLabel.setBounds(20, 590, 80, 35);

            //---- brushSizeSlider ----
            brushSizeSlider.setMaximum(60);
            brushSizeSlider.setValue(5);
            brushSizeSlider.setMinimum(1);
            brushSizeSlider.setBackground(new Color(225, 225, 225));
            brushSizeSlider.setForeground(Color.black);
            gamePanel.add(brushSizeSlider);
            brushSizeSlider.setBounds(110, 590, 200, 35);

            //---- brushSizeIndicatorLabel ----
            brushSizeIndicatorLabel.setText("5");
            brushSizeIndicatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            brushSizeIndicatorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            gamePanel.add(brushSizeIndicatorLabel);
            brushSizeIndicatorLabel.setBounds(310, 590, 40, 35);

            //---- brushColorLabel ----
            brushColorLabel.setText("Brush Color");
            brushColorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            gamePanel.add(brushColorLabel);
            brushColorLabel.setBounds(20, 635, 95, 35);

            //---- button1 ----
            button1.setBackground(Color.red);
            button1.setBorder(new LineBorder(Color.red, 10));
            gamePanel.add(button1);
            button1.setBounds(125, 635, 15, 15);

            //---- button2 ----
            button2.setBackground(Color.pink);
            button2.setBorder(new LineBorder(Color.pink, 10));
            gamePanel.add(button2);
            button2.setBounds(125, 655, 15, 15);

            //---- button3 ----
            button3.setBackground(Color.yellow);
            button3.setBorder(new LineBorder(Color.yellow, 10));
            gamePanel.add(button3);
            button3.setBounds(145, 655, 15, 15);

            //---- button4 ----
            button4.setBackground(Color.orange);
            button4.setBorder(new LineBorder(Color.orange, 10));
            gamePanel.add(button4);
            button4.setBounds(145, 635, 15, 15);

            //---- button5 ----
            button5.setBackground(Color.cyan);
            button5.setBorder(new LineBorder(Color.cyan, 10));
            gamePanel.add(button5);
            button5.setBounds(185, 635, 15, 15);

            //---- button6 ----
            button6.setBackground(Color.green);
            button6.setBorder(new LineBorder(Color.green, 10));
            gamePanel.add(button6);
            button6.setBounds(165, 635, 15, 15);

            //---- button7 ----
            button7.setBackground(Color.blue);
            button7.setBorder(new LineBorder(Color.blue, 10));
            gamePanel.add(button7);
            button7.setBounds(185, 655, 15, 15);

            //---- button8 ----
            button8.setBackground(Color.magenta);
            button8.setBorder(new LineBorder(Color.magenta, 10));
            gamePanel.add(button8);
            button8.setBounds(165, 655, 15, 15);

            //---- button9 ----
            button9.setBackground(Color.darkGray);
            button9.setBorder(new LineBorder(Color.darkGray, 10));
            gamePanel.add(button9);
            button9.setBounds(215, 655, 15, 15);

            //---- button10 ----
            button10.setBackground(Color.gray);
            button10.setBorder(new LineBorder(Color.gray, 10));
            gamePanel.add(button10);
            button10.setBounds(235, 635, 15, 15);

            //---- button11 ----
            button11.setBackground(Color.black);
            button11.setBorder(new LineBorder(Color.black, 10));
            gamePanel.add(button11);
            button11.setBounds(215, 635, 15, 15);

            //---- button12 ----
            button12.setBackground(Color.white);
            button12.setBorder(new LineBorder(Color.white, 10));
            gamePanel.add(button12);
            button12.setBounds(235, 655, 15, 15);

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(chatTextField);
            }
            gamePanel.add(scrollPane3);
            scrollPane3.setBounds(750, 590, 355, 75);

            //======== scrollPane4 ========
            {

                //---- chatArea ----
                chatArea.setFocusable(false);
                chatArea.setEditable(false);
                chatArea.setBackground(Color.white);
                scrollPane4.setViewportView(chatArea);
            }
            gamePanel.add(scrollPane4);
            scrollPane4.setBounds(750, 200, 355, 381);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < gamePanel.getComponentCount(); i++) {
                    Rectangle bounds = gamePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = gamePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                gamePanel.setMinimumSize(preferredSize);
                gamePanel.setPreferredSize(preferredSize);
            }
        }
    }


}
