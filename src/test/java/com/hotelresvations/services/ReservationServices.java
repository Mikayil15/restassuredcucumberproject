package com.hotelresvations.services;

import com.hotelresvations.models.Auth;
import com.hotelresvations.models.Booking;
import com.hotelresvations.models.BookingDates;
import com.hotelresvations.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationServices extends BaseTest {




    public String generateToken() {
        Auth authBody = new Auth("admin", "password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }


    public BookingResponse createBooking(){
        BookingDates bookingDates = new BookingDates("2024-08-02","2024-08-03");
        Booking booking = new Booking("Adaleat","Huseynov",500,true,bookingDates,"PC");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);



return response.as(BookingResponse.class);
    }



    public void deleteBooking(String token, int bookingId){

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token =" + token)
                .when()
                .delete("/booking"+bookingId);

        response
                .then()
                .statusCode(201);

    }



}
