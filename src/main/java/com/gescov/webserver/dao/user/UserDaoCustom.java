package com.gescov.webserver.dao.user;

import com.gescov.webserver.model.User;

import java.util.List;

public interface UserDaoCustom {

    List<User> findAllBySchoolID(String schoolID);

}
