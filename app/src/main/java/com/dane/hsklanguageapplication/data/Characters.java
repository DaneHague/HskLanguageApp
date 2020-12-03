package com.dane.hsklanguageapplication.data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "character_table")
public class Characters {
    @PrimaryKey
    int id;
}
