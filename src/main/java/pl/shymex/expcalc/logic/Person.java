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

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public void addProduct(Product product) {
        products.add(product);
        moneySpent += product.getPrice();
        for(int i=0; i<product.getConsumer().length(); i++) {
            for (Person person : debtors.keySet()) {
                if (person.getInitial().charAt(0) == product.getConsumer().charAt(i)) {
                    debtors.put(person, debtors.get(person) + product.getPrice()/product.getConsumer().length());
                }
            }
        }
    }

    public void setDebtors(Person person) {
        debtors.put(person, 0.0);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

