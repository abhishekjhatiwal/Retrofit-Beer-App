package com.example.pagginationapp.data

import com.example.pagginationapp.data.local.BeerEntity
import com.example.pagginationapp.data.remote.Beer
import com.example.pagginationapp.data.remote.BeerDto


fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}