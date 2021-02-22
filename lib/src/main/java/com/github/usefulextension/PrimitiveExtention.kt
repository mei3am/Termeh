package com.github.usefulextension

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.security.MessageDigest
import java.security.SecureRandom
import java.text.DecimalFormat
import java.util.regex.Pattern

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

/**
 * check is string contain only alphabet and digits
 * */
val String.isAlphanumeric: Boolean
    get() = matches(Regex("[a-zA-Z0-9]*"))

/**
 * check is string contain latin letter
 * */
val String.containsLatinLetter: Boolean
    get() = matches(Regex(".*[A-Za-z].*"))

/**
 * check is string contain only persian letter
 * */
val String.containsOnlyPersianLetter: Boolean
    get() = matches(Regex("^[\\u0622\\u0627\\u0628\\u067E\\u062A-\\u062C\\u0686\\u062D-\\u0632\\u0698\\u0633-\\u063A\\u0641\\u0642\\u06A9\\u06AF\\u0644-\\u0648\\u06CC]+\$"))

/**
 * check is string contain only digits
 * */
val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))


/**
 * Checking validity of an email address
* */
fun String.isValidEmail(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

/**
 * Checking validity of an iranian mobile number
* */
fun String.isValidIranianMobile(): Boolean {
    val expression = "^(\\+98|0)?9\\d{9}\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

/**
 * generate sha hash
 */
val String.sha1: String
    get() {
        val bytes = MessageDigest.getInstance("SHA-1").digest(this.toByteArray())
        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }