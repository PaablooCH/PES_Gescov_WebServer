package com.gescov.webserver.dao;
/*
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.School;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository("schoolMongo")
public class SchoolMongoDB implements SchoolDao  {

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<School> schoolCollection;

    /*@PostConstruct
    void init() {
        schoolCollection = client.getDatabase("Gescov").getCollection("schools", School.class);
    }*/

    //@Override
    /*public int insertSchool(School school) {
        if (schoolCollection.countDocuments(eq("name", school.getName())) == 0) {
            schoolCollection.add(school);
            return 1;
        }
        throw new AlreadyExistsException("School with 'name' " + school.getName() + " already exists!");
    }*/
/*
    @Override
    public List<School> selectAllSchools() {
        List<School> allSchools = new ArrayList<>();
        FindIterable<School> result = schoolCollection.find();
        for (School s : result) allSchools.add(s);
        return allSchools;
    }

    @Override
    public School selectSchoolById(String id) {
        return null;
    }


    @Override
    public School selectSchoolByName(String schoolName) {
        FindIterable<School> result = schoolCollection.find(eq("name", schoolName));
        School s = null;
        for (School sc : result) s = sc;
        if (s == null) throw new NotFoundException("School with 'name' " + schoolName + " not found!");
        return s;
    }

    @Override
    public int deleteSchoolById(String id) {
        schoolCollection.findOneAndDelete(eq("_id", id));
        return 1;
    }

    Override
    public int updateSchoolNameById(String id, String name) {
        return 0;
    }

    @Override
    public int updateSchoolStateById(String id, String state) {
        return 0;
    }


    public int updateSchoolNameById(ObjectId id, String name) {
        schoolCollection.findOneAndUpdate(eq("_id", id), set("name", name));
        return 1;
    }


    public int updateSchoolStateById(ObjectId id, String state) {
        schoolCollection.findOneAndUpdate(eq("_id", id), set("state", state));
        return 1;
    }

    @Override
    public <S extends School> S save(S entity) {
        return null;
    }

    @Override
    public <S extends School> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<School> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<School> findAll() {
        return null;
    }

    @Override
    public Iterable<School> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(School entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends School> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<School> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<School> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends School> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends School> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends School> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends School> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends School> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends School> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends School> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends School> boolean exists(Example<S> example) {
        return false;
    }
}*/


