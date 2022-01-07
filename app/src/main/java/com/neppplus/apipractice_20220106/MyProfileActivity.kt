package com.neppplus.apipractice_20220106

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.neppplus.apipractice_20220106.models.BasicResponse
import com.neppplus.apipractice_20220106.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

//        서버에 내 정보를 요청하자 => 기기에 저장된 토큰값을 활용해서 내가 누구인지 서버에 알려주자

//        ()안에 저장된 토큰값을 꺼내와야함 어떻게?
//        ContextUtil로 저장해둔 토큰을 가져오자

        apiList.getRequestMyInfo(ContextUtil.getToken(mContext)).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

//                BasicResponse를 br변수로 담아두자
                    val br = response.body()!!

//                    사용자가 누구인지? 변수에 담자
                    val myInfo = br.data.user

//                    UI에 닉네임 등 반영해보기
                    txtMyNickname.text = myInfo.nick_name
                    Glide.with(mContext).load(myInfo.profile_img).into(imgMyProfile)

                }


            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })


    }
}