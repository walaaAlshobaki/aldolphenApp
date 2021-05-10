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

    var profilePicturePath:String? = "https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg"
    var permissionsActions: ArrayList<Int> = arrayListOf(1)
    var age: Int? =0
    var branchId: Int? =0
    var contryId: Int? =0


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
            profilePicturePath: String? = "https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg",
            age: Int? =0,
            branchId: Int? =0,
            contryId: Int? =0,
           permissionsActions: ArrayList<Int> = arrayListOf(1),


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
        this.profilePicturePath=profilePicturePath!!
        this.age=age!!
        this.branchId=branchId
        this.permissionsActions=permissionsActions
        this.contryId=contryId

    }


}
