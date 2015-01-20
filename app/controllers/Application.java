package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.UserFormData;
import views.html.index;
import views.html.register;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index(String s) {
        return ok(index.render(s));
    }




}
