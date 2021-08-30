package com.cindy.cibweatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

@SuppressLint("ConstantLocale")
val DATE_DAY_FORMAT = SimpleDateFormat("EEEE", Locale.getDefault())

fun String.getDayOfWeek(): String{

    val inputDate = DATE_FORMAT.parse(this)
    return if(inputDate != null){
        DATE_DAY_FORMAT.format(inputDate)
    }else{
        ""
    }

}