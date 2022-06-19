package com.tobery.livedata.call.livedatalib

import retrofit2.Response


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
