package com.example.kotlinflowapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(@PrimaryKey val label: Int, val preKey: Int?, val nextKey: Int?)