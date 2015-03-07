/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chris.getbingsimage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author xristos
 */
public class JSONImageFinder {

    String img;
    

    public JSONImageFinder() {
        
        System.out.println(getSource());

        try {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getSource());
            } catch (JSONException ex) {
                Logger.getLogger(JSONImageFinder.class.getName()).log(Level.SEVERE, null, ex);
            }

            JSONArray arr = jsonObject.getJSONArray("images");
            for (int i = 0; i < arr.length(); i++) {
                img = arr.getJSONObject(i).getString("url");

            }
        } catch (JSONException ex) {
            Logger.getLogger(JSONImageFinder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getSource() {
        String inputLine = "";
        try {
            URL url = new URL("http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-US");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuffer response = new StringBuffer();

            try {
                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine + "\n");
                }
                inputLine = response.toString();
            } catch (IOException ex) {
                Logger.getLogger(JSONImageFinder.class.getName()).log(Level.SEVERE, null, ex);
            }

            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(JSONImageFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONImageFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputLine;
    }

}
