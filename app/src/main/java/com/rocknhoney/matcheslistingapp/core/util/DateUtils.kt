package com.rocknhoney.matcheslistingapp.core.util

import com.rocknhoney.matcheslistingapp.core.util.Utils.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Extension function to format a Long value representing a date into a string with a specified date format.
 *
 * @return Formatted date string.
 */
fun Long.toDateFormat(): String {
    val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(this))
}