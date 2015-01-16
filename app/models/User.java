package models;

import play.db.ebean.Model;
import views.formdata.UserFormData;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ru4262 on 12.01.2015.
 */
public class User extends Model {

    @Id
    private long id;
    private String vorname;
    private String nachname;
    private String username;
    private String password;
    private String email;
    private List<Offer> offers = new ArrayList<Offer>();
    private List<Car> cars = new ArrayList<Car>();
    // Mailadresse des Anwenders, muss eindeutig sein


    /**
     * Fake a database of users.
     */
    private static HashMap<Long, User> allUsers = new HashMap<Long, User>();
    private static long currentID = 0L;

    // test user
    static User test = new User("bla","blub", "asd", "asd@asd.de", "asd" );

    public User(String vorname, String nachname, String username, String email, String password) {
        this.setId(currentID);
        currentID++;
        this.vorname = vorname.trim();
        this.nachname = nachname.trim();
        this.username = username.trim();
        this.email = email.trim();
        this.password = password;
        allUsers.put(this.getId(), this);


    }


    public static Finder<String,User> find = new Finder<String,User>(
            String.class, User.class
    );

    public static Object authenticate(String email, String password){
        if (email.equals("asd@asd.de")){
            return "bla";
        }
        return null;
    }

    public User getUserByUsername(String username) {
        User currentUser;
        for (long key : this.allUsers.keySet()) {
            currentUser = this.allUsers.get(key);
            // Username korrekt
            if (currentUser.getUsername().equals(username)) {
                return currentUser;
            }
        }
        return null;
    }

    /**
     * Find a user instance given the ID.
     *
     * @param id The id of the user.
     * @return The User instance
     */
    public static User getUserById(long id) {
        return allUsers.get(id);
    }

    public static User getUserByEmail(String email) {
        User currentUser;
        for (long key : allUsers.keySet()) {
            currentUser = allUsers.get(key);
            // email korrekt
            if (currentUser.getEmail().equals(email)) {
                return currentUser;
            }
        }
        return null;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Returns a User instance created from the form data.
     * Assumes that the formData has been validated.
     * The ID field is not assigned or managed in this application.
     *
     * @param formData The user form data.
     * @return A user instance.
     */
    public static User makeInstance(UserFormData formData) {
        User user = new User(formData.getVorname(), formData.getNachname(), formData.getUsername(), formData.getEmail(), formData.getPassword());

        return user;
    }
}
