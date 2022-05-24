package com.niit.artist.dao;

import com.niit.artist.model.Artist;

import java.util.List;

//Do not modify the interface
public interface ArtistDAO {
    boolean addArtist(Artist artist);
    List<Artist> listAllArtist();
    boolean deleteArtistById(int artistId);
    Artist getArtistById(int artistId);
    Artist updateArtist(Artist artist);
}
