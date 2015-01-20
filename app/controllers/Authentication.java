package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginFormData;
import views.formdata.UserFormData;
import views.html.login;
import views.html.register;

import static play.data.Form.form;

/**
 * Created by ru4262 on 20.01.2015.
 */
public class Authentication extends Controller {

    /**
     * Process a form submission.
     * First we bind the HTTP POST data to an instance of OfferFormData.
     * The binding process will invoke the OfferFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     *
     * @return The index page with the results of validation.
     */
    public static Result postLoginForm() {

        // Get the submitted form data from the request object, and run validation.
        Form<LoginFormData> formData = form(LoginFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Bitte beheben sie zuerst alle Fehler");
            return badRequest(login.render(formData));
        } else {
            // Convert the formData into a offer model instance.
            User current = User.getUserByEmail(formData.get().getEmail());
            session().clear();
            session().put("id", String.valueOf(current.getId()));
            session().put("username", String.valueOf(current.getUsername()));
            session().put("connected", "true");
            flash("success", "Die Anmeldung  war erfolgreich");
            return redirect("/");

            //  return ok(index.render("Willkommen zurück "+current.getUsername()));
        }
    }
    public static Result getLoginForm(long id) {
        LoginFormData loginData = new LoginFormData();
        Form<LoginFormData> formData = form(LoginFormData.class).fill(loginData);

        return ok(login.render(formData));
    }

    public static Result logout() {

        session().clear();
        return redirect("/");
    }

    public static Result register() {

        // Get the submitted form data from the request object, and run validation.
        Form<UserFormData> formData = form(UserFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Bitte beheben sie zuerst alle Fehler");
            return badRequest(register.render(formData));
        } else {
            // Convert the formData into a user model instance.
            User  current = User.makeInstance(formData.get());

            flash("success", "Registrierung war erfolgreich");
            return ok(register.render(formData));
        }

    }

    public static Result showRegistrationForm() {

        UserFormData loginData = new UserFormData();
        Form<UserFormData> formData = form(UserFormData.class).fill(loginData);

        return ok(register.render(formData));
    }


}
