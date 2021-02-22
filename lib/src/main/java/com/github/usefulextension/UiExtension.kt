package com.github.usefulextension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
* hide keyboard in [Fragment ]
*/
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/**
* hide keyboard in [Activity]
*/
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

/**
* hide keyboard [Context]
*/
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
* quick toast in [Fragment]
* Toast.LENGTH_LONG
*/
fun Fragment.longToast(@StringRes res: Int) {
    Toast.makeText(requireContext(), getString(res), Toast.LENGTH_LONG).show()
}


/**
* quick toast in [Fragment]
* Toast.LENGTH_SHORT
*/
fun Fragment.shortToast(@StringRes res: Int) {
    Toast.makeText(requireContext(), getString(res), Toast.LENGTH_SHORT).show()
}

/**
* quick toast in [Activity]
* Toast.LENGTH_LONG
*/
fun Activity.longToast(@StringRes res: Int) {
    Toast.makeText(this, getString(res), Toast.LENGTH_LONG).show()
}


/**
* quick toast in [Activity]
* Toast.LENGTH_SHORT
*/
fun Activity.shortToast(@StringRes res: Int) {
    Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show()
}

/**
* quick toast
* Toast.LENGTH_LONG
*/
fun Context.longToast(@StringRes res: Int) {
    Toast.makeText(this, getString(res), Toast.LENGTH_LONG).show()
}

/**
* quick toast
* Toast.LENGTH_SHORT
*/
fun Context.shortToast(@StringRes res: Int) {
    Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show()
}

/**
 * start new activity in [Activity]
 * */
inline fun <reified T : Activity> Activity.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

/**
 * start new activity in [Fragment]
 * */
inline fun <reified T : Activity> Fragment.startActivity() {
    val intent = Intent(this.requireActivity(), T::class.java)
    startActivity(intent)
}

/**
 * visible view
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * invisible view
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * gone view
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * disable view
 */
fun View.disable() {
    isEnabled  = false
}

/**
 * enable view
 */
fun View.enable() {
    isEnabled = true
}
