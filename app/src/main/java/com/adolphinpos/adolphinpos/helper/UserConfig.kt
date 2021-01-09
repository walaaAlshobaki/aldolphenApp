package com.adolphinpos.adolphinpos.helper



class UserConfig {

    var firstName:String="Guest"
    var lastName:String="Guest"
    var country:String="JO"
    var userid:String="-1"
    var phoneNumber:String="normal"
    var email:String="none"
    var auth_token:String="none"



    constructor(
        firstName:String="",
        lastName:String="",
        country:String="",
        userid:String="",
        phoneNumber:String="",
        email:String="",
        auth_token:String="",


    ){
        this.firstName=firstName
        this.lastName=lastName
        this.country=country
        this.userid=userid
        this.phoneNumber=phoneNumber
        this.email=email
        this.auth_token=auth_token



    }




}