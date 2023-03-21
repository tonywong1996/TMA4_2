package com.example.tma4_2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student student);

    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Delete
    void delete(Student student);


    @Query("SELECT * FROM student WHERE id = :id")
    Student getById(long id);
}
