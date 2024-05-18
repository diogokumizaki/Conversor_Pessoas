/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.conversorpessoa.interfaces;


import br.unipar.conversorpessoa.models.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Diogo Kumizaki
 */
public class PessoaDAOImpl implements PessoaDAO {
    
    private EntityManager entityManager;

    public PessoaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Pessoa save(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(pessoa);
        transaction.commit();
        entityManager.close();
        
        System.out.println("Pessoa salvo com sucesso!");
        return pessoa;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(pessoa);
        transaction.commit();
        entityManager.close();
        
        System.out.println("Pessoa atualizado com sucesso!");
        return pessoa;
    }

    @Override
    public Boolean delete(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            entityManager.remove(pessoa);
            transaction.commit();
            entityManager.close();

            System.out.println("Pessoa removido com sucesso!");
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Pessoa atualizado com sucesso!");
            return false;
        }
    }

    @Override
    public Pessoa findById(Integer id) {
        return entityManager.find(Pessoa.class, id);
    }

    @Override
    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p",
                Pessoa.class).getResultList();
    }
    
}
