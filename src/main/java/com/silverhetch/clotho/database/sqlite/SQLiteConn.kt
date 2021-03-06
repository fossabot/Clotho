package com.silverhetch.clotho.database.sqlite

import com.silverhetch.clotho.Source
import java.sql.Connection
import java.sql.DriverManager

/**
 * Source of SQLite connection.
 */
class SQLiteConn(private val name: String) : Source<Connection> {
    override fun value(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:$name")
    }
}