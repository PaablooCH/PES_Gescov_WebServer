package com.gescov.webserver.service;

import com.gescov.webserver.dao.SubjectDao;
import com.gescov.webserver.model.Subject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectDao subjectDao;


    @Autowired
    public SubjectService(@Qualifier("subjectMongo") SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public int addSubject(Subject subject){
        return subjectDao.insertSubject(subject);
    }

    public List<Subject> getAllSubject(){
        return subjectDao.selectAllSubjects();
    }

    public Optional<Subject> selectSubjectByID(ObjectId id){
        return subjectDao.selectSubjectsById(id);
    }

    public int deleteSubject(ObjectId id){
        return subjectDao.deleteSubject(id);
    }

    public int updateSubject(ObjectId id, Subject subject){
        return subjectDao.updateSubject(id, subject);
    }




}
