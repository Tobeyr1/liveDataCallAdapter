package com.tobey.pagger3study

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *  @Package:        com.tobey.pagger3study
 * @ClassName:      LiveDataCallAdapterFactory
 * @Author:         Tobey_r1
 * @CreateDate:     2022/6/19 13:24
 * @Description:     java类作用描述
 * @UpdateUser:        更新者
 * @UpdateDate:     2022/6/19 13:24
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class LiveDataCallAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}