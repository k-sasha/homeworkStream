package stream.populationCount;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> namesOfMen = Arrays.asList("Ivan", "Bob", "Petr", "Vladimir", "Sergey", "Pavel");
        List<String> surnamesOfMen = Arrays.asList("Petrov", "Sidorov", "Ivanov", "Volkov", "Morozov", "Stepanov");
        List<String> namesOfWomen = Arrays.asList("Liza", "Sveta", "Maria", "Sasha", "Olga", "Irina");
        List<String> surnamesOfWomen = Arrays.asList("Petrova", "Sidorova", "Ivanova", "Volkova", "Morozova", "Stepanova");
        List<Person> people = new ArrayList<>();
        int count = 10000000;

        for (int i = 0; i < count / 2; i++) {
            people.add(new Person(
                    namesOfMen.get(new Random().nextInt(namesOfMen.size())),
                    surnamesOfMen.get(new Random().nextInt(surnamesOfMen.size())),
                    new Random().nextInt(100),
                    Sex.MAN));
        }

        for (int i = 0; i < count / 2; i++) {
            people.add(new Person(
                    namesOfWomen.get(new Random().nextInt(namesOfWomen.size())),
                    surnamesOfWomen.get(new Random().nextInt(surnamesOfWomen.size())),
                    new Random().nextInt(100),
                    Sex.WOMAN));
        }


        for (int i = 0; i < count; i++) {
            if (people.get(i).getAge() < 12)
                people.get(i).setEducation(Education.ELEMENTARY);
            else if (people.get(i).getAge() < 14)
                people.get(i).setEducation(Education.SECONDARY);
            else if (people.get(i).getAge() < 18)
                people.get(i).setEducation(Education.FURTHER);
            else {
                people.get(i).setEducation(Education.values()[new Random().nextInt(Education.values().length)]);
            }
        }

        long minors = people.parallelStream().filter(e -> e.getAge() < 18).count();
        List<String> surnamesOfSoldiers = people.parallelStream()
                .filter(e -> e.getSex() == Sex.MAN)
                .filter(e -> e.getAge() >= 18 && e.getAge() < 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());
        List<Person> workablePeople = Stream.concat(people.parallelStream()
                                .filter(e -> e.getAge() >= 18 && e.getAge() < 60 && e.getSex() == Sex.WOMAN),
                        people.parallelStream()
                                .filter(e -> (e.getAge() >= 18 && e.getAge() < 65 && e.getSex() == Sex.MAN)))
                .filter(e -> e.getEducation() == Education.HIGHER)
                .sorted((x, y) -> x.getSurname().compareTo(y.getSurname()))
                .collect(Collectors.toList());

        System.out.println("Count of minors: " + minors);
        System.out.println("-------------------------------------------------------");
        System.out.println("Surnames of soldiers:");
        System.out.println(surnamesOfSoldiers);
        System.out.println("-------------------------------------------------------");
        System.out.println("Workable people:");
        System.out.println(workablePeople);

    }
}
