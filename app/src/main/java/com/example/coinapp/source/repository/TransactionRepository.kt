package com.example.coinapp.source.repository

import com.example.coinapp.source.local.TransactionDao
import com.example.coinapp.source.local.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRepository @Inject constructor(val transactionDao: TransactionDao) {

    suspend fun insert(transactionModel: TransactionModel) = withContext(Dispatchers.IO) {
        transactionDao.insertTransaction(transactionModel)
    }

    fun getAllTransactions() = flow {
        val transactions = transactionDao.getAllTransactions()
        emit(transactions)
    }.flowOn(Dispatchers.IO)
}