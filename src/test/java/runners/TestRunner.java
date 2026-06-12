package runners;

import com.intuit.karate.junit5.Karate;

public class TestRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:api/features/hello.feature");
        //return Karate.run("classpath:features").relativeTo(getClass());
    }
}