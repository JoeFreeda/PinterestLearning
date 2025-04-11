package com.example.pintrestsample.data.services


sealed class ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error<T>(val errorMessage: String): ApiResponse<T>()
    data class Failed<T>(val failedException:String): ApiResponse<T>()
}