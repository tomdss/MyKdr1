package com.example.mykdr1.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    var login: String? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("location")
    var location: String? = null,
    @SerializedName("public_repos")
    var publicRepos: Int = 0

)