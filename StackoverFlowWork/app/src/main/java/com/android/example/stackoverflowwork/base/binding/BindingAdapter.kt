package com.android.example.stackoverflowwork.base.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.example.stackoverflowwork.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("invisibility")
fun setInVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("imageUrl", "applyCorner")
fun setImageUrl(imageView: ImageView, url: String?, applyCorner: Boolean) {
    if (url.isNullOrEmpty()) return

    Glide
        .with(imageView.context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(if (applyCorner) 32 else 1)))
        .into(imageView)
}

@BindingAdapter("circleImageUrl")
fun setCircleImageUrl(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) return

    Glide
        .with(imageView.context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}
@BindingAdapter("dateText")
fun dateCreate(textView: TextView,int: Int){
    val dateFormat = SimpleDateFormat("yyyy-dd-MM", Locale.ROOT)

    val myDate: Date?
    try {
        myDate = Date(int.toLong())
        textView.text = "Tarih : "+dateFormat.format(myDate ?: Date())
    } catch (e: ParseException) {
        textView.text = "Tarih ayarlanamadı."
    }

}
