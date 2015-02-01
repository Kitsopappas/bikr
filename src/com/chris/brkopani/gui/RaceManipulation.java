package com.chris.brkopani.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xristos
 */
public class RaceManipulation extends JFrame {

    private JTable table;
    private JLabel bikersNumL;
    private JTextField bikersNumTXT;
    private JButton bikerPass;
    private String[] columnNames = new String[]{"Name", "Age", "Weight", "Town", "Time"};
    private String[][] rowData = new String[][]{{"", "", "", "", ""}};
    private final Color col = new Color(86, 86, 86);

    public void createAndShowGui() {
        new JFrame();
        setUndecorated(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1010, 550);
        setShape(new RoundRectangle2D.Double(0, 0, 1010, 550, 15, 15));
        setTitle("Bikr");
        setLocationRelativeTo(null);
        //set image icon for the frame
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/br.png"));
        this.getClass().getResource("res/br.png");
        getContentPane().setBackground(col);
        DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
        table = new JTable(dtm);

        table.setBackground(Color.LIGHT_GRAY);
        table.setEnabled(false);
        add(new JScrollPane(table));

        //add(getRacersNumAndSec(), BorderLayout.SOUTH);

        
    }
/*
    public JPanel getRacersNumAndSec() {
        JPanel p = new JPanel(new GridLayout(0, 2));
        p.setBackground(col);
        bikerPass = new JButton("Passed");
        p.add(bikersNumber());
        p.add(bikerPass);

        return p;
    }

    public JPanel bikersNumber() {
        JPanel p = new JPanel(new GridLayout(2, 0));
        p.setBackground(col);
        //race manip panel
        bikersNumL = new JLabel("Give bikers number!");
        bikersNumL.setForeground(Color.LIGHT_GRAY);
        bikersNumTXT = new JTextField(20);
        p.add(bikersNumL);
        p.add(bikersNumTXT);
        return p;

    }
    */
}
