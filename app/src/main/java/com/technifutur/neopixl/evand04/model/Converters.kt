package com.technifutur.neopixl.evand04.model

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimeStamp(value: Long?): Date?{
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimesStamp(date:Date?): Long? {
        return date?.time?.toLong()
    }
}