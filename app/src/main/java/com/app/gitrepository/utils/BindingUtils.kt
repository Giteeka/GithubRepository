package com.app.gitrepository.utils

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.gitrepository.GlideApp
import com.app.gitrepository.R
import com.bumptech.glide.request.RequestOptions
import android.R.attr.button
import android.graphics.drawable.GradientDrawable


@BindingAdapter("setImagePath")
fun AppCompatImageView.setImagePath(path: String?) {
    GlideApp.with(this.context).load(path).apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.ic_launcher_background).into(this)
}

@BindingAdapter("setRefreshing")
fun SwipeRefreshLayout.setRefreshing(boolean: Boolean) {
    isRefreshing = boolean
}

@BindingAdapter(value = ["rowText", "defaultText"], requireAll = false)
fun AppCompatTextView.setTextViewText(rowText: String?, defaultText: String?) {
    if (rowText.isNullOrEmpty() || rowText.equals("null", ignoreCase = false))
        text = defaultText
    else
        text = rowText

}

@BindingAdapter("drawbaleColor")
//    @BindingAdapter("defaultText")
fun AppCompatTextView.setDrawableColor(color: String?) {
    var replace = color
    if (replace != null) {
        if (replace.length == 4 && replace[0] == '#') {
            replace = replace.replace("#", "")
            replace = "#$replace$replace"
        }
        val images = compoundDrawables
        val image = images[0]
        replace.let {
            image.setTint(Color.parseColor(replace))
        }
        setCompoundDrawables(image, null, null, null)
    }


}

