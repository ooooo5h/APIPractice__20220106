package com.neppplus.apipractice_20220106.models

class ReviewData(
    val id : Int,
    val title : String,
    val content : String,
    val score : Double,
    val thumbnail_img : String,
    val user : UserData,   // 기존에 만든 UserData 재활용
) {
}