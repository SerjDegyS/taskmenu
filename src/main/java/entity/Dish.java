package entity;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Create by Serj Degys
 */

@Entity
@Table(name = "Menu")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price_in_$", nullable = false)
    private Double price;

    @Column(name = "weight_in_grams", nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private Boolean hasDiscount;

    public Dish() {};

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getWeight() {
        return weight;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHasDiscount(Boolean discount) {
        this.hasDiscount = discount;
    }

    public static Dish readDishFromConsole(){
        Scanner scan = new Scanner(System.in);
        Dish dish = new Dish();
        System.out.println("Enter dish name and his property like" +
                "\n (name; price in $; weight in grams; has discount(true or false);):");
        String [] stringDish = scan.nextLine().split(";");
        if(stringDish.length == 4){
            dish.setName(stringDish[0]);
            dish.setPrice(Double.parseDouble(stringDish[1]));
            dish.setWeight(Integer.parseInt(stringDish[2].trim()));
            dish.setHasDiscount(Boolean.parseBoolean(stringDish[3].trim()));
        }else {
            System.out.println("Wrong input! Try again:");
            return null;
        }
        return dish;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", hasDiscount=" + hasDiscount +
                '}';
    }
}
