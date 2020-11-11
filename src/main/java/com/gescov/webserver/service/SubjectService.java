package com.gescov.webserver.service;

import com.gescov.webserver.dao.subject.SubjectDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;



    public Subject addSubject(Subject subject, String creatorID){
        String schoolID = subject.getSchoolID();
        Optional<School> s =  schoolService.getSchoolById(schoolID);
        if(s.isEmpty())throw new NotFoundException(School.class, schoolID);
        Optional<User> u = userService.getUserById(creatorID);
        if(u.isEmpty())throw new NotFoundException(User.class, creatorID);
        subject.addTeacher(creatorID);
        return subjectDao.insert(subject);
    }

    public List<Subject> getAllSubject() { return subjectDao.findAll(); }

    public List<Subject> selectSubjectBySchoolId(String id){ return subjectDao.selectAllBySchoolId(id);}

    public Optional<Subject> findById(String id) { return subjectDao.findById(id); }

    public List<Subject> getSubjectBySchool(String schoolName) { return subjectDao.selectAllBySchoolName(schoolName); }

    public List<Subject> getSubjectByName(String name) { return subjectDao.findAllByName(name); }

    public void deleteSubject(String id){
        subjectDao.deleteById(id);
    }

    public void updateSubject(String id, String name){
        Optional<Subject> s = subjectDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Subject.class, id);
        s.get().setName(name);
        subjectDao.save(s.get());
    }

    public void addStudent(String id, String userId){
        Optional<Subject> s = subjectDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Subject.class, id);
        s.get().addStudent(userId);
        subjectDao.save(s.get());
    }

    public void addTeacher(String id, String userId){
        Optional<Subject> s = subjectDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Subject.class, id);
        s.get().addTeacher(userId);
        subjectDao.save(s.get());
    }
}

