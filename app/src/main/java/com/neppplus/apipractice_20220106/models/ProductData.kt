package com.neppplus.apipractice_20220106.models

import com.google.gson.annotations.SerializedName

class ProductData(
    val id : Int,
    val name : String,
    val price : Int,
    @SerializedName("image_url")
    val imageURL : String,  // 서버가 주는 이름표와 우리가 저장하려는 변수명이 다를 때 이렇게 코드 작성
    val store : StoreData,
) {
}