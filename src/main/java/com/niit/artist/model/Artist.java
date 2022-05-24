package com.niit.artist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * The class "Artist" will be acting as the data model for the artist Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the
 * process of looking through that particular Java object to recreate it as a table in your database.
 *
 * The Artist must have an auto generated id field artistId, this value must not be entered by the user.
 */
@Entity
public class Artist {
    //Use the @Id for the artistId and it must be auto generated.
   @Id
    private int artistId;
    private String artistName;
    private String url;
    private String imageUrl;
    private String imageSpecs;

    // Write the getters and setters and the constructors appropriately

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageSpecs() {
        return imageSpecs;
    }

    public void setImageSpecs(String imageSpecs) {
        this.imageSpecs = imageSpecs;
    }

    public Artist() {
    }

    public Artist(int artistId, String artistName, String url, String imageUrl, String imageSpecs) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.url = url;
        this.imageUrl = imageUrl;
        this.imageSpecs = imageSpecs;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageSpecs='" + imageSpecs + '\'' +
                '}';
    }
}
