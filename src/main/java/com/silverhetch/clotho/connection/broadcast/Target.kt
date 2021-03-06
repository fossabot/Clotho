package com.silverhetch.clotho.connection.broadcast

import java.net.InetAddress

/**
 * The broadcast Interface
 */
interface Target {
    /**
     * The interface Name
     */
    fun name(): String
    /**
     * The [InetAddress] this interface
     */
    fun interfaceInetAddress(): InetAddress

    /**
     * The broadcast Address.
     */
    fun broadcastAddress(): InetAddress
}