package com.example.coinapp.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionModel(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "transactionTime") val transactionTime: String?,
    @ColumnInfo(name = "isDeposit") val isDeposit: Boolean?
)