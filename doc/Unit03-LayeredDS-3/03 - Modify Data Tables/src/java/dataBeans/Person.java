package dataBeans;

import java.io.Serializable;


/**
 * Person class - used to demonstrate the use of the javadoc tool.
 *
 * @author: Reiner.Dojen Date: 16.09.2010
 */
public class Person implements Comparable<Person>, Serializable {

    private static final long serialVersionUID = 1;
    
    private String firstname;
    private String lastname;
    private int age;
    private double height; // stores height in cm
    private boolean canEdit;

    /**
     * default constructor - creates a Person object with no information
     *
     */
    public Person() {
        firstname = "";
        lastname = "";
        age = 0;
        height = 0;
        canEdit = false;
    }

    /**
     * generic constructor - creates a Person object with the given information.
     * First- and last name are set to the empty string (""), age and height are
     * set to 0.
     *
     * @param fname - first name of the person
     * @param lname - last name of the person
     * @param ageYears - age of the person in years
     * @param heightCM - height of the person in cm
     */
    public Person(String fname, String lname, int ageYears, double heightCM) {
        firstname = fname;
        lastname = lname;
        if (age >= 0) {
            age = ageYears;
        }
        if (heightCM > 0) {
            height = heightCM;
        }
        canEdit = false;
    }

    /**
     * generic constructor - creates a Person object with the given information.
     * Age and height of the person are set to 0.
     *
     * @param fname first name of person
     * @param lname last name of person
     */
    public Person(String fname, String lname) {
        firstname = fname;
        lastname = lname;
        age = 0;
        height = 0;
        canEdit = false;
    }

    /**
     * copy constructor - creates a copy of the person object.
     *
     * @param orig - Original person object of which a copy will be created.
     */
    public Person(Person orig) {
        firstname = orig.firstname;	
        lastname = orig.lastname;	
        age = orig.age; 	
        height = orig.height;
        canEdit = orig.canEdit;
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
     * accessor for last name of person
     *
     * @return Last name of person
     */
    public String getLastname() {
        return lastname;
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
     * accessor for the height of person
     *
     * @return Height of person in cm
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * Accessor for isEdit
     * @return value of canEdit
     */
    public boolean isCanEdit() {
        return canEdit;
    }

    /**
     * Mutator for canEdit
     * @param canEdit false indicates this object cannot be edited
     */
    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
    
    /**
     * accessor for the height of person
     *
     * @return Height of person in cm
     */
    public double getHeightInCM() {
        return height;
    }

    /**
     * accessor for the height of person
     *
     * @return Height of person in feet.
     */
    public double getHeightInFeet() {
        // 1 foot == 30.48cm
        return height / 30.48;
    }

    /**
     * mutator for firstname of person
     *
     * @param name - New first name that will be assigned to the person object
     */
    public void setFirstname(String name) {
        firstname = name;
    }

    /**
     * mutator for lastname of person
     *
     * @param name - New last name that will be assigned to the person object
     */
    public void setLastname(String name) {
        lastname = name;
    }

    /**
     * mutator for the age of person. It ensures plausibility: age must be &ge; 0.
     *
     * @param ageInYears - New age (in years) that will be assigned to the
     * person object
     */
    public void setAge(int ageInYears) {
        if (ageInYears >= 0) {
            age = ageInYears;
        }
    }

    /**
     * mutator for height of person. It ensures plausibility: height must be &gt;
     * 0.
     *
     * @param heightInCM - New height (in cm) that will be assigned to the
     * person object
     */
    public void setHeight(double heightInCM) {
        if (heightInCM > 0) {
            height = heightInCM;
        }
    }

    /**
     * mutator for height of person. It ensures plausibility: height must be &gt;
     * 0.
     *
     * @param heightInCM - New height (in cm) that will be assigned to the
     * person object
     */
    public void setHeightInCM(double heightInCM) {
        if (heightInCM > 0) {
            height = heightInCM;
        }
    }

    /**
     * mutator for height of person. It ensures plausibility: height must be &gt;
     * 0.
     *
     * @param heightInFeet - New height (in feet) that will be assigned to the
     * person object
     */
    public void setHeightInFeet(double heightInFeet) {
        if (heightInFeet > 0) {
            height = heightInFeet * 30.48;
        }
    }

    /**
     * Convenience method to obtain a String representation of the person
     * object.
     *
     * @return String that describes the person
     */
    public String getDescription() {
        String description = firstname + " " + lastname + " is " + age + " years old and "
                + height + "cm tall";
        return description;
    }

    @Override
    public int compareTo(Person p) {
        return lastname.compareTo(p.lastname);
    }
}
