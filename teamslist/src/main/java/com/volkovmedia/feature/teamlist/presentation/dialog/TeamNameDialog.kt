package com.volkovmedia.feature.teamlist.presentation.dialog

import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog

internal class TeamNameDialog(private val context: Context) {

    fun show(currentName: String? = null, onAccept: (String) -> Unit) {
        val input = createView(currentName)

        AlertDialog.Builder(context)
            .setView(input)
            .setPositiveButton(android.R.string.ok) { _, _ -> onAccept.invoke(input.text.toString()) }
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun createView(currentName: String? = null) = EditText(context).apply {
        setSingleLine()
        setPadding(64, 64, 64, 64)

        layoutParams = RelativeLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        ).apply {
            setMargins(64, 64, 64, 64)
        }

        currentName?.let {
            setText(it)
            setSelection(it.length)
        }
    }

}