package at.wrk.fmd.environment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import at.wrk.fmd.selenium.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    LoginWebsiteSeleniumTest.class,
    MaterialSeleniumTest.class,
    LagerstandortWebsiteSeleniumTest.class,
    BenutzerregistrierungSeleniumTest.class,
    MitarbeitertypSeleniumTest.class,
    PasswordAenderungSeleniumTest.class,
    EinheitentypSeleniumTest.class,
    MaterialtypSeleniumTest.class,
    ReportWebsiteSeleniumTest.class,
    MitarbeiterverwaltungWebsiteSeleniumTest.class
})

public class SeleniumSuite {}
