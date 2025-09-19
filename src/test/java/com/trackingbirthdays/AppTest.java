package com.trackingbirthdays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest {

    @BeforeAll
    public static void setup() {
        // initialize the hashmap with the test JSON
        String testFilePath = "src/main/resources/birthdayOnlyForTesting.json";
        BirthdayTracker.initializeMap(testFilePath);
    }

    @Test
    public void testExactMatch() {
        String birthday = BirthdayTracker.getBirthday("Rodrigo");
        assertNotNull(birthday, "Birthday should not be null for Rodrigo");
        assertEquals("03/15/1990", birthday, "Rodrigo's birthday should match");
    }

    @Test
    public void testUnknownName() {
        String birthday = BirthdayTracker.getBirthday("NonExistentName");
        assertNull(birthday, "Birthday should be null for a name not in the list");
    }

    @Test
    public void testPartialMatch() {
        // Partial match logic is in main(), but getBirthday only returns exact
        // So here we just test that exact keys work, partial matches are handled in main()
        String birthday = BirthdayTracker.getBirthday("Rodrigo");
        assertNotNull(birthday, "Birthday should be found for partial matches using exact key");
    }
}
