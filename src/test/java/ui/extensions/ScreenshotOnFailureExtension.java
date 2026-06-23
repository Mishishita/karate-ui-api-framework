package ui.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import ui.base.BaseTest;

public class ScreenshotOnFailureExtension
        implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable)
            throws Throwable {

        Object testInstance =
                context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest baseTest) {

            baseTest.takeScreenshot(
                    context.getRequiredTestMethod()
                            .getName());
        }

        throw throwable;
    }
}