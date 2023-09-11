package com.example.coinapp.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionModel::class], version = 1)
abstract class TransactionsDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

}