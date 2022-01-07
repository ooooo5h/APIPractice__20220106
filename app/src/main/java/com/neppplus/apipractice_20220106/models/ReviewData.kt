package com.neppplus.apipractice_20220106.models

import java.io.Serializable

class ReviewData(
    val id : Int,
    val title : String,
    val content : String,
    val score : Float,
    val thumbnail_img : String,
    val user : UserData,   // 기존에 만든 UserData 재활용
) : Serializable{
}