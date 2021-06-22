package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

class VariationModel {

    var AttributeName: String = "Guest"
    var NumberOfChoice: String = "Guest"
    var ControlType: ArrayList<LookupModel.Data>? =null
    var Options: ArrayList<OptionDataModel>? =null
    var required: Boolean = false
    var ShowAttribute:  Boolean = false
    var selectedControlType:  Int = 1
    var selectedControlTypePosition:  Int = 1



    constructor(
        AttributeName: String = "",
        NumberOfChoice: String = "",
        ControlType: ArrayList<LookupModel.Data>? =null,
        Options: ArrayList<OptionDataModel>? =null,
        required: Boolean = false,
        ShowAttribute: Boolean = false,
        selectedControlType: Int = 1,
        selectedControlTypePosition: Int = 1,

    ) {
        this.AttributeName = AttributeName
        this.NumberOfChoice = NumberOfChoice
        this.ControlType = ControlType

        this.Options = Options
        this.required = required
        this.ShowAttribute=ShowAttribute
        this.selectedControlType=selectedControlType
        this.selectedControlTypePosition=selectedControlTypePosition


    }
}