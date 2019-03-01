package at.wrk.fmd.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import at.wrk.fmd.web.EinheitentypControllerTest;
import at.wrk.fmd.web.LagerstandortControllerTest;
import at.wrk.fmd.web.MaterialTypControllerTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    EinheitentypControllerTest.class,
    LagerstandortControllerTest.class,
    MaterialTypControllerTest.class,
    MaterialTypControllerTest.class,
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

public class JunitTestSuite {}
