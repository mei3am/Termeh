package com.github.usefulextension

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresPermission
import java.util.*

/**
 vibrate device
*/
@RequiresPermission(Manifest.permission.VIBRATE)
fun Context.vibrate(duration: Long) =
    (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrate(duration)
        }
    }

/**
 * return device manufacturer adn model
 */
fun Any.deviceNam(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    return if (model.startsWith(manufacturer))
        model.capitalize(Locale.getDefault())
    else
        "${manufacturer.capitalize(Locale.getDefault())} $model"
}

/**
 * return application version code
 */
fun Context.versionCode(): Long? =
    try {
        val pInfo = packageManager.getPackageInfo(packageName, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            pInfo?.longVersionCode
        } else {
            pInfo?.versionCode?.toLong()
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }

/**
 * return application version name
 */
fun Context.versionName(): String? =
    try {
        val pInfo = packageManager.getPackageInfo(packageName, 0);
        pInfo?.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }