package com.example.woof

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DogDataClass(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)

