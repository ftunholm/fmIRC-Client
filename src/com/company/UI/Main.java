package com.company.UI;

import com.company.ChatClient;
import com.company.Util.WrapEditorKit;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {

    protected JTextPane mainTextArea;
    protected JButton connectBtn;
    protected JTextField tfPort;
    protected JTextField tfIp;
    public JTextField nick;
    protected JTextField chat;
    protected JList onlineList;
    protected JFrame window;
    protected Image connectImg;
    protected Image disconnectImg;
    protected DefaultListModel model;
    public JTabbedPane tabbedPane;
    public static int tabCount = 0;

    public Main() throws IOException {
        createAndShowGUI();
    }

    public static void main(String[] args) throws IOException {
        new ChatClient();
    }

    private void createAndShowGUI() throws IOException {
        //Set the active tab background to white
        UIManager.put("TabbedPane.selected", Color.DARK_GRAY);
        //Set the inactive tab background to black
        UIManager.put("TabbedPane.unselectedTabBackground", Color.BLACK);
        //Remove borders
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));

        connectImg = ImageIO.read(getClass().getResource("/com/company/resources/connect.png")).getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        disconnectImg = ImageIO.read(getClass().getResource("/com/company/resources/disconnect.gif")).getScaledInstance(22, 22, Image.SCALE_SMOOTH);

        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 12);
        Font consoleFont = new Font("Consolas", Font.BOLD, 12);

        setIconImage(connectImg);
        setTitle("fmIRC+");
        setMinimumSize(new Dimension(950, 650));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel ipLab = new JLabel("IP address: ");
        JLabel portLab = new JLabel("Port: ");
        JLabel nickLab = new JLabel("Nickname: ");
        ipLab.setFont(consoleFont);
        portLab.setFont(consoleFont);
        nickLab.setFont(consoleFont);
        ipLab.setForeground(Color.WHITE);
        portLab.setForeground(Color.WHITE);
        nickLab.setForeground(Color.WHITE);

        tfIp = new JTextField(10);
        tfIp.setBorder(blackBorder);
        tfIp.setFont(font);

        tfPort = new JTextField(10);
        tfPort.setBorder(blackBorder);
        tfPort.setFont(font);

        chat = new JTextField();
        chat.setFont(font);
        chat.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), BorderFactory.createEmptyBorder(2, 2, 2, 2)));

        nick = new JTextField(10);
        nick.setBorder(blackBorder);
        nick.setFont(font);

        connectBtn = new JButton();
        connectBtn.setToolTipText("Connect");
        connectBtn.setMargin(new Insets(1, 3, 1, 3));
        connectBtn.setBackground(Color.BLACK);
        connectBtn.setIcon(new ImageIcon(connectImg));

        mainTextArea = new JTextPane();
        mainTextArea.setFont(consoleFont);
        mainTextArea.setEditable(false);
        mainTextArea.setBorder(blackBorder);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setEditorKit(new WrapEditorKit());

        JLabel onlineLab = new JLabel("ONLINE LIST");
        onlineLab.setFont(consoleFont);
        onlineLab.setForeground(Color.YELLOW);
        onlineLab.setHorizontalAlignment(JLabel.CENTER);
        onlineLab.setVerticalAlignment(JLabel.CENTER);

        model = new DefaultListModel();
        onlineList = new JList(model);
        onlineList.setSelectionBackground(Color.DARK_GRAY);
        onlineList.setSelectionForeground(Color.WHITE);
        onlineList.setFixedCellWidth(150);
        onlineList.setFont(consoleFont);
        onlineList.setBackground(Color.BLACK);
        onlineList.setForeground(Color.WHITE);
        onlineList.setBorder(blackBorder);

        JScrollPane scrollableOnlineList = new JScrollPane(onlineList);
        scrollableOnlineList.setBackground(Color.BLACK);
        scrollableOnlineList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 8, 5, 0), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        scrollableOnlineList.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollableOnlineList.getHorizontalScrollBar().setMinimumSize(new Dimension(0, 0));
        scrollableOnlineList.getHorizontalScrollBar().setMaximumSize(new Dimension(0, 0));
        scrollableOnlineList.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollableOnlineList.getVerticalScrollBar().setMinimumSize(new Dimension(0, 0));
        scrollableOnlineList.getVerticalScrollBar().setMaximumSize(new Dimension(0, 0));

        JScrollPane scrollableTextArea = new JScrollPane(mainTextArea);
        scrollableTextArea.setBackground(Color.BLACK);
        scrollableTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.getHorizontalScrollBar().setUI(CustomUI.getCustomScrollUI());
        scrollableTextArea.getVerticalScrollBar().setUI(CustomUI.getCustomScrollUI());
        DefaultCaret caret = (DefaultCaret) mainTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JPanel leftInnerPanel = new JPanel();
        leftInnerPanel.setBackground(Color.BLACK);
        leftInnerPanel.add(connectBtn);

        JPanel rightInnerPanel = new JPanel();
        rightInnerPanel.setBackground(Color.BLACK);
        rightInnerPanel.add(nickLab);
        rightInnerPanel.add(nick);
        rightInnerPanel.add(ipLab);
        rightInnerPanel.add(tfIp);
        rightInnerPanel.add(portLab);
        rightInnerPanel.add(tfPort);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.add(rightInnerPanel, BorderLayout.CENTER);
        topPanel.add(leftInnerPanel, BorderLayout.WEST);

        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.setBackground(Color.BLACK);
        eastPanel.add(onlineLab, BorderLayout.NORTH);
        eastPanel.add(scrollableOnlineList, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(scrollableTextArea, BorderLayout.CENTER);
        centerPanel.add(chat, BorderLayout.SOUTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.setUI(CustomUI.getCustomTabbedUI());
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBackground(Color.BLACK);
        addTab("#fmIRC+", centerPanel, tabbedPane);

        JPanel mainCenterPanel = new JPanel(new BorderLayout());
        mainCenterPanel.setBackground(Color.BLACK);
        mainCenterPanel.add(topPanel, BorderLayout.NORTH);
        mainCenterPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);

        window = this;
        add(mainPanel);
        setVisible(true);
        pack();
    }

    public static void addTab(String title, JPanel panel, JTabbedPane tabbedPane) {
        tabbedPane.add(title, new JLabel(title));
        tabbedPane.setTabComponentAt(tabCount, new ButtonTabComponent(tabbedPane));
        tabbedPane.setComponentAt(tabCount, panel);
        tabCount++;
    }
}

