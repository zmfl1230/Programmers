package Hash;
import java.util.*;

class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    // hashcode의 생상 상 번거로움을 막기 위해 자바에선 hash 값을 만드는 함수는 제공한다.
    // @Override
    // public int hashCode() {
    //     return Objects.hash(name, age);
    // }
    
    // hash 값 자체 생성
    @Override
    public int hashCode() {
        return (name.hashCode() + age) / 2;
    }

    @Override
    public boolean equals(Object obj) {
        Person compared = (Person)obj;
        if(name.equals(compared.name) && age == compared.age) return true;
        return false;
    }



}
public class HashAlgorithm {
    public static void main(String[] args) {
        Set<Person> personSet =  new HashSet<>();
        personSet.add(new Person("A", 12));
        personSet.add(new Person("B", 12));
        personSet.add(new Person("C", 15));
        personSet.add(new Person("A", 13));
        personSet.add(new Person("A", 12));
        personSet.add(new Person("B", 11));
        personSet.add(new Person("A", 12));

        Iterator<Person> itr = personSet.iterator();

        for(Iterator<Person> elm = itr; elm.hasNext();) {
            System.out.println(elm.next());
        }
    }
}
