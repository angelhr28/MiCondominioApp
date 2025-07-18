package org.angelhr28.micondominio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform