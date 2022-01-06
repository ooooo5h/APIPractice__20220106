package com.neppplus.apipractice_20220106.api

import com.neppplus.apipractice_20220106.models.BasicResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

interface APIList {
    
//    api.gudoc.in 서버의 기능들을 하나씩 접근하는 방법(함수로)을 명시하는 파일
    
//    * 로그인
    
    @FormUrlEncoded   // @Field에 데이터를 담으려면 추가로 부착해야하는 코드
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String,    
    ) : Call<BasicResponse>     // (특정 클래스 형태(BasicResponse)의 응답을 받는)API호출 기능을 만들어냄

//    * 회원가입

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw : String,
        @Field("nick_name") nick : String,
        @Field("phone") phone : String,
    ) : Call<BasicResponse>   // 회원가입도 로그인처럼 code, message, data 세가지만 제일 바깥 { }에 담겨있기때문에 BasicResponse를 그대로 써도 됨
    
    
}