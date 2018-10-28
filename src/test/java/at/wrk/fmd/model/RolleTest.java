package at.wrk.fmd.model;

public class RolleTest {

    private long id;
    private String bezeichnung;

    public RolleTest() {

    }

    public RolleTest(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}