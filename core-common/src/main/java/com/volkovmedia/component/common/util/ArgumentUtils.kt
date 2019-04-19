package com.volkovmedia.component.common.util

import android.app.Activity
import android.os.Parcelable
import androidx.fragment.app.Fragment

fun <T : Parcelable> Fragment.argumentParcelable(key: String) = lazy { arguments?.getParcelable<T>(key) }
fun Fragment.argumentBoolean(key: String) = lazy { arguments?.getBoolean(key) }
fun Fragment.argumentInt(key: String) = lazy { arguments?.getInt(key) }
fun Fragment.argumentLong(key: String) = lazy { arguments?.getLong(key) }
fun Fragment.argumentShort(key: String) = lazy { arguments?.getShort(key) }
fun Fragment.argumentChar(key: String) = lazy { arguments?.getChar(key) }
fun Fragment.argumentByte(key: String) = lazy { arguments?.getByte(key) }
fun Fragment.argumentFloat(key: String) = lazy { arguments?.getFloat(key) }
fun Fragment.argumentDouble(key: String) = lazy { arguments?.getDouble(key) }
fun Fragment.argumentCharSequence(key: String) = lazy { arguments?.getCharSequence(key) }
fun Fragment.argumentString(key: String) = lazy { arguments?.getString(key) }

fun <T : Parcelable> Activity.argumentParcelable(key: String) = intent?.getParcelableExtra<T>(key)
fun Activity.argumentBoolean(key: String, defaultValue: Boolean = false) = intent?.getBooleanExtra(key, defaultValue)
fun Activity.argumentInt(key: String, defaultValue: Int = 0) = intent?.getIntExtra(key, defaultValue)
fun Activity.argumentLong(key: String, defaultValue: Long = 0L) = intent?.getLongExtra(key, defaultValue)
fun Activity.argumentShort(key: String, defaultValue: Short = 0) = intent?.getShortExtra(key, defaultValue)
fun Activity.argumentChar(key: String, defaultValue: Char = ' ') = intent?.getCharExtra(key, defaultValue)
fun Activity.argumentByte(key: String, defaultValue: Byte = 0) = intent?.getByteExtra(key, defaultValue)
fun Activity.argumentFloat(key: String, defaultValue: Float = 0f) = intent?.getFloatExtra(key, defaultValue)
fun Activity.argumentDouble(key: String, defaultValue: Double = 0.toDouble()) = intent?.getDoubleExtra(key, defaultValue)
fun Activity.argumentCharSequence(key: String) = intent?.getCharSequenceExtra(key)
fun Activity.argumentString(key: String) = intent?.getStringExtra(key)