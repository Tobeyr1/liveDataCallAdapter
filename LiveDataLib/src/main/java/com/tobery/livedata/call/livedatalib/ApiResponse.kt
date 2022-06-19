package com.tobey.pagger3study

import retrofit2.Response

/**
 *  @Package:        com.tobey.pagger3study
 * @ClassName:      ApiResponse
 * @Author:         Tobey_r1
 * @CreateDate:     2022/6/19 14:43
 * @Description:     java类作用描述
 * @UpdateUser:        更新者
 * @UpdateDate:     2022/6/19 14:43
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
data class ApiResponse<T>(val status: Status, val data: T?, val message: String?){
    companion object {
        fun <T> error(error: Throwable): ApiResponse<T> {
            return ApiResponse(Status.ERROR,null,error.message ?: "unknown error")
        }

        fun <T> success(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() !=200) {
                    ApiResponse(Status.ERROR, body,response.message().toString())
                }else{
                    ApiResponse(Status.SUCCESS,body,response.message().toString())
                }
            }else{
                ApiResponse(Status.SUCCESS,null,response.message().toString())
            }
        }
    }
}
