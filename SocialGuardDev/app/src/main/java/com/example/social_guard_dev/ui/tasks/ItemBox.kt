package com.example.social_guard_dev.ui.tasks

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.social_guard_dev.R
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class ItemBox (
    var name: String,
    var desc: String,
    var due: LocalTime?,
    var dateOfComp: LocalDate?,
    var id: UUID = UUID.randomUUID()
)
{
    fun completion() = dateOfComp != null
    fun imageResource(): Int = if(completion()) R.drawable.ic_checkedbox else R.drawable.ic_uncheckedbox
    fun imageColor(context: Context): Int = if(completion()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
}
