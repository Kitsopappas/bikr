/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chris.getbingsimage;

/**
 *
 * @author xristos
 */
public class SaveImage {

    String destinationFile = System.getProperty("java.io.tmpdir");
    String url = "http://www.bing.com";
    

    public void saveImg() {
        JSONImageFinder jif = new JSONImageFinder();
        System.out.println(jif.img);
        System.out.println(System.getProperty("java.io.tmpdir"));
        
       
        try {
           ImageDownloader.saveImage(url+jif.img, destinationFile+"img.jpg");

        } catch (Exception e1) {

            e1.printStackTrace();
        } finally {

        }

    }
}
