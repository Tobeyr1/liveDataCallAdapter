package com.tobery.livedata.call.livedatalib

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 *  @Package:        com.tobey.pagger3study
 * @ClassName:      LiveDataCallAdapter
 * @Author:         Tobey_r1
 * @CreateDate:     2022/6/19 13:23
 * @Description:     java类作用描述
 * @UpdateUser:        更新者
 * @UpdateDate:     2022/6/19 13:23
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<ApiResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.success(response))
                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(ApiResponse.error(t))
                        }
                    })
                }
            }
        }
    }
}