/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chris.brkopani.gui.analytics;

import com.alee.laf.WebLookAndFeel;
import com.chris.brkopani.logic.MySQLAccess;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xristos
 */
public class FirstAndLast {

    static JInternalFrame frame;
    private DefaultTableModel dtm;
    private JTable table;

    public void createForms(JDesktopPane desk) {

            //Δημιουργία tabed pane και εισαγωγή φορμών με εικόνες
        WebLookAndFeel.install();
        frame = new JInternalFrame("Κατάταξη", true, true, true, true);
        frame.setFrameIcon(new ImageIcon("res/br.png"));
        frame.setBounds(5, 5, 520, 350);
        frame.getContentPane().getBackground();

        dtm = new DefaultTableModel();
        dtm.addColumn("Αριθμός");
        dtm.addColumn("Όνομα");
        dtm.addColumn("Επόνυμο");
        dtm.addColumn("Ηλικία");
        dtm.addColumn("Βάρος");
        dtm.addColumn("Πόλη");
        dtm.addColumn("Χρόνος");

        table = new JTable(dtm);
        //MySQLAccess.selectAll(dtm);
        table.revalidate();

        table.setBackground(Color.LIGHT_GRAY);
        frame.add(new JScrollPane(table));

        frame.setVisible(true);
        desk.add(frame);

    }
}
