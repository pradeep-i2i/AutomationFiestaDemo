package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

public class CustomLogger {
    private static CustomLogger instance;
    private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);
    private CustomLogger() {}

    public static CustomLogger getInstance() {
        if (instance == null) {
            instance = new CustomLogger();
        }
        return instance;
    }
    public void info(final String message) {
        logger.info(message);
        Reporter.log(message + "<br>");
    }
    public void warn(final String message) {
        String msg = message;
        logger.warn(message);
        msg = "<div class=\"skipped\">" + msg + "</div>"; // yellow color from reportng css
        Reporter.log(msg + "<br>");
    }
    public void error(final String message) {
        String msg = message;
        logger.error(message);
        msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
        Reporter.log(msg + "<br>");
    }
    public void error(final String message, final Throwable t) {
        String msg = message;
        logger.error(message, t); // Logs exception with stack trace
        msg = "<div class=\"failedConfig\">" + msg + " | Exception: " + t.getMessage() + "</div>";
        Reporter.log(msg + "<br>");
    }
    public void step(int step, String message) {
        String logMessage = String.format("--------==[ Step %d - %s ]==--------", step, message);
        logger.info(logMessage);
    }
    public void logTestCaseStart(String testCaseName) {
        logger.info("====================START OF TEST CASE====================");
        String message = "Test Case '" + testCaseName + "' has started";
        logger.info(message);
    }
    public void logTestCaseEnd(String testCaseName) {
        String message = "Test Case '" + testCaseName + "' has ended";
        logger.info(message);
        logger.info("====================END OF TEST CASE====================");
    }

}
