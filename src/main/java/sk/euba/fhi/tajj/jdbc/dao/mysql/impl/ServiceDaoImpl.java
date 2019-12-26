package sk.euba.fhi.tajj.jdbc.dao.mysql.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.tajj.jdbc.dao.mysql.ServiceDao;
import sk.euba.fhi.tajj.jdbc.entity.*;
import sk.euba.fhi.tajj.jdbc.factory.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDaoImpl.class);

    public List<Question> generateTest() {
        List<Question> questions = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                String sql = "SELECT id, content, answer_1, answer_2, answer_3, answer_4 FROM questions";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String content = rs.getString("content");
                    String answer_1 = rs.getString("answer_1");
                    String answer_2 = rs.getString("answer_2");
                    String answer_3 = rs.getString("answer_3");
                    String answer_4 = rs.getString("answer_4");

                    //logger.debug("Id: {}, meno: {}, priezvisko: {}, predmet: {}", id, meno, priezvisko, predmet);

                    Question subject = new Question(id, content, answer_1, answer_2, answer_3, answer_4);
                    questions.add(subject);
            }
            } catch (SQLException ex) {
                logger.error("Nepodarilo sa ziskat questions z databazky", ex);
            }
        }
        return questions;
    }

    public void addSessionForStudent(Student student) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                String sql = "INSERT INTO students (name, surname, session) VALUES (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, student.getMeno());
                ps.setString(2, student.getPriezvisko());
                ps.setString(3, student.getSession());

                int rows = ps.executeUpdate();
                System.out.println("Vlozil som {} zakaznikov." + rows);
            } catch (SQLException ex) {
                logger.error("Nepodarilo sa vlozit zakaznikov.", ex);
            }
        }
    }

    @Override
    public void addAnswer(Answer answer) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                String sql = "INSERT INTO answers (session, question, answer) VALUES (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, answer.getSession());
                ps.setInt(2, answer.getQuestion());
                ps.setInt(3, answer.getAnswer());

                int rows = ps.executeUpdate();
                System.out.println("Vlozil som {} odpovedi." + rows);
            } catch (SQLException ex) {
                logger.error("Nepodarilo sa vlozit zakaznikov.", ex);
            }
        }
    }

    @Override
    public List<QuestionView> theMostInvalidQuestions() {
        List<QuestionView> questions = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                String sql = "select B.id, B.content, COUNT(*) as oks from answers A " +
                        "JOIN questions B ON A.question = B.id " +
                        "WHERE A.answer = B.correct_answer " +
                        "GROUP BY B.id " +
                        "ORDER BY oks ASC";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String content = rs.getString("content");
                    int oks = rs.getInt("oks");
                    //logger.debug("Id: {}, meno: {}, priezvisko: {}, predmet: {}", id, meno, priezvisko, predmet);

                    QuestionView questionView = new QuestionView(id, content, oks);
                    questions.add(questionView);
                }
            } catch (SQLException ex) {
                logger.error("Nepodarilo sa ziskat questions z databazky", ex);
            }
        }
        return questions;
    }

    @Override
    public List<QuestionView> theMostValidQuestions() {
        List<QuestionView> questions = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                String sql = "select B.id, B.content, COUNT(*) as bads from answers A " +
                        "JOIN questions B ON A.question = B.id " +
                        "WHERE A.answer != B.correct_answer " +
                        "GROUP BY B.id " +
                        "ORDER BY bads DESC";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String content = rs.getString("content");
                    int bads = rs.getInt("bads");

                    QuestionView questionView = new QuestionView(id, content, bads);
                    questions.add(questionView);
                }
            } catch (SQLException ex) {
                logger.error("Nepodarilo sa ziskat questions z databazky", ex);
            }
        }
        return questions;
    }


    @Override
    public List<QuestionView> detailedQuestions() {
        List<QuestionView> questions = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                String sql = "select B.id, B.content, A.answer, COUNT(*) as responses from answers A " +
                        "JOIN questions B ON A.question = B.id " +
                        "GROUP BY A.question, A.answer " +
                        "ORDER BY A.question, responses DESC";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String content = rs.getString("content");
                    int answer = rs.getInt("answer");
                    int responses = rs.getInt("responses");

                    QuestionView questionView = new QuestionView(id, content, answer, responses);
                    questions.add(questionView);
                }
            } catch (SQLException ex) {
                logger.error("Nepodarilo sa ziskat questions z databazky", ex);
            }
        }
        return questions;
    }

}
