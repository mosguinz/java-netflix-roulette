/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosguinz.javanetflixroulette;

/**
 *
 * @author Mos
 */
public class NetflixTitle {
    final String netflixID;
    final String title;
    final String imageURL;
    final String synopsis;
    final String rating;
    final String type;
    final String releaseYear;
    final String runtime;
    
    NetflixTitle(String title) {
        this.netflixID = "netflixID";
        this.title = title;
        this.imageURL = "imageURL";
        this.synopsis = "synopsis";
        this.rating = "rating";
        this.type = "type";
        this.releaseYear = "releaseYear";
        this.runtime = "runtime";
    }
    
    NetflixTitle(String netflixID, String title, String imageURL,
            String synopsis, String rating, String type, String releaseYear,
            String runtime) {
        this.netflixID = netflixID;
        this.title = title;
        this.imageURL = imageURL;
        this.synopsis = synopsis;
        this.rating = rating;
        this.type = type;
        this.releaseYear = releaseYear;
        this.runtime = runtime;
        
        
    }
    
}