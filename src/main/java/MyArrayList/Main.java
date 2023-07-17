package MyArrayList;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList
        MyArrayList list = new MyArrayList();
        list.add(new Person(5, "Name5"));
        list.add(new Person(7, "Name7"));
        list.add(new Person(1, "Name1"));
        list.add(new Person(3, "Name3"));
        list.add(new Person(2, "Name2"));
        list.add(new Person(6, "Name6"));
        list.add(new Person(4, "Name4"));
        System.out.println(list);
        list.sort(list, Comparator.comparingInt(Person::getId));
        System.out.println(list);

        list.set(new Person(9,"For"),4);
        System.out.println(list);
        list.sort(list, Comparator.comparingInt(Person::getId));
        System.out.println(list);

        list.isEmpty();
        list.get(4);
        list.remove(4);
        System.out.println(list);
        list.size();
        list.remove(list.get(3));
        System.out.println(list);
    }

    static class Person implements Comparable<Person> {
        private final int id;
        private final String name;

        public int getId() {
            return id;
        }

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.id, o.getId());
        }
    }
}
