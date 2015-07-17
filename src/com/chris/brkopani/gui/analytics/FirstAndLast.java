/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chris.brkopani.gui.analytics;

import com.alee.laf.WebLookAndFeel;
import com.chris.brkopani.gui.GetRect;
import com.chris.brkopani.logic.MySQLAccess;
import java.awt.Color;
import java.sql.SQLException;
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
    JTabbedPane tabbedPane;

    public void createForms(JDesktopPane desk) throws SQLException {

        //Δημιουργία tabed pane και εισαγωγή φορμών με εικόνες
        WebLookAndFeel.install();
        frame = new JInternalFrame("Κατάταξη", true, true, true, true);
        frame.setFrameIcon(new ImageIcon("res/br.png"));
        frame.setBounds(5, 5, 520, 350);
        frame.getContentPane().getBackground();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("5-20", new ImageIcon(""), rankPanels(GetRect.AGE_MIN), "Tab 1");
        tabbedPane.addTab("21-35", new ImageIcon(""), rankPanels(GetRect.AGE_MID), "Tab 2");
        tabbedPane.addTab("35-..", new ImageIcon(""), rankPanels(GetRect.AGE_MAX), "Tab 3");
        tabbedPane.addTab("All", new ImageIcon(""), rankPanels(GetRect.ALL), "Tab 4");

        frame.add(tabbedPane);
        frame.setVisible(true);
        desk.add(frame);

    }

    public JPanel rankPanels(int age_value) throws SQLException {
        JPanel p = new JPanel();
        dtm = new DefaultTableModel();
        dtm.addColumn("Αριθμός");
        dtm.addColumn("Όνομα");
        dtm.addColumn("Επόνυμο");
        dtm.addColumn("Ηλικία");
        dtm.addColumn("Βάρος");
        dtm.addColumn("Πόλη");
        dtm.addColumn("Χρόνος");

        table = new JTable(dtm);
        MySQLAccess.rankSelect(dtm, age_value);
        table.revalidate();

        table.setBackground(Color.LIGHT_GRAY);
        p.add(new JScrollPane(table));
        return p;
    }
}
