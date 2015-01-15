package models;

import play.db.ebean.Model;
import views.formdata.LoginFormData;

/**
 * Created by ru4262 on 14.01.2015.
 */
public class Login extends Model{

    public String email;
    public String password;



    /**
     * Model entities typically want to have a no-arg constructor.
     */
    public Login(){
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

//    public String validate() {
//        if (User.authenticate(email, password) == null) {
//            return "Invalid user or password";
//        }
//        return null;
//    }
public String getEmail() {
    return email;
}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Returns a User instance created from the form data.
     * Assumes that the formData has been validated.
     * The ID field is not assigned or managed in this application.
     *
     * @param formData The student form data.
     * @return A student instance.
     */
    public static Login makeInstance(LoginFormData formData) {
        Login newLogin = new Login();
        newLogin.setEmail(formData.getEmail());

        return newLogin;
    }
}
