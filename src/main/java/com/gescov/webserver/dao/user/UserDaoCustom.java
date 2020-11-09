package com.gescov.webserver.dao.user;

import com.gescov.webserver.model.User;
import com.mongodb.client.FindIterable;

import java.util.List;

public interface UserDaoCustom<T, ID> {

    List<User> findAllBySchoolsID(String schoolID);

}
