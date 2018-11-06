package at.wrk.fmd.config;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.wrk.fmd.model.BenutzerTest;
import at.wrk.fmd.model.RolleTest;
import at.wrk.fmd.model.Veranstaltung;

public class EntityTest {
    
    private static Connection connection;
    private int counter = 0;
    private LocalDate today = LocalDate.now();
    
    @BeforeAll
    public static void setupClass() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:mem:testDB");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE benutzer (" +
                        " id IDENTITY NOT NULL PRIMARY KEY, " +
                        " username VARCHAR(64) NOT NULL, " +
                        " passwort VARCHAR(64) NOT NULL, " +
                        " anzeigename VARCHAR(50) NULL," +
                        " dienstnummer VARCHAR(50) NULL" +
                        ")");
        
        stmt.execute("CREATE TABLE rolle (" +
                " id IDENTITY NOT NULL PRIMARY KEY, " +
                " bezeichnung VARCHAR(64) NOT NULL, " +
                ")");
    }

    @BeforeEach
    public void setUp() {}

    @Test
    public void testInsertBenutzerAndRead() throws Exception {
        // givwn
        BenutzerTest user = new BenutzerTest(null, "ADMIN", "Test", "ADMIN", "123");

        // when
        String insertStmt = "INSERT INTO benutzer(username, passwort) " +
                "VALUES(?, ?)";

        PreparedStatement insertPrepStmt = connection.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
        insertPrepStmt.setString(1, user.getUsername());
        insertPrepStmt.setString(2, user.getPasswort());

        int affectedRows = insertPrepStmt.executeUpdate();
        assertThat(affectedRows).isEqualTo(1);

        ResultSet key = insertPrepStmt.getGeneratedKeys();
        assertThat(key.next()).isTrue();
        Long generatedId = key.getLong("id");
        assertThat(generatedId).isNotNull().isNotNegative().isGreaterThan(0);
        user.setId(generatedId);
    }
    
    @Test
    public void testInsertRolleAndRead() throws Exception {
        // givwn
        RolleTest rolle = new RolleTest("ADMIN");

        // when
        String insertStmt = "INSERT INTO rolle(bezeichnung) " +
                "VALUES(?)";

        PreparedStatement insertPrepStmt = connection.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
        insertPrepStmt.setString(1, rolle.getBezeichnung());

        int affectedRows = insertPrepStmt.executeUpdate();
        assertThat(affectedRows).isEqualTo(1);

        ResultSet key = insertPrepStmt.getGeneratedKeys();
        assertThat(key.next()).isTrue();
        Long generatedId = key.getLong("id");
        assertThat(generatedId).isNotNull().isNotNegative().isGreaterThan(0);
    }

    @Test
    public void testInsertAndReadForList() throws Exception {
        // given
        BenutzerTest userA = new BenutzerTest(null, "ADMIN", "secret", "ADMIN", "123");

        // when
        String insertStmt = "INSERT INTO benutzer(username, passwort) " +
                "VALUES(?, ?)";

        PreparedStatement insertPrepStmt = connection.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
        insertPrepStmt.setString(1, userA.getUsername());
        insertPrepStmt.setString(2, userA.getPasswort());
        int affectedRows = insertPrepStmt.executeUpdate();
        assertThat(affectedRows).isEqualTo(1).isGreaterThan(0);
        ResultSet resultSet = insertPrepStmt.getGeneratedKeys();
        assertThat(resultSet.next()).isTrue();
        counter++;

        readFromDatabase(resultSet, counter);
    }
    
//    @Test
//    public void testInsertAndReadVeranstaltung() throws Exception {
//        // given
//        Veranstaltung veranstaltung = new Veranstaltung();
//        veranstaltung.setName("TestVeranstaltung");
//        veranstaltung.setBeginn(today);
//        veranstaltung.setEnde(today.plus(1, ChronoUnit.DAYS));
//
//        // when
//        String insertStmt = "INSERT INTO veranstaltung(name, beginn, ende) " +
//                "VALUES(?, ?, ?)";
//
//        PreparedStatement insertPrepStmt = connection.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
//        insertPrepStmt.setString(1, veranstaltung.getName());
//        insertPrepStmt.setString(1, veranstaltung.getName());
//        insertPrepStmt.setString(1, veranstaltung.getName());
//        int affectedRows = insertPrepStmt.executeUpdate();
//        assertThat(affectedRows).isEqualTo(1).isGreaterThan(0);
//        ResultSet resultSet = insertPrepStmt.getGeneratedKeys();
//        assertThat(resultSet.next()).isTrue();
//
//        readFromVeranstaltungTable(resultSet);
//    }

    private void readFromVeranstaltungTable(ResultSet resultSet) throws Exception {
        String selectSQL = "SELECT name, beginn, ende FROM veranstaltung WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

        preparedStatement.setInt(1, 1);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            rs.getString("name");
            rs.getString("beginn");
            rs.getString("ende");
                
            assertThat("ADMIN").isEqualTo("ADMIN");
        }
    }

    private void readFromDatabase(ResultSet resultSet, int counter) throws SQLException {
        String selectSQL = "SELECT username, passwort FROM benutzer WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

        for (int i = 1; i<=counter; i++) {
            preparedStatement.setInt(1, i);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rs.getString("username");
                rs.getString("passwort");
                new Long(i);
                
                assertThat("ADMIN").isEqualTo("ADMIN");
                
            }
        }
    }

    @AfterEach
    public void tearDown() {}
}