package com.niit.artist.test.dao;

import com.niit.artist.config.AppConfig;

import com.niit.artist.dao.ArtistDAO;
import com.niit.artist.dao.ArtistDAOImpl;
import com.niit.artist.model.Artist;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Query;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration(classes = { AppConfig.class })
public class ArtistDAOTest {
    @Autowired
    private SessionFactory sessionFactory;
    private ArtistDAO artistDAO;
    private Artist artist;

    @BeforeEach
    void setUp()
    {
        artistDAO = new ArtistDAOImpl(sessionFactory);
        artist = new Artist();
        artist.setArtistId(100);
        artist.setArtistName("Taylor Swift");
        artist.setUrl("http://taylorswift.com/");
        artist.setImageUrl("https://www.instagram.com/taylorswift/?hl=en");
        artist.setImageSpecs("300x200");
    }
    @AfterEach
    void tearDown()
    {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Artist");
        query.executeUpdate();
    }


    @Test
     public void testSaveArtist() {
        artistDAO.addArtist(artist);
        List<Artist> artistList = artistDAO.listAllArtist();
        assertEquals("Taylor Swift", artistList.get(0).getArtistName());
    }

    @Test

    public void testDeleteArtist() {
        artistDAO.addArtist(artist);
        Artist data = artistDAO.getArtistById(artist.getArtistId());
        boolean status = artistDAO.deleteArtistById(artist.getArtistId());
        assertEquals(true, status);
    }
    @Test

    public void testGetArtistById() {
        artistDAO.addArtist(artist);
       	Artist data = artistDAO.getArtistById(artist.getArtistId());
        assertEquals(artist, data);
        artistDAO.deleteArtistById(artist.getArtistId());
    }
    @Test

    public void testUpdateArtist()
    {

        artistDAO.addArtist(artist);
        Artist data1 = artistDAO.getArtistById(artist.getArtistId());
        data1.setArtistName("Rihanna");
        data1.setUrl("http://www.rihannanow.com/");
        data1.setImageUrl("https://www.instagram.com/badgalriri/");
        data1.setImageSpecs("20x50");
        Artist artist1 = artistDAO.updateArtist(data1);
        Artist updatedArtist = artistDAO.getArtistById(artist.getArtistId());
        assertEquals("Rihanna", updatedArtist.getArtistName());
        artistDAO.deleteArtistById(updatedArtist.getArtistId()); }

    @Test
      public void testGetAllArtists() {
        Artist artist1 = new Artist();
        artist1.setArtistId(1);
        artist1.setArtistName("Rihanna");
        artist1.setUrl("http://www.rihannanow.com/");
        artist1.setImageUrl("https://www.instagram.com/badgalriri/");
        artist1.setImageSpecs("20x50");
        Artist artist2 = new Artist();
        artist2.setArtistId(2);
        artist2.setArtistName("Taylor Swift");
        artist2.setUrl("http://taylorswift.com/");
        artist2.setImageUrl("https://www.instagram.com/taylorswift/?hl=en");
        artist2.setImageSpecs("300x200");
        artistDAO.addArtist(artist1);
        artistDAO.addArtist(artist2);
        List<Artist> artists = artistDAO.listAllArtist();
        assertEquals(2, artists.size());

      }

}
