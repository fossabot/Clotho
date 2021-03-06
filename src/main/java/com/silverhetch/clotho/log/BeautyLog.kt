package com.silverhetch.clotho.log

import com.silverhetch.clotho.Source

/**
 * Source that build a [Log] object with tag and color.
 */
class BeautyLog : Source<Log> {
    override fun value(): Log {
        return TagLog(
            ANSIColorLog(
                SystemPrintLog()
            )
        )
    }
}