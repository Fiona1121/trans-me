package com.example.restservice.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.restservice.Model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
    // @Query("{name:'?0', password:'?1'}")
    // Account findItemByName(String name, String password);

    List<Account> findByUsername(@Param("name") String username);
    // 不能用欸，why
    // 可以跑一下 main 裡面的 code 就知道了
    
    // 第一種是 override Mongo 裡面的
    // 第二種是 override CRUDrepo

    // 測試資料 @ 費南's DB
    // userID: "test", password: "test"

    public long count();
}

// 和 tutorial 不一樣
// 沒有 rest 所以沒有改 endopoint
// 內部呢，只是自己定義而已