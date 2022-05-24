package com.niit.artist.dao;

import com.niit.artist.model.Artist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
/*
 * This class is implementing the ArtistDAO interface. This class has to be annotated with
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database
 * 					transaction. The database transaction happens inside the scope of a persistence
 * 					context.
 * */

//Write appropriate logic to add, delete, update, list all Artists and get artist by id from the database table
@Repository
@Transactional
public class ArtistDAOImpl implements ArtistDAO {
    /*
     * Autowiring should be implemented for the SessionFactory.
     */
    @Autowired
    private SessionFactory sessionFactory;

    public ArtistDAOImpl(SessionFactory sessionFactory) {
      this.sessionFactory=sessionFactory;
    }

    @Override
    public boolean addArtist(Artist artist) {

        try
        {
            this.sessionFactory.getCurrentSession().saveOrUpdate(artist);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Artist> listAllArtist() {
        return this.sessionFactory.getCurrentSession().createQuery("from Artist").list();
    }

    @Override
    public boolean deleteArtistById(int artistId) {
        boolean flag=false;
        try{
        Artist ar= this.sessionFactory.getCurrentSession().load(Artist.class,artistId);
        System.out.println(ar.toString()+"DISPLAYING DELETED OBJECT");
        this.sessionFactory.getCurrentSession().delete(ar);
        flag= true;
       }
        catch (Exception e){
            e.printStackTrace();
        }
       return flag;
    }

    @Override
    public Artist getArtistById(int artistId) {
       // return this.sessionFactory.getCurrentSession().byId(artistId);
        return this.sessionFactory.getCurrentSession().get(Artist.class,artistId);
    }

    @Override
    public Artist updateArtist(Artist artist) {
        try
        {
            this.sessionFactory.getCurrentSession().saveOrUpdate(artist);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return artist;
    }

}
