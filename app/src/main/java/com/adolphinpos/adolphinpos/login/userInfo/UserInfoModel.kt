package com.adolphinpos.adolphinpos.login.userInfo


class UserInfoModel {
    var firstName: String = "Guest"
    var lastName: String = "Guest"
    var isVerfied: Boolean = false
    var phoneNumber: String = "normal"
    var email: String = "none"
    var token: String = "none"
    var userId: Int = 0
    var companyId: String = "none"
//    var profilePicturePath: String ?= "none"
//    var age: Int? =0
//    var branchId: String = "none"


    constructor(
            firstName: String = "",
            lastName: String = "",
            isVerfied: Boolean = false,
            phoneNumber: String = "",
            email: String = "",
            token: String = "",
            userId: Int = 0,
            companyId: String = "",
//
//            profilePicturePath: String ?= "",
//            age: Int ?= 0,
//            branchId: String = "",

            ) {
        this.firstName = firstName
        this.lastName = lastName
        this.isVerfied = isVerfied

        this.phoneNumber = phoneNumber
        this.email = email
        this.token=token
        this.userId=userId
        this.companyId=companyId
//
//        this.profilePicturePath=profilePicturePath!!
//        this.age=age!!
//        this.branchId=branchId

    }


}
