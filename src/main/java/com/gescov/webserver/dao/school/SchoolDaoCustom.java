package com.gescov.webserver.dao.school;

public interface SchoolDaoCustom<T,ID> {

    boolean existsByIdAndAdministratorsIDIn(String schoolID, String adminID);

}
