package com.hotelresvations.steps;

import com.hotelresvations.models.BookingResponse;
import com.hotelresvations.services.ReservationServices;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {

    ReservationServices reservationServices;
    String authkey;
    BookingResponse bookingResponse;


    @Given("Create a new user")
    public void create() {
        reservationServices = new ReservationServices();
        System.out.println("test1");

    }

    @And("Give information for Booking")
    public void createAuth() {
        System.out.println("test2");

        authkey = reservationServices.generateToken();
        System.out.println(authkey);
        System.out.println("test3");
    }

    @When("User Create new booking")
    public void createReservation() {
       bookingResponse = reservationServices.createBooking();
        System.out.println("When");

    }


    @Then("Booking created succesfully")
    public void reservationAssertions(){
        Assertions.assertEquals("Adaleat",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Huseynov",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(500,bookingResponse.getBooking().getTotalprice());
        Assertions.assertTrue(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("PC",bookingResponse.getBooking().getAdditionalneeds());

    }


    @And("Deleting reservation")
    public void cancelReservation() {
        reservationServices.deleteBooking(authkey,bookingResponse.getBookingid());
    }
}





