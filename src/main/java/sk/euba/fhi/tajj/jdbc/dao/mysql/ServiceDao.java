package sk.euba.fhi.tajj.jdbc.dao.mysql;

import sk.euba.fhi.tajj.jdbc.entity.*;

import java.util.List;

public interface ServiceDao {

    List<Question> generateTest();

    void addSessionForStudent(Student student);

    void addAnswer(Answer answer);

    List<QuestionView> theMostInvalidQuestions();
    List<QuestionView> theMostValidQuestions();
    List<QuestionView> detailedQuestions();
}
