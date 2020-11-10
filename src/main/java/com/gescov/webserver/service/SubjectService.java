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

    private final SubjectDao subjectDao;
    private final SchoolService schoolService;
    private final UserService userService;

    @Autowired
    public SubjectService(SubjectDao subjectDao, SchoolService schoolService, UserService userService) {
        this.subjectDao = subjectDao;
        this.schoolService = schoolService;
        this.userService = userService;
    }

    public Subject addSubject(Subject subject, String creatorID){
        String schoolID = subject.getSchoolID();
        Optional<School> s =  schoolService.getSchoolById(schoolID);
        if(s.isEmpty())throw new NotFoundException("School with 'id' " + schoolID + " not found!");
        Optional<User> u = userService.getUserById(creatorID);
        if(u.isEmpty())throw new NotFoundException("User with 'id' " + creatorID + " not found!");
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
        if (s.isEmpty()) throw new NotFoundException("Subject with 'id'" + id + "not found!");
        s.get().setName(name);
        subjectDao.save(s.get());
    }

    public void addStudent(String id, String userId){
        Optional<Subject> s = subjectDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Subject with 'id'" + id + "not found!");
        s.get().addStudent(userId);
        subjectDao.save(s.get());
    }

    public void addTeacher(String id, String userId){
        Optional<Subject> s = subjectDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Subject with 'id'" + id + "not found!");
        s.get().addTeacher(userId);
        subjectDao.save(s.get());
    }
}

