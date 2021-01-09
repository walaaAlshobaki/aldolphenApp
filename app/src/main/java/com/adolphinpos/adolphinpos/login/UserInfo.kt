package com.adolphinpos.adolphinpos.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserInfo {
    @SerializedName("userEmail")
    @Expose
    var userEmail: String? = null

    @SerializedName("userPassword")
    @Expose
    var userPassword: String? = null

     constructor(username: String, password: String){
         this.userEmail=username
         this.userPassword=password
     }
}