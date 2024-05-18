/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.conversorpessoa.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Diogo Kumizaki
 */
public class EntityManagerUtil {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    private EntityManagerUtil() {}
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf == null) {
            emf = Persistence
                    .createEntityManagerFactory("pessoas");
            System.out.println("conexão aberta!");
        }
        return emf;
    }
    
    public static void closeEntityManagerFactory() {
        if(emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("conexão fechada!");
        }
    }
    
    public static EntityManager getManager() {
        if(em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            System.out.println("entity manager aberta!");
        }
        return em;
    }
}
