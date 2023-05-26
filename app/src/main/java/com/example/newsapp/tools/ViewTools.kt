package com.sawaf.weatherappex.tools

import android.app.Service
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.squareup.picasso.Picasso

private val baseImageUrl =
    "https://openweathermap.org/img/wn/"

fun EditText.getInputString(): String {
    return text.toString()
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}
//.placeholder(placeholder)
//.error(placeholder)
//placeholder: Int = com.google.android.material.R.drawable.ic_m3_chip_checked_circle
fun ImageView.loadImage(url: String, placeholder: Int = R.drawable.ic_launcher_background, context : Context) =

    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(placeholder)
        .centerCrop().
        into(this)
fun View.toInvisible() {
    this.visibility = View.GONE
}
/**
 * Easy toast function for Activity.
 */
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, text, duration).show()
}

/**
 * Inflate the layout specified by [layoutRes].
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}