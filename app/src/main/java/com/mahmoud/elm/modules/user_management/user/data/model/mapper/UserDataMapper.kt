package com.mahmoud.elm.modules.user_management.user.data.model.mapper

import com.mahmoud.elm.modules.user_management.user.data.model.UserResponse
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity

fun UserResponse.toEntity(email: String) = UserEntity(
    accessToken = access_token,
    email = email
)