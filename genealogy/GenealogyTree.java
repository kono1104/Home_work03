package genealogy;

import genealogy.filehandling.GenealogyFileHandler;
import genealogy.util.GenealogySorter;
import genealogy.model.Person;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class GenealogyTree implements Iterable<Person> {
    private List<Person> people;
    private GenealogyFileHandler fileHandler;

    public GenealogyTree(GenealogyFileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.people = fileHandler.readFromFile("genealogy_data.ser");
    }

    public void saveToFile() {
        fileHandler.writeToFile("genealogy_data.ser", people);
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> findChildren(String personName) {
        List<Person> children = new ArrayList<>();
        for (Person person : people) {
            if (person.getName().equals(personName)) {
                children.addAll(person.getChildren());
            }
        }
        return children;
    }

    public void printAllPeopleInfo() {
        for (Person person : people) {
            System.out.println("Имя: " + person.getName());
            System.out.println("Год рождения: " + person.getBirthYear());
            System.out.println("Пол: " + person.getGender());
            System.out.println("Род занятий: " + person.getOccupation());
            System.out.println("Место рождения: " + person.getPlaceOfBirth());
            System.out.println("------------------------");
        }
    }

    @Override
    public Iterator<Person> iterator() {
        List<Person> sortedPeople = GenealogySorter.sortByName(people);
        return sortedPeople.iterator();
    }
}