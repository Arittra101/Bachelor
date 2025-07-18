package com.example.bachelors.core.common.data.model

object Months {
     var months : List<String> = listOf()
    fun getCurrentMonth(): String{
        return this.months[0]
    }
}