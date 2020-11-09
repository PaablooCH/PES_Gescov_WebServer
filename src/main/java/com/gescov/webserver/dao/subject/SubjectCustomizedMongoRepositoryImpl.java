package com.gescov.webserver.dao.subject;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import com.gescov.webserver.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("subjectMongo")
public class SubjectCustomizedMongoRepositoryImpl<T,ID> implements SubjectCustomizedMongoRepository<T,ID> {

    private final MongoTemplate mongoTemplate;
    private final SchoolDao schoolDao;

    @Autowired
    public SubjectCustomizedMongoRepositoryImpl(MongoTemplate mongoTemplate, SchoolDao schoolDao) {
        this.mongoTemplate = mongoTemplate;
        this.schoolDao = schoolDao;
    }

    public List<Subject> selectAllBySchoolId (String id){
        Optional<School> school = schoolDao.findById(id);
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(school.get().getId()));
        return mongoTemplate.find(q, Subject.class);
    }

    public List<Subject> selectAllBySchoolName (String schoolName){
        School school = schoolDao.findByName(schoolName);
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(school.getId()));
        return mongoTemplate.find(q, Subject.class);
    }
}






    /*
    @Override
    public List<Subject> selectAllSubjects() {
        List<Subject> allSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find();
        for(Subject s : result){
            allSubjects.add(s);
        }
        return allSubjects;
    }

    @Override
    public List<Subject> selectSubjectsBySchool(String school) {
        List<Subject> schoolSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find(eq("school.name",school));
        for (Subject s : result) {
            schoolSubjects.add(s);
        }
        return schoolSubjects;
    }

    @Override
    public List<Subject> selectSubjectsByName(String name) {
        List<Subject> nameSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find(eq("name", name));
        for (Subject s : result) {
            nameSubjects.add(s);
        }
        return nameSubjects;
    }

    @Override
    public int deleteSubject(String name) {
        subjectCollection.findOneAndDelete(eq("name", name));
        return 1;
    }

    @Override
    public int updateSubject(String name, Subject subject) {
        subjectCollection.findOneAndUpdate(eq("name", name), set("name", subject.getName()));
        return 1;
    }
}*/
 
