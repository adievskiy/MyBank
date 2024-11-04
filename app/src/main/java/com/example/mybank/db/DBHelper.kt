package com.example.mybank.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "USERS"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
        const val KEY_ID = "id"
        const val KEY_LOGIN = "login"
        const val KEY_PASS = "pass"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_LOGIN + " TEXT," +
                KEY_PASS + " TEXT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun addUser(users: Users) {
        val values = ContentValues()
        values.put(KEY_ID, users.id)
        values.put(KEY_LOGIN, users.login)
        values.put(KEY_PASS, users.password)
        val db = writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun isIdExists(id: Int): Boolean {
        val db = this.readableDatabase
        val query = "SELECT COUNT(*) FROM $TABLE_NAME WHERE $KEY_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))
        cursor.use {
            if (it.moveToFirst()) {
                return it.getInt(0) > 0
            }
        }
        cursor.close()
        return false
    }

    @SuppressLint("Recycle")
    fun checkUser(login: String, pass: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $KEY_LOGIN = ? AND $KEY_PASS = ?"
        val cursor = db.rawQuery(query, arrayOf(login, pass))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun checkLogin(login: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $KEY_LOGIN = ?"
        val cursor = db.rawQuery(query, arrayOf(login))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}