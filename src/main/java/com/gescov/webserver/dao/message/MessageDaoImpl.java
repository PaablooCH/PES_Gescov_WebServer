package com.gescov.webserver.dao.message;

import com.gescov.webserver.dao.classSession.ClassSessionDao;
import com.gescov.webserver.exception.ClassroomDateException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository("messageMongo")
public class MessageDaoImpl<T,ID> implements MessageDaoCustom<T,ID> {

}
