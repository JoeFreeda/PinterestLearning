package com.example.pintrestsample.domain

import android.content.SharedPreferences
import com.example.pintrestsample.data.PreferenceAccess
import com.example.pintrestsample.data.SharedPreferenceModule.KEY_TOKEN


class PreferenceAccessImpl(private val preferences: SharedPreferences) :
    PreferenceAccess {

    override fun getToken(): String {
        return preferences.getString(KEY_TOKEN, null) ?: ""
    }
}