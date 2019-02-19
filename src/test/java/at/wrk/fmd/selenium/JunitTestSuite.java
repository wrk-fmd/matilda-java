package at.wrk.fmd.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    LoginWebsiteTest.class,
    MaterialTest.class,
    LagerstandortWebsiteTest.class,
    BenutzerregistrierungTest.class,
    MitarbeitertypTest.class,
    PasswordAenderungTest.class,
    EinheitentypTest.class,
    MaterialtypTest.class,
    ReportWebsiteTest.class,
    MitarbeiterverwaltungWebsiteTest.class
})

public class JunitTestSuite {

}
