/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chris.brkopani.gui.analytics;

import com.alee.laf.WebLookAndFeel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author xristos
 */
public class DesktopAnalytics {

    private JDesktopPane desk;
    private FirstAndLast fal;
    private Graph g;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu toastTest;

    private final String destinationFile = System.getProperty("java.io.tmpdir");
    private final String imgRes = destinationFile + "img.jpg";
    private final String imgRes_blur = destinationFile + "img_blur.jpg";

    public void createAndShowgui() {

        WebLookAndFeel.install();
        // Create the frame
        frame = new JFrame("reLine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon32_32.png"));
        this.getClass().getResource("res/icon32_32.png");
        //Create the desktop pane
        desk = new JDesktopPane();
        loadBackgroundImage();
        //add menu bar

        //Create all the internal frames
        fal = new FirstAndLast();
        fal.createForms(desk);
        
        //graph
        g = new Graph();
        g.createAndShowGui(desk);
        
        
       
        frame.add(desk);

        frame.setSize(1200, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Add backgroun image to the desktop
    protected final void loadBackgroundImage() {
        ImageIcon icon = new ImageIcon(imgRes);
        JLabel l = new JLabel(icon);
        l.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        // Place the image in the lowest possible layer so nothing
        // can ever be painted under it.
        desk.add(l, new Integer(Integer.MIN_VALUE));
    }

}
