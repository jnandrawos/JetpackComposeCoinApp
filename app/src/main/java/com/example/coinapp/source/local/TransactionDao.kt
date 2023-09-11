package com.example.coinapp.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): List<TransactionModel>

    @Insert
    suspend fun insertTransaction(transactionModel: TransactionModel)
}