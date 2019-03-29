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
