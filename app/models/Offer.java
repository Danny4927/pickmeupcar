package models;

import play.db.ebean.Model;
import views.formdata.OfferFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ru4262 on 29.09.2014.
 */
public class Offer extends Model {

    public long id;
    public User user;
    public String startPoint;
    public String targetPoint;
    public String date;
    public String time;

    private static long currentID = 0L;

    private static List<Offer> allOffers = new ArrayList<Offer>();

    /**
     * Model entities typically want to have a no-arg constructor.
     */
    public Offer(){
    }

    public Offer(User user, String startPoint, String targetPoint, String date, String time) {
        this.setId(currentID);
        currentID++;
        this.user = user;
        this.startPoint = startPoint;
        this.targetPoint = targetPoint;
        this.date = date;
        this.time = time;
        allOffers.add(this);
    }


    static {
        // Valid student. No optional data supplied.
        new Offer(new User("bla", "blub","blaa", "mail@bla.de","test12345678"), "München", "Berlin", "25.11.2015", "10:00");
//        // Valid student with optional data.
//        allOffers.add(new Offer("Alice Good", "mypassword"));
//
//        // Invalid student. Password is too short.
//        allOffers.add(new Offer("Frank Bad", "pass"));
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns a User instance created from the form data.
     * Assumes that the formData has been validated.
     * The ID field is not assigned or managed in this application.
     *
     * @param formData The student form data.
     * @return A student instance.
     */
    public static Offer makeInstance(OfferFormData formData) {
        Offer newOffer = new Offer();
        newOffer.setUser(User.getUserById(formData.getUserID()));
        newOffer.setStartPoint(formData.getStartPoint());
        newOffer.setTargetPoint(formData.getTargetPoint());
        newOffer.setDate(formData.getDate());
        newOffer.setTime(formData.getTime());
        allOffers.add(newOffer);

        return newOffer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static OfferFormData makeOfferFormData(long id) {
        for (Offer o : allOffers) {
            if (o.getId() == id)
                return o.getOfferFormData();
        }
        return null;
    }

    /**
     * Converts data from form to model
     *
     * @return
     */
    public OfferFormData getOfferFormData() {

        return new OfferFormData(this.getUser(), this.getStartPoint(), this.getTargetPoint(), this.getDate(), this.getTime());
    }

    public static List<Offer> getAllOffers() {
        return allOffers;
    }

    public long getId() {
        return id;
    }
}
