package com.neppplus.apipractice_20220106.api

import com.neppplus.apipractice_20220106.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

//    서버 통신을 하게 도와주는 클래스(Retrofit)를 객체로 만들어주는 기능을 가진 클래스
//    함수 한번만 호출하면, 어디서든 편하게 가져다 쓰일 수 있게 함수로 만들어두기
//    클래스이름.기능()  => @classmethod에 대응되는 기능을 활용하려고 하는 것

    companion object {
//        이 companion object {}안에 적는 변수와 함수들은, ServerAPI.변수/함수명 으로 바로 활용(노출)이 가능하다

//        val BASE_URL = "https://api.gudoc.in"

        private val BASE_URL = "https://api.gudoc.in"  //이 변수는 노출안되고, 이 클래스 내부에서만 사용할 예정 -> private 처리
        private var retrofit : Retrofit? = null  // 서버 연결을 전담하는 변수. 기본적으로는 아직 안만든 상태기때문에 null대입

//        retrofit 변수에 환경설정 + 객체화 => 가져다 쓸 수 있게 하는 기능을 함수로 제공
        fun getRetrofit(context : Context) : Retrofit {

//            retrofit 변수가 null이라면 아직 안만든 상태니까, 새 객체를 만들어주자
//            retrofit 변수가 null이 아니라면, 이미 만들어둔게 있다는 이야기니까 있는 객체를 사용하게 하자
//            하나의 객체를 계속해서 재활용하게 유도하는 패턴을 "싱글톤 패턴"이라고함

            if (retrofit == null) {


                
//                토큰의 경우, 여러 API 함수에서 사용해야 하고 + 매번 같은 토큰값이 입력된다(Context.getToken())
//                자동화하면 훨씬 편하겠지?
//                어떻게? => 레트로핏 객체를 생성하기 전에, 토큰에 관련된 세팅을 코드로 추가해두자

//                API 요청이 만들어 질 때 가로채서 헤더를 추가해주자
//                헤더가 붙여지고 나서, 나머지 API 요청을 실행(자동으로 헤더 첨부 효과 발생)

                val interceptor = Interceptor {
                    with(it) {

//                        새 리퀘스트를 만들자 어떤? 헤더가 첨부된 리퀘스트

                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getToken(context))
                            .build()

//                        완성된 새 리퀘스트로 작업을 이어가게 하자
                        proceed(newRequest)
                    }
                }

//                위에 적은 인터셉터를 적용하는 통신 체계를 만들어야함(클라이언트의 기능을 수정)
                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

//                 null이면 실제 레트로핏 객체를 생성하기 => 인터셉터 적용하는 클라이언트를 적용하도록 수정해야함
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // 어느 서버안에서 움직일건지 설정
                    .addConverterFactory(GsonConverterFactory.create())  // JSON응답을 자동으로 분석하는 도구 설치
                    .client(myClient)
                    .build()

            }

//            retrofit이 null이라면 채워줬고, 아니라면 있는 객체를 사용하게 됨
            return retrofit!! // 절대 null일리 없다고 !! 붙여주기

        }

    }

}