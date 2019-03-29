/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.model.*;

/**
 *
 * @author Reiner
 */
@ManagedBean
@SessionScoped
public class MyFriends implements Serializable {

    // required field for Serializable
    private static final long serialVersionUID = 1;
    // List of my friends
    private List<Person> friends;
    private Person cFriend;

    private String firstname;
    private String surname;
    private int age;

    /**
     * Creates a new instance of MyFriends
     */
    public MyFriends() {
        // instantiate firends with some people
        friends = new ArrayList<>(Arrays.asList(
                new Person("John", "Roberts", 22, 176),
                new Person("Maria", "Whelan", 45, 175),
                new Person("Joseph", "Quinn", 42, 184),
                new Person("Tom", "Whelan", 62, 182),
                new Person("Michelle", "Franklin", 37, 182),
                new Person("Roisin", "Quinn", 23, 155),
                new Person("Michael", "Flannery", 52, 152),
                new Person("Colin", "Reidy", 31, 175)
        ));
    }

    /**
     * Getter for current friend
     *
     * @return current friend
     */
    public Person getcFriend() {
        return cFriend;
    }

    /**
     * Setter for current friend
     *
     * @param cFriend new current friend
     */
    public void setcFriend(Person cFriend) {
        this.cFriend = cFriend;
    }

    /**
     * accessor for first name of person
     *
     * @return First name of person
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * mutator for firstname of person
     *
     * @param name - New first name that will be assigned to the person object
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * accessor for surname of person
     *
     * @return surname of person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * mutator for surname of person
     *
     * @param name - New surname that will be assigned to the person object
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * accessor - for age of person
     *
     * @return Age of the person in years.
     */
    public int getAge() {
        return age;
    }

    /**
     * mutator for the age of person.
     *
     * @param ageInYears - New age (in years) that will be assigned to the
     * person object
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Return the list of all stored friends.
     *
     * @return List of friends
     */
    public List<Person> getFriends() {
        return new ArrayList<Person>(friends);
    }

    /**
     * Return the friend at given index.
     *
     * @param index Index of friend
     * @return Friend at index
     */
    public Person getFriend(int index) {
        return friends.get(index);
    }

    /**
     * Return a list of all friends with a given surname
     *
     * @param surname Surname of friends to look for
     * @return List containing all friends with given surname
     */
    public List<Person> getFriendByName(String surname) {
        ArrayList<Person> result = new ArrayList<>();
        // find all friends with given surname and add to result
        for (Person p : friends) {
            if (p.getLastname().compareTo(surname) == 0) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Sorts the collection of friends by surname
     */
    public void sortBySurname() {
        Collections.sort(friends);
    }

    /**
     * Sorts the collection of friends by name
     */
    public void sortByFirstname() {
        Collections.sort(friends, new FirstnameSorter());
    }

    /**
     * Sorts the collection of friends by age
     */
    public void sortByAge() {
        Collections.sort(friends, new AgeSorter());
    }

    /**
     * Method to add a new friend to the collection. values will be taken
     * from attributes
     */
    public void addFriend() {
        friends.add(new Person(firstname, surname, age, 0));
    }

    /**
     * removes a person from the collection
     * @param p Person to be removed
     */
    public void removeFriend(Person p) {
        friends.remove(p);
    }

    /**
     * Enables editing of person entries
     * @param p Person for which to enable editing
     */
    public void editFriend(Person p) {
        p.setCanEdit(true);
    }

    /**
     * Disable editing for all person entries.
     */
    public void saveFriends() {
        for (Person p : friends) {
            p.setCanEdit(false);
        }
    }

    // private helper class for sorting by first name
    private class FirstnameSorter implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFirstname().compareTo(o2.getFirstname());
        }

    }

    // private helper class for sorting by age
    private class AgeSorter implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge() - o2.getAge();
        }

    }
}
