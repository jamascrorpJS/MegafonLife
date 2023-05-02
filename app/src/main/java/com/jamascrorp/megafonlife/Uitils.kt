package com.jamascrorp.megafonlife

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.hideAction() {
    (activity as AppCompatActivity).supportActionBar?.hide()
}

fun Fragment.showAction() {
    (activity as AppCompatActivity).supportActionBar?.show()
}