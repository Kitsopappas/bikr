package com.chris.getbingsimage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class BingsImageFinder {

    String imgSrc = "";
    String x;

    public void image() {
        try {
            URL htmlUrl = new URL("http://www.bing.com");
            URLConnection htmlConn = htmlUrl.openConnection();
            htmlConn.connect();
            HttpURLConnection htmlHttpConn = (HttpURLConnection) htmlConn;

            if ((int) htmlHttpConn.getResponseCode() != 200) {
                System.out.println("Cannot open connection.");
            } else {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(htmlConn.getInputStream()));
                String str = "";
                while ((str = reader.readLine()) != null) {
                    if (str.lastIndexOf("{url:") > 0) {
                        imgSrc = str.substring((str.lastIndexOf("{url:") + 7),
                                (str.lastIndexOf(".jpg',") + 4));
                    }
                }
                imgSrc = "http://www.bing.com" + imgSrc; // The source image url from Bing search engine.
                System.out.println(imgSrc);
                x = imgSrc;
            }
        } catch (Exception ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void setImg(String x) {
        this.x = x;
    }

    public String getImg() throws IllegalStateException {
        return x;
    }
}
