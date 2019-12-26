package sk.euba.fhi.tajj.jdbc.entity;

public class Student {

    private int id;
    private String meno;
    private String priezvisko;
    private String session;

    // constructor
    public Student(int id, String meno, String priezvisko, String session) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
