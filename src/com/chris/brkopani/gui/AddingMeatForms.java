package com.chris.brkopani.gui;

import com.alee.laf.WebLookAndFeel;
import com.chris.brkopani.logic.MySQLAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author xristos
 */
public class AddingMeatForms {

    private JFrame frame;
    private JTextField bikersNameTXT, bikersLastNameTXT, bikersAgeTXT, bikersWeightTXT, bikersTownTXT;
    private JLabel bikersNameL, bikersLastNameL, bikersAgeL, bikersWeightL, bikersTownL;
    private JButton addBikerBUTTON, startRaceBUTTON;
    private JPanel buttonsPANEL;
    private JLabel bikeImage;
    // private RaceManipulation rm;
   

    public void createAndShowGui() throws SQLException {
        WebLookAndFeel.install();
        //SQL Access
        MySQLAccess.connection();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bikr");
        frame.setSize(350, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //set image icon for the frame
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/br.png"));
        this.getClass().getResource("res/br.png");

        frame.getContentPane().setBackground(GetRect.col);
        //gui content stuff
        addBikerBUTTON = new JButton("Add Biker");
        startRaceBUTTON = new JButton("Start Race");
        buttonsPANEL = new JPanel(new GridLayout(0, 2));
        buttonsPANEL.setBackground(GetRect.col);
        frame.add(buttonsPANEL, BorderLayout.SOUTH);
        bikeImage = new JLabel(new ImageIcon("res/br_big.png"));
        frame.add(bikeImage, BorderLayout.CENTER);
        frame.add(form(), BorderLayout.NORTH);
        buttonsPANEL.add(addBikerBUTTON);
        buttonsPANEL.add(startRaceBUTTON);

        //listeners
        //Start race listener
        startRaceBUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Stop Crap!");
                    frame.dispose();
                    RaceIsUp rup = new RaceIsUp();
                    rup.init();
                } catch (SQLException ex) {
                    Logger.getLogger(AddingMeatForms.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        //on mouse over listener

        bikeImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {

            }

            @Override
            public void mouseExited(MouseEvent evt) {

            }

            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    RaceManipulation rm = new RaceManipulation();
                    rm.createAndShowGui();
                    rm.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddingMeatForms.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                //rm.setVisible(false);
            }
        });
        //add biker listener
        addBikerBUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int age = Integer.parseInt(bikersAgeTXT.getText());
                int weight = Integer.parseInt(bikersWeightTXT.getText());
                MySQLAccess.querys(bikersNameTXT.getText(), bikersLastNameTXT.getText(),
                        age, weight, bikersTownTXT.getText());

            }
        });

        frame.setVisible(true);
    }

    //form to add bikers to database easy
    public JPanel form() {
        JPanel p = new JPanel(new GridLayout(10, 0));
        p.setBackground(GetRect.col);
        bikersNameL = new JLabel("First Name");
        bikersNameL.setForeground(Color.LIGHT_GRAY);
        bikersNameTXT = new JTextField(20);
        bikersLastNameL = new JLabel("Last Name");
        bikersLastNameL.setForeground(Color.LIGHT_GRAY);
        bikersLastNameTXT = new JTextField(20);
        bikersAgeL = new JLabel("Age");
        bikersAgeL.setForeground(Color.LIGHT_GRAY);
        bikersAgeTXT = new JTextField(20);
        bikersWeightL = new JLabel("Weight");
        bikersWeightL.setForeground(Color.LIGHT_GRAY);
        bikersWeightTXT = new JTextField(20);
        bikersTownL = new JLabel("Town");
        bikersTownL.setForeground(Color.LIGHT_GRAY);
        bikersTownTXT = new JTextField(20);

        p.add(bikersNameL);
        p.add(bikersNameTXT);
        p.add(bikersLastNameL);
        p.add(bikersLastNameTXT);
        p.add(bikersAgeL);
        p.add(bikersAgeTXT);
        p.add(bikersWeightL);
        p.add(bikersWeightTXT);
        p.add(bikersTownL);
        p.add(bikersTownTXT);

        return p;
    }

}
