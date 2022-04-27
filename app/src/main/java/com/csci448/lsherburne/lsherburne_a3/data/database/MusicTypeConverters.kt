package com.csci448.lsherburne.lsherburne_a3.data.database

import androidx.room.TypeConverter
import java.util.*

class MusicTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun fromString(uuidString: String) = UUID.fromString(uuidString)

}