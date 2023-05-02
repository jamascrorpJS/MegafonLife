package com.jamascrorp.megafonlife.test

data class Day(
    val day: Int,
    val timeInMillis: Long? = null,
    var isSelected: Boolean = false,
    var isStart: Boolean = false,
    var isEnd: Boolean = false
)
