package com.wwt.nimbleviewing.data.util

import java.util.*

fun String.getUrl() = String(Base64.getDecoder().decode(this))