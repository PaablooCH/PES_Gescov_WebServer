package com.gescov.webserver.service;

import com.gescov.webserver.dao.SubjectDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao) { this.subjectDao = subjectDao; }

    public Subject addSubject(Subject subject){
        return subjectDao.insert(subject);
    }

    public List<Subject> getAllSubject() { return subjectDao.findAll(); }

    public Optional<Subject> findById(String id) { return subjectDao.findById(id); }

    public List<Subject> getSubjectBySchool(String school) { return subjectDao.findAllBySchool(school); }

    public List<Subject> getSubjectByName(String name) { return subjectDao.findAllByName(name); }

    public void deleteSubject(String id){
        subjectDao.deleteById(id);
    }

    public void updateSubject(String id, String name){
        Optional<Subject> s = subjectDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Subject with 'id'" + id + "not found!");
        s.get().setName(name);
        subjectDao.insert(s.get());
    }

}

