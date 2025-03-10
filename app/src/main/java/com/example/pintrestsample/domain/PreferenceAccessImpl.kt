package com.example.pintrestsample.domain

import android.content.SharedPreferences
import android.util.Log
import com.example.pintrestsample.di.SharedPreferenceModule.KEY_TOKEN


class PreferenceAccessImpl(private val preferences: SharedPreferences) :
    PreferenceAccess {

    override fun getToken(): String {
        return preferences.getString(KEY_TOKEN, null) ?: ""
    }
}