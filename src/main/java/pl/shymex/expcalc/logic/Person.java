package pl.shymex.expcalc.logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private String name;
    private ArrayList<Product> products = new ArrayList<>();
    private double moneySpent = 0;
    private String initial;
    Map<Person, Double> debtors = new HashMap<>();

    public Person(String name, String initial) {
        this.name = name;
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getInitial() {
        return initial;
    }

    public void addProduct(Product product) {
        products.add(product);
        moneySpent += product.getPrice();
        for (Person person : debtors.keySet()) {
            if (product.getConsumer().contains(person.getInitial()) && !this.equals(person)) {
                debtors.put(person, debtors.get(person) + product.getPrice()/product.getConsumer().length());
            }
        }
    }

    public void setDebtors(Person person) {
        debtors.put(person, 0.0);
    }
    public void editDebtor(Person person, Double price) {
        debtors.replace(person, price);
    }

    public Map<Person, Double> getDebtors() {
        return debtors;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public boolean isConsumer(Product product) {
        if (product.getConsumer().contains(this.initial)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

