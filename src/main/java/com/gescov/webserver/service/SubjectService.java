package com.gescov.webserver.service;

import com.gescov.webserver.dao.SubjectDao;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Subject> getSubjectBySchool(String school){ return subjectDao.selectSubjectsBySchool(school); }

    public List<Subject> getSubjectByName(String name){ return subjectDao.selectSubjectsByName(name); }

    public int deleteSubject(String name){
        return subjectDao.deleteSubject(name);
    }

    public int updateSubject(String name, Subject subject){
        return subjectDao.updateSubject(name, subject);
    }

}

