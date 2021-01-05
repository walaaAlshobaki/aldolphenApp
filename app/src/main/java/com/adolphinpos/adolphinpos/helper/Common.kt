package com.adolphinpos.adolphinpos.helper

import com.google.gson.Gson

class Common {

    companion object {

        val instance = Common()

    }
    var parserJson = Gson()
    var apiDomainRoot:String=""
}