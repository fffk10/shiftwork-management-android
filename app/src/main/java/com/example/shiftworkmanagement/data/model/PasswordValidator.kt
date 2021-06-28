package com.example.shiftworkmanagement.data.model

import java.util.regex.Matcher
import java.util.regex.Pattern

class PasswordValidator {
    /**
     *  8文字以上24文字以下半角英数字のパスワードパターン
     */
    private val PASSWORD_PATTERN = """((?=.* \d)(?=.* [a-z])(?=.* [A-Z])(?=.* [@#$%]).{8,24})"""

    private var pattern: Pattern = Pattern.compile(PASSWORD_PATTERN)
    private lateinit var matcher: Matcher

    fun validate(password: String): Boolean {
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}