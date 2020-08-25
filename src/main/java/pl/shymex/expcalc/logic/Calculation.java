package pl.shymex.expcalc.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Calculation {
    private Person persons[] = new Person[3];

    public Calculation(Person p1, Person p2, Person p3) {
        persons[0] = p1;
        persons[1] = p2;
        persons[2] = p3;
        assignDebtors();
        optimizeDebts();
    }

    public void assignDebtors() {
        for (Person person : persons) {
            for (Person another : persons) {
                if (!(person.equals(another))) {
                    person.setDebtors(another);
                }
            }
        }
    }

    public void optimizeDebts() {
        for (Person person : persons) {
            for (Person another : persons) {
                if(!(person.equals(another))) {
                    double d1 = person.debtors.get(another);
                    double d2 = another.debtors.get(person);
                    if (d1 > d2) {
                        person.debtors.put(another, d1 - d2);
                        another.debtors.put(person, 0.0);
                    } else {
                        another.debtors.put(person, d2 - d1);
                        person.debtors.put(another, 0.0);
                    }
                }

            }
        }
    }
    public void removeProductFromDebtors(Person person, Product product) {
        Map<Person, Double> mapCopy = new HashMap<>();
        mapCopy.putAll(person.getDebtors());
        for (Person another : mapCopy.keySet()) {
            if (another.isConsumer(product)) {
                double value = person.getDebtors().get(another);
                person.editDebtor(another, value
                        - product.getPrice()/product.getConsumer().length());

                System.out.println(person.getDebtors());
            }

        }
    }
}

