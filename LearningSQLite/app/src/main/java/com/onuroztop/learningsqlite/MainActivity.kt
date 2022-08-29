package com.onuroztop.learningsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {

            // creating database
            val database = this.applicationContext.openOrCreateDatabase("MyDatabase", Context.MODE_PRIVATE,null)
            val query = "CREATE TABLE IF NOT EXISTS products(id INTEGER PRIMARY KEY ASC AUTOINCREMENT,name TEXT,price REAL)"
            database.execSQL(query)

            /* writing data
            val insertQuery = "INSERT INTO products(name,price) VALUES('Huawei P90',8550.5)"
            database.execSQL(insertQuery)
            */

            // reading data
            val readQuery = "SELECT * FROM products"
            val cursor = database.rawQuery(readQuery,null)

            val idColumnIndex = cursor.getColumnIndex("id")
            val nameColumnIndex = cursor.getColumnIndex("name")
            val priceColumnIndex = cursor.getColumnIndex("price")

            while (cursor.moveToNext()){

                println("ID    : ${cursor.getInt(idColumnIndex)}")
                println("NAME  : ${cursor.getString(nameColumnIndex)}")
                println("PRICE : ${cursor.getDouble(priceColumnIndex)}")

            }
            cursor.close()

        }catch (e:Exception){



        }


    }
}