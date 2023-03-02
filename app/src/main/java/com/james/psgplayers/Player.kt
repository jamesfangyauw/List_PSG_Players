package com.james.psgplayers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name : String,
    val position : String,
    val photo : Int,
    val description : String?
) : Parcelable
