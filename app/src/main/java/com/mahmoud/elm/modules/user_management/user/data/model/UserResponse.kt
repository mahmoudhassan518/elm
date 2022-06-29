package com.mahmoud.elm.modules.user_management.user.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("token")
    val access_token: String,
)