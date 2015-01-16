package views.formdata;

import models.User;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ru4262 on 20.10.2014.
 */
public class UserFormData {

    public String vorname = "";
    public String nachname = "";
    public String password = "";
    public String password2 = "";
    public String email = "";
    private String username;


    public void addUser(User user) {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Validates Form<StudentFormData>.
     * Called automatically in the controller by bindFromRequest().
     *
     * Validation checks include:
     * <ul>
     * <li> Name must be non-empty.
     * <li> Password must be at least five characters.
     * <li> Hobbies (plural) are optional, but if specified, must exist in database.
     * <li> Grade Level is required and must exist in database.
     * <li> GPA is required and must exist in database.
     * <li> Majors (plural) are optional, but if specified, must exist in database.
     * </ul>
     *
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<ValidationError>();
        boolean noUsername = false;



        if (vorname == null || vorname.length() == 0) {
            errors.add(new ValidationError("vorname", "Keinen Vornamen eingegeben"));
        }

        if (nachname == null || nachname.length() == 0) {
            errors.add(new ValidationError("nachname", "Keinen Nachname eingegeben"));
        }

        if (username == null || username.length() == 0) {
            errors.add(new ValidationError("username", "Kein Benutzername eingegeben"));
            noUsername = true;
        }
        if (email == null || email.length() == 0) {
            errors.add(new ValidationError("email", "Keine Emailadresse eingegeben"));
        }else if (!noUsername){
            boolean usernameTaken = false;
            boolean emailTaken = false;

            //iterating over values only
            for (User value : User.getAllUsers().values()) {
                if (value.getUsername().equals(username)) {
                    usernameTaken = true;
                }
                if (value.getEmail().equals(email)) {
                    emailTaken = true;
                }
            }

            if (usernameTaken) {
                errors.add(new ValidationError("username", "Der Benutzername wurde bereits vergeben"));
            }

            if (emailTaken) {
                errors.add(new ValidationError("email", "Sie haben sich mit dieser Email Adresse bereits registriert"));
            }

        }
        if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "Kein Password eingegeben"));
        }
        if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "Kein Password eingegeben"));
        }else if (!password.equals(password2)) {
            errors.add(new ValidationError("password", "Passwörter stimmen nicht überein"));
            errors.add(new ValidationError("password2", "Passwörter stimmen nicht überein"));
        }

        if(errors.size() > 0)
            return errors;

        return null;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getPassword2() {
        return password2;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
