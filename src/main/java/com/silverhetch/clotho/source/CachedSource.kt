package com.silverhetch.clotho.source

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.storage.Ceres
import com.silverhetch.clotho.time.ToUTCTimestamp
import java.lang.NumberFormatException

/**
 * String source that cached by given duration. Use the origin one if expired.
 *
 * @param duration The expire duration in millisecond.
 */
class CachedSource(
    key: String,
    private val duration: Long,
    private val ceres: Ceres,
    private val origin: Source<String>
) : Source<String> {
    companion object {
        private const val CACHED_TIME_ = "CACHED_TIME_"
        private const val CACHED_KEY_ = "CACHED_KEY_"
    }

    private val cachedTime = "$CACHED_TIME_$key"
    private val cachedKey = "$CACHED_KEY_$key"

    override fun value(): String {
        val current = ToUTCTimestamp(System.currentTimeMillis()).value()
        val tokenTime = tokenTime()
        return if (current - tokenTime > duration) {
            origin.value().apply {
                ceres.store(cachedTime, current.toString())
                ceres.store(cachedKey, this)
            }
        } else {
            ceres.get(cachedKey)
        }
    }

    private fun tokenTime(): Long {
        return try {
            ceres.get(cachedTime).toLong()
        } catch (e: NumberFormatException) {
            0L
        }
    }
}