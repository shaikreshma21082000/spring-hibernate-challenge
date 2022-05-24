package com.niit.artist.controller;

import com.niit.artist.dao.ArtistDAO;
import com.niit.artist.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ArtistController {
    /*
     * From the problem statement, we can understand that the application requires
     * us to implement the following functionalities.
     *
     * 1. display the all the details of artists from the database.
     * 2. add a new artist detail into the data base
     * 3. Delete an existing artist by the id
     * 4. Update an existing artist
     *
     */

    /*
     * Autowiring should be implemented for the ArtistDAO.
     * Create a News object.
     *
     */
    @Autowired
    private ArtistDAO artistDAO;

    public ArtistController(ArtistDAO artistDAO) {
    this.artistDAO=artistDAO;
    }

    /*
     * Define a handler method to read all artists from the database and add
     * it to the Model, used when building model data for use with views. it should map to the default URL i.e. "/"
     */
   @GetMapping("/")
    public String homepage(Model model) {
       model.addAttribute("artist",new Artist());
       model.addAttribute("artistList",artistDAO.listAllArtist());
       return "index";
    }

    /*
     * Define a handler method which will read the artist details from the jsp page and save the artist in artist table in
     * database.  This methos must map to the url /addArtist . After saving the artist, it should show the same along with existing artist items. Hence, this handler
     * method should redirect to the default URL i.e. "/".
     */
    @PostMapping("/addArtist")
    public String addArtist(@ModelAttribute("artist")Artist artist,ModelMap modelMap)
    {
        boolean status = artistDAO.addArtist(artist);
        modelMap.addAttribute("artistList",this.artistDAO.listAllArtist());
        return "index";
    }


    /*
     * Define a handler method which will update the existing artist. This handler
     * method should map to the URL /updateArtist.
     */
    @GetMapping("update/{artistId}")
    public ModelAndView updateArtist(@PathVariable int artistId) {
        ModelAndView model=new ModelAndView("index");
        Artist artist=artistDAO.getArtistById(artistId);
        model.addObject("artist",artist);
        model.addObject("artistList",artistDAO.listAllArtist());
        artistDAO.updateArtist(artist);
        return model;
    }



    /*
     * Define a handler method which will read the artistId from request parameters
     * and remove an existing artist by calling the deleteArtist() method of the
     *  ArtistDAO class.This handler method should map to the URL "/deleteArtist". Post
     * deletion, the handler method should be redirected to the default URL i.e. "/"
     */
@GetMapping("delete/{artistId}")
    public ModelAndView deleteArtist(@PathVariable("artistId") int artistId)
    {
        ModelAndView model =new ModelAndView("index");
        artistDAO.deleteArtistById(artistId);
        model.addObject("artistList",this.artistDAO.listAllArtist());
        System.out.println("inside controller delete");
        return model;
    }

}
