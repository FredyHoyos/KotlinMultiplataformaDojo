package com.udea.firstkotlinmultiplatformkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform