package pl.shymex.expcalc.logic;

import java.time.LocalDate;

public class Product {
    private String name;
    private LocalDate date;
    private double price;
    private Person buyer;
    private String consumer;

    public Product(String name, LocalDate date, double price, Person buyer, String consumer) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.buyer = buyer;
        this.consumer = consumer;
    }


    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
