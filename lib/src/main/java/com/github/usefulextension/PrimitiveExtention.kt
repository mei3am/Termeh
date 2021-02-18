package com.github.usefulextension

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.security.SecureRandom
import java.text.DecimalFormat

/**
 * check if number is odd
 * */
fun Int.isOdd(): Boolean = kotlin.run { ( this % 2 ) != 0 }

/**
 * check if number is even
 * */
fun Int.isEven(): Boolean = kotlin.run { ( this % 2 ) == 0 }

/**
 * Constructs a secure random number in size
 * */
fun IntRange.secureRandom(size: Int) =
    SecureRandom().apply {
        nextBytes(ByteArray(size))
    }.nextInt((endInclusive + 1) - start) + start

/**
 * MutableLiveData with initial value
 * */
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

/**
 * convert string to price format
 * like 10000 to 10,000 or 9999999 to 9,999,999
 * */
fun String?.toPriceFormat(): String? = run {
    return try {
        this?.let {
            val am = it.replace(Regex("\\.(.*)"), "")
            val formatter = DecimalFormat("#,###,###")
            formatter.format(am.toLong())
        }
    } catch (e: Exception) {
        Log.e(this.toString(), "cannot convert this value : ${e.message} ")
        this
    }
}

/**
 * convert long to price format
 * like 10000 to 10,000 or 9999999 to 9,999,999
 * */
fun Long?.toPriceFormat(): String? = run {
    return try {
        this.toString().let {
            val am = it.replace(Regex("\\.(.*)"), "")
            val formatter = DecimalFormat("#,###,###")
            formatter.format(am.toLong())
        }
    } catch (e: Exception) {
        Log.e(this.toString(), "cannot convert this value : ${e.message} ")
        this.toString()
    }
}

/**
 * convert string number to long safely
 * */
fun String?.toSafeLong(): Long? = run {
    return try {
        this?.toLong()
    }catch (e: java.lang.Exception){
        Log.e(this.toString(), "cannot convert : ${e.message} ")
        null
    }
}

/**
 * convert string number to int safely
 * */
fun String?.toSafeInt(): Int? = run {
    return try {
        this?.toInt() ?: 0
    }catch (e: java.lang.Exception){
        Log.e(this.toString(), "cannot convert : ${e.message} ")
        null
    }
}

val String.isDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isAlphanumeric: Boolean
    get() = matches(Regex("[a-zA-Z0-9]*"))

