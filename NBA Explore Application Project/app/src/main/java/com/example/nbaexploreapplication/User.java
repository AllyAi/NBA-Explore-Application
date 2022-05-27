package com.example.nbaexploreapplication;

public class User {

    public String firstName, lastName, emailAddress; //public variables for user details to be used by other methods in the project

    public User(){
                    //empty constructor used to pass through the public variables to retrieve user info by other methods
    }


    public User(String firstName, String lastName, String emailAddress){
        this.firstName =  firstName;
        this.lastName = lastName;                      //constructor that accepts arguments: firstName, lastName, emailAddress
        this.emailAddress = emailAddress;              //and initialise variables
    }
}
