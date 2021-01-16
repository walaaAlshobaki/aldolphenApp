package com.adolphinpos.adolphinpos.login.userInfo


class UserInfoModel {
    var firstName: String = "Guest"
    var lastName: String = "Guest"
    var isVerfied: Boolean = false
    var phoneNumber: String = "normal"
    var email: String = "none"
    var token: String = "none"
    var userId: Int = 0


    constructor(
            firstName: String = "",
            lastName: String = "",
            isVerfied: Boolean = false,
            phoneNumber: String = "",
            email: String = "",
            token: String = "",
            userId: Int = 0

            ) {
        this.firstName = firstName
        this.lastName = lastName
        this.isVerfied = isVerfied

        this.phoneNumber = phoneNumber
        this.email = email
        this.token=token
        this.userId=userId

    }


}
