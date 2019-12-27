package sk.euba.fhi.tajj.jdbc;

import ro.pippo.core.Pippo;
import sk.euba.fhi.tajj.jdbc.dao.mysql.ServiceDao;
import sk.euba.fhi.tajj.jdbc.dao.mysql.impl.ServiceDaoImpl;
import sk.euba.fhi.tajj.jdbc.entity.*;

import java.util.*;

public class AppClass {

    private static ServiceDao questionsDao = new ServiceDaoImpl();
    private static String MASTER_KEY = "demcakova_iman5";

    public static void main(String[] args) {
        Pippo pippo = new Pippo();
        pippo.getServer().getSettings().port(8080);
        pippo.getServer().getSettings().host("0.0.0.0");
        pippo.addPublicResourceRoute(); // "/public/..."

        pippo.GET("/", routeContext -> {
            //Map<String, Object> model = generateTest();
            routeContext.render("s1_login");
        });

        pippo.GET("/s1/login", routeContext -> {
            routeContext.render("s1_login");
        });

        pippo.POST("/s1/test", routeContext -> {
            // submit student
            String meno = routeContext.getParameter("formularMeno").toString();
            String priezvisko = routeContext.getParameter("formularPriezvisko").toString();
            String session = UUID.randomUUID().toString();

            Student student = new Student(0, meno, priezvisko, session);
            questionsDao.addSessionForStudent(student);

            // redirect the student to test page
            HashMap<String, Object> params = new HashMap<>();
            params.put("session", student.getSession());
            routeContext.redirect("/s1/test", params);
        });

        pippo.GET("/s1/test", routeContext -> {
            Map<String, Object> test = generateTest();
            test.put("session", routeContext.getRequest().getParameter("session"));
            routeContext.render("s1_test", test);
        });

        pippo.POST("/s1/results", routeContext -> {
            System.out.println("Vyhodnotenie testu poslat");
            List<Answer> answers = new ArrayList<>();
            String session =  routeContext.getParameter("session").toString();
            routeContext.getRequest().getParameters()
                    .forEach((key, value) -> {
                        if (key.startsWith("question_")) {
                            int questionId = new Integer(key.replaceAll("question_", ""));
                            int answerId = value.toInt();
                            answers.add(new Answer(session, questionId, answerId));
                        }
                    });

            for (Answer answer : answers) {
                questionsDao.addAnswer(answer);
            }
            routeContext.redirect("/s1/results");
        });

        pippo.GET("/s1/results", routeContext -> {
            routeContext.render("s1_results");
        });


        pippo.GET("/s2/login", routeContext -> {
            routeContext.render("s2_login");
        });

        pippo.GET("/s2/invalid", routeContext -> {
            routeContext.render("s2_invalid");
        });

        pippo.POST("/s2/results", routeContext -> {
            String masterKey = routeContext.getParameter("masterKey").toString();
            System.out.println("masterKey" + masterKey);

            if (MASTER_KEY.equals(masterKey)) {
                System.out.println("OK master key");
                routeContext.redirect("/s2/results?masterKey="+masterKey);
            } else {
                routeContext.redirect("/s2/invalid");
            }
        });

        pippo.GET("/s2/results", routeContext -> {
            String masterKey = routeContext.getParameter("masterKey").toString();

            if (MASTER_KEY.equals(masterKey)) {
                Map<String, Object> resultsView = generateView();
                routeContext.render("s2_results", resultsView);
            } else {
                routeContext.redirect("/s2/invalid");
            }

        });



        pippo.start();
    }

    private static Map<String, Object> generateTest() {
        List<Question> questions = questionsDao.generateTest();

        Map<String, Object> model = new HashMap<>();
        model.put("questions", questions);

        return model;
    }

    private static Map<String, Object> generateView() {
        List<QuestionView> questions = questionsDao.theMostInvalidQuestions();
        List<QuestionView> badsQuestions = questionsDao.theMostValidQuestions();
        List<QuestionView> detailedQuestions = questionsDao.detailedQuestions();

        Map<String, Object> model = new HashMap<>();
        model.put("questions", questions);
        model.put("badsQuestions", badsQuestions);
        model.put("detailedQuestions", detailedQuestions);

        return model;
    }

}
