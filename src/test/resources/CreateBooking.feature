Feature: Create Booking by User

  Scenario: User able to create booking and delete it
    Given Create a new user
    And Give information for Booking
    When User Create new booking
    Then Booking created succesfully
    And Deleting reservation
