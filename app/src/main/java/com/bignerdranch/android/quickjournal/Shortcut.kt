package com.bignerdranch.android.quickjournal

import java.util.*

data class Shortcut(val id: UUID = UUID.randomUUID(),
                    var title: String = "",
                    var fields: ArrayList<String>,
                    var result: String = "")