package at.wrk.fmd.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import at.wrk.fmd.web.EinheitentypControllerTest;
import at.wrk.fmd.web.LagerstandortControllerTest;
import at.wrk.fmd.web.MainControllerTest;
import at.wrk.fmd.web.MaterialTypControllerTest;
import at.wrk.fmd.web.MaterialtypEinheitentypControllerTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    EinheitentypControllerTest.class,
    LagerstandortControllerTest.class,
    MainControllerTest.class,
    MaterialTypControllerTest.class,
    MaterialtypEinheitentypControllerTest.class,
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
