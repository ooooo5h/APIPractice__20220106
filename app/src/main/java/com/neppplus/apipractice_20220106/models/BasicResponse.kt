package com.neppplus.apipractice_20220106.models

//  서버의 응답(Response)을 분석(parsing)해서 변수에 담아두는 역할을 하는 클래스임

class BasicResponse(
    val code : Int,
    val message : String,
    val data : DataResponse,
) {
}