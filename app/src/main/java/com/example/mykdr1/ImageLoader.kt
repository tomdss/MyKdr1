package com.example.mykdr1

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoader {
    fun loadImage(url: String?, imageView: ImageView) = Picasso.get().load(url).into(imageView)
}