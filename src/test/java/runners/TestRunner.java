package runners;

import com.intuit.karate.junit5.Karate;

public class TestRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:api/features/end-to-end.feature").relativeTo(getClass());
        // return Karate.run("classpath:features").relativeTo(getClass());
    }
}