package com.example.petgalleryapp.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewExtension {
    @BindingAdapter("android:loadUrl")
    fun ImageView.loadImage(url: String?) {
        loadImage(url, null, null, false, false)
    }

    @BindingAdapter("android:loadUrl", "android:error", "android:placeholder")
    fun ImageView.loadImage(url: String?, error: Drawable? = null, placeholder: Drawable? = null) {
        loadImage(url, error, placeholder, false)
    }

    @BindingAdapter("android:loadUrl", "android:error", "android:placeholder", "android:isCircle")
    fun ImageView.loadImage(url: String?, error: Drawable? = null, placeholder: Drawable? = null, isCircle: Boolean? = false) {
        loadImage(url, error, placeholder, isCircle, false)
    }

    @BindingAdapter("android:loadUrl", "android:error", "android:placeholder", "android:isCircle", "android:showPlaceholderUntilLoad")
    fun ImageView.loadImage(url: String?, error: Drawable? = null, placeholder: Drawable? = null, isCircle: Boolean? = false, showPlaceholderUntilLoad: Boolean? = false) {
        val loadUrl =
            if (url.isNullOrEmpty())
                if (showPlaceholderUntilLoad != true) return else null
            else
                url
        val glide = Glide
            .with(context)
            .load(loadUrl)

        error?.let { glide.error(it) }
        placeholder?.let { glide.placeholder(it) }
        if(isCircle == true) glide.circleCrop()
        glide.into(this)

        //TODO: for test pick image
        tag = url
    }
}