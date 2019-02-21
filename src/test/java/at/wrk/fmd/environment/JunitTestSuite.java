package at.wrk.fmd.environment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import at.wrk.fmd.selenium.BenutzerregistrierungTest;
import at.wrk.fmd.selenium.EinheitentypTest;
import at.wrk.fmd.selenium.LagerstandortWebsiteTest;
import at.wrk.fmd.selenium.LoginWebsiteTest;
import at.wrk.fmd.selenium.MaterialTest;
import at.wrk.fmd.selenium.MaterialtypTest;
import at.wrk.fmd.selenium.MitarbeitertypTest;
import at.wrk.fmd.selenium.MitarbeiterverwaltungWebsiteTest;
import at.wrk.fmd.selenium.PasswordAenderungTest;
import at.wrk.fmd.selenium.ReportWebsiteTest;

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

public class JunitTestSuite {}
