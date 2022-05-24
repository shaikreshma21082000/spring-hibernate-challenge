package com.niit.artist.test.controller;

import com.niit.artist.config.AppConfig;
import com.niit.artist.controller.ArtistController;
import com.niit.artist.dao.ArtistDAO;
import com.niit.artist.model.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class })
public class ArtistControllerTest {
    private MockMvc mockMvc;
    private Artist artist;
    DateTimeFormatter fmt;
    @Mock
    private ArtistDAO artistDAO;
    @InjectMocks
    private ArtistController artistController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(artistController).build();
        artist = new Artist();
        artist.setArtistId(100);
        artist.setArtistName("Taylor Swift");
        artist.setUrl("http://taylorswift.com/");
        artist.setImageUrl("https://www.instagram.com/taylorswift/?hl=en");
        artist.setImageSpecs("300x200");

    }

    @Test
    public void testIndexPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(forwardedUrl("index"));
    }

    @Test
    public void testAddArtistSuccess() throws Exception {
        when(artistDAO.addArtist(any(Artist.class))).thenReturn(true);
        mockMvc.perform(post("/addArtist")
                .param("artistId", "")
                .param("artistName", artist.getArtistName())
                .param("url", artist.getUrl())
                .param("imageUrl", artist.getImageUrl())
                .param("imageSpecs", artist.getImageSpecs()))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/"));
    }

    @Test
    public void testAddArtistFailure() throws Exception {
        when(artistDAO.addArtist(any(Artist.class))).thenReturn(false);
        mockMvc.perform(post("/addArtist")
                .param("artistId", "")
                .param("artistName", artist.getArtistName())
                .param("url", artist.getUrl())
                .param("imageUrl", artist.getImageUrl())
                .param("imageSpecs", artist.getImageSpecs()))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/"));
    }
    @Test
    public void testDeleteArtist() throws Exception {
        when(artistDAO.deleteArtistById(artist.getArtistId())).thenReturn(true);
        mockMvc.perform(get("/deleteArtist/100"))
                .andExpect(redirectedUrl("/"));

    }
    @Test
    public void testUpdateArtist() throws Exception {
        when(artistDAO.updateArtist(any(Artist.class))).thenReturn(artist);
        mockMvc.perform(get("/updateArtist/100"))
                .andExpect(forwardedUrl("index"));

        when(artistDAO.updateArtist(any(Artist.class))).thenReturn(artist);

        mockMvc.perform(post("/addArtist")
                .param("artistId", "")
                .param("artistName", artist.getArtistName())
                .param("url", artist.getUrl())
                .param("imageUrl", artist.getImageUrl())
                .param("imageSpecs", artist.getImageSpecs()))
                .andExpect(status().isFound()).andExpect(redirectedUrl("/"));

    }



}
