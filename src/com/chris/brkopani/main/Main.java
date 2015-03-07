package com.chris.brkopani.main;

import com.chris.brkopani.gui.analytics.DesktopAnalytics;
import java.sql.SQLException;

/**
 *
 * @author xristos
 */
public class Main {

    public static void main(String[] args) throws SQLException {
       // AddingMeatForms mf = new AddingMeatForms();
       // mf.createAndShowGui();
        
        DesktopAnalytics da = new DesktopAnalytics();
        da.createAndShowgui();

    }

}
