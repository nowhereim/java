package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository <T,ID> extends Repository<T,ID>{

    // create , update
    T save(T data);

    //read


    Optional<T> findById(ID id); //Optional은 데이터가 있을 수도 있고 없을 수도 있음을 의미


    List<T> findAll();

    // delete
    void delete(ID id);
//
//    List<T> findOverScore(int score);
}
