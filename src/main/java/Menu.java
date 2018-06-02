

import entity.Dish;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Menu {

    private EntityManagerFactory emf;
    private EntityManager em;

    public Menu() {
        this.emf = Persistence.createEntityManagerFactory("UnitMenu");
        this.em = emf.createEntityManager();
    }

    public Dish addDish(Dish dish) {
        try {
            em.getTransaction().begin();
            em.persist(dish);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        return dish;
    }

    public List<Dish> getAllDish() {
        List<Dish> list = null;
        try {
            Query query = em.createQuery("select dish from Dish dish", Dish.class);
            list =  query.getResultList();
        } catch (NoResultException ex) {
            System.out.println("Dish not found!");
        }
        return list;
    }

    public List<Dish> hasDiscount(){
        List<Dish> list = null;
        try{
            Query query = em.createQuery("select dish from Dish dish where dish.hasDiscount = true", Dish.class);
        list = query.getResultList();
        }catch(NoResultException ex){
            System.out.println("Dishes not found!");
        }
        return list;
    }

    public List<Dish> priceFromTo(Double ...lowAndHightPrice){
        List<Dish> list = null;
        try{
            Query query = em.createQuery("select dish from Dish dish where dish.price >= :lowPrice and dish.price <= :hightPrice", Dish.class);
            query.setParameter("lowPrice", lowAndHightPrice[0]);
            query.setParameter("hightPrice", lowAndHightPrice[1]);
            list = query.getResultList();
        }catch (NoResultException ex){
            System.out.println("No dishes found with a price from " +
            + lowAndHightPrice[0] + " to " + lowAndHightPrice[1]);
        }
        return list;
    }

    public List<Dish> totalWeightUpToKg(){
        List<Dish> list = new ArrayList<>();
        int weight = 0;

        Query query = em.createQuery("select count(dish) from Dish dish");
        Long count =(long)query.getSingleResult();
        System.out.println(count);
        Query query1 = em.createQuery("select dish from Dish dish where dish.id = :id", Dish.class);
        while (weight < 1000){
            System.out.println((long)(Math.random()*count));
            query1.setParameter("id",(int)((Math.random()*count)+1));
//            query1.setParameter("id", 3);
            Dish dish = (Dish) query1.getSingleResult();
            weight += dish.getWeight();

            list.add(dish);
        }

        return list;
    }

    public void closeAllManager(){
        em.close();
        emf.close();
    }
}
