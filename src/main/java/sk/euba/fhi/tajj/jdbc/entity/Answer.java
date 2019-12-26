package sk.euba.fhi.tajj.jdbc.entity;

public class Answer {

    private String session;
    private int question;
    private int answer;

    public Answer(String session, int question, int answer) {
        this.session = session;
        this.question = question;
        this.answer = answer;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "session='" + session + '\'' +
                ", question=" + question +
                ", answer=" + answer +
                '}';
    }
}