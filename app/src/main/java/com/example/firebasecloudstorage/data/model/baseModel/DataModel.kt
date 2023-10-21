package com.example.firebasecloudstorage.data.model.baseModel



/**
 * A base model for Data layer, DataModel sealed class contain two sub class, Success and Error
 * Success for success results
 * Error for errors like api call errors
 **/
sealed class DataModel<out T> {
    data class Success<T>(val data: T) : DataModel<T>()
    data class Error(val error: ErrorModel) : DataModel<Nothing>()
}

/**
 * ErrorModel class is a class contain two properties errorCode and errorMessage
 * */
data class ErrorModel(val errorMessage:String)
