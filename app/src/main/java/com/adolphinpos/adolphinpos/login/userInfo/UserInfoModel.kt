package com.adolphinpos.adolphinpos.login.userInfo


class UserInfoModel {
    var firstName: String = "Guest"
    var lastName: String = "Guest"
    var isVerfied: Boolean = false
    var phoneNumber: String = "normal"
    var email: String = "none"


    constructor(
            firstName: String = "",
            lastName: String = "",
            isVerfied: Boolean = false,
            phoneNumber: String = "",
            email: String = "",


            ) {
        this.firstName = firstName
        this.lastName = lastName
        this.isVerfied = isVerfied

        this.phoneNumber = phoneNumber
        this.email = email

    }


}
