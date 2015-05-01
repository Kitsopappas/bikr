package com.chris.brkopani.main;

import com.chris.brkopani.gui.AddingMeatForms;
import com.chris.brkopani.logic.MySQLAccess;
import java.sql.SQLException;

/**
 *
 * @author xristos
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        AddingMeatForms mf = new AddingMeatForms();
        mf.createAndShowGui();
        
        //DesktopAnalytics da = new DesktopAnalytics();
        //da.createAndShowgui();

    }

}
