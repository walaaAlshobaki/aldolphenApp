package com.adolphinpos.adolphinpos.registeration.country

import java.io.Serializable

class CountryModel : Serializable {
    var country_id:Int
    var thumb_country:String
    var title:String
    var code:String
    var type:String

    constructor(
        country_id:Int = -2,

        thumb_country:String="",
        title:String="",
        code:String="",
        type:String="item"


    ) {

        this.country_id=country_id
        this.code=code
        this.thumb_country=thumb_country
        this.title=title
        this.type=type


    }
}