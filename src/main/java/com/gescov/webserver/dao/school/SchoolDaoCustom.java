package com.gescov.webserver.dao.school;

public interface SchoolDaoCustom {

    boolean existsByIdAndAdministratorsIDIn(String schoolID, String adminID);

}
