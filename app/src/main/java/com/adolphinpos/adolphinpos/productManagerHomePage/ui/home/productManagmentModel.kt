package com.adolphinpos.adolphinpos.productManagerHomePage.ui.home

class productManagmentModel {
    var id:Int=0
    var name:String="Guest"
    var price:Double=0.0
    var discount:Int=0
    var category:String=""
    var date:String="normal"
    var Calories:String="none"
    var Weight:String="none"
    var image:String="none"



    constructor(
            id:Int=0,
            name:String="",
            price:Double=0.0,
            discount:Int=0,
            date:String="",
            Calories:String="",
            Weight:String="",
            image:String="",
            category:String=""

            ){
        this.id=id
        this.name=name
        this.price=price
        this.discount=discount
        this.date=date
        this.Calories=Calories
        this.Weight=Weight
        this.image=image
        this.category=category



    }

}