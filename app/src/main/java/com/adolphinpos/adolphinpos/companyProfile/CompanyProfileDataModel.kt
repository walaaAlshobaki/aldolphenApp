package com.adolphinpos.adolphinpos.companyProfile

class CompanyProfileDataModel {
    var name: String = "Guest"
    var taxName: String = "Guest"
    var taxNumber: String = "none"
    var taxRecored: String = "normal"
    var taxValue: String = "none"
    var contactEmail: String = "none"
    var contactPhoneNumber: String = "none"
    var countryId: Int? =0

    var logo:String? = "https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg"



    constructor(
            name: String = "Guest",
            taxName: String = "Guest",
            taxNumber: String = "none",
            taxRecored: String = "normal",
            taxValue: String = "none",
            contactEmail: String = "none",
            contactPhoneNumber: String = "none",
            countryId: Int? =0,
//
            logo: String? = "https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg",



//            age: Int ?= 0,
//            branchId: String = "",

    ) {
        this.name = name
        this.taxName = taxName
        this.taxNumber = taxNumber

        this.taxRecored = taxRecored
        this.taxValue = taxValue
        this.contactEmail=contactEmail
        this.contactPhoneNumber=contactPhoneNumber
        this.countryId=countryId
//
        this.logo=logo!!


    }



}