/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.suyuti.homework_2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;
/**
 *
 * @author mehmet.dindar
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("homework2-pu");
        EntityManager em = emf.createEntityManager();
        PersistTest(em);
        FindTest(em);
        QueryTest(em);
        RemoveTest(em);
        DetachTest(em);
        ClearTest(em);
        FlushTest(em);
    }
    
    public static void PersistTest(EntityManager em) {
        EntityTransaction tr = em.getTransaction();

        Takim t = new Takim();
        t.setAd("Takım1");
        t.setBaskan("Baskan1");
        t.setKurulus(1900);
        t.setStad("Stad1");
        
        Futbolcu f1 = new Futbolcu();
        f1.setAd("Futbolcu1");
        f1.setMevki("On");
        f1.setNumara(1);
        f1.setYas(20);
        
        Futbolcu f2 = new Futbolcu();
        f2.setAd("Futbolcu2");
        f2.setMevki("Sol");
        f2.setNumara(2);
        f2.setYas(30);
        
        t.setOyuncular(Arrays.asList(f1, f2));

        tr.begin();
        em.persist(f1);
        em.persist(f2);
        em.persist(t);
        tr.commit();
        
        
        // test....
        Long takimCount      = em.createQuery("SELECT COUNT(t) FROM Takim t", Long.class).getSingleResult();
        Long futbolcuCount   = em.createQuery("SELECT COUNT(f) FROM Futbolcu f", Long.class).getSingleResult();
        if (takimCount != 1) { // hard coded :(
            System.out.println("Takim sayisi hatali");
            return;
        }
        if (futbolcuCount != 2) {
            System.out.println("Futbolcu sayisi hatali");
            return;
        }
        System.out.println("Persist OK");
    }

    public static void FindTest(EntityManager em) {
        Takim t = em.find(Takim.class, 1L);
        if (t.getAd().equals("Takım1")) {
            System.out.println("Find Takim OK");
        }
        else {
            System.out.println("Find Takim ERROR");
        }
        
        Futbolcu f = em.find(Futbolcu.class, 1L);
        if (f == null) {
            System.out.println("Find Futbolcu ERROR");
        }

        Futbolcu f2 = em.find(Futbolcu.class, 99L);
        if (f2 == null) {
            System.out.println("Find nonexistent Futbolcu OK");
        }
    }
    
    public static void QueryTest(EntityManager em) {
        List<Futbolcu> f = em.createQuery("SELECT f FROM Futbolcu f WHERE f.yas > 25", Futbolcu.class).getResultList();
        if (f.size() != 1) {
            System.out.println("QueryTest ERROR");
            return;
        }
        System.out.println("QueryTest OK");
    }
    
    public static void RemoveTest(EntityManager em) {
        Futbolcu f = em.find(Futbolcu.class, 1L);
        if (f != null) {
            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
        }
        
        Long counter = em.createQuery("SELECT COUNT(f) FROM Futbolcu f", Long.class).getSingleResult();
        if (counter != 1) {
            System.out.println("RemoveTest ERROR");
            return;
        }
        System.out.println("RemoveTest OK");
    }
    
    public static void DetachTest(EntityManager em) {
        Futbolcu f = em.find(Futbolcu.class, 2L);
        if (f != null) {
            em.getTransaction().begin();
            em.detach(f);
            em.getTransaction().commit();
        }
    }
    
    public static void ClearTest(EntityManager em) {
        em.clear();
    }

    public static void FlushTest(EntityManager em) {
        EntityTransaction tr = em.getTransaction();

        Takim t = new Takim();
        t.setAd("flushed 1");
        t.setBaskan("Baskan1");
        t.setKurulus(1900);
        t.setStad("Stad1");
        
        Futbolcu f1 = new Futbolcu();
        f1.setAd("flushed 2");
        f1.setMevki("On");
        f1.setNumara(1);
        f1.setYas(20);
        
        Futbolcu f2 = new Futbolcu();
        f2.setAd("Futbolcu2");
        f2.setMevki("Sol");
        f2.setNumara(2);
        f2.setYas(30);
        
        t.setOyuncular(Arrays.asList(f1, f2));

        tr.begin();
        em.persist(f1);
        em.persist(f2);
        em.flush();
        em.persist(t);
        //tr.commit(); // çalıştığını görelim
        tr.rollback(); //?
    }
}
