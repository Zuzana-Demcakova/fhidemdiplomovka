package sk.euba.fhi.tajj.jdbc.entity;

public class QuestionView {
    private int id;
    private String content;
    private int number;
    private int number2;

    public QuestionView(int id, String content, int number) {
        this.id = id;
        this.content = content;
        this.number = number;
    }

    public QuestionView(int id, String content, int number, int number2) {
        this.id = id;
        this.content = content;
        this.number = number;
        this.number2 = number2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }
}
