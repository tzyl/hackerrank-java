package com.tzyl.hackerrank.worldcodesprint.wcs13.landslide;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SolutionTest {
    @Test
    public void testBasicCase() throws IOException {
        int n = 10;
        String input =
                "1 9\n" +
                        "4 8\n" +
                        "9 7\n" +
                        "2 4\n" +
                        "3 6\n" +
                        "1 2\n" +
                        "9 10\n" +
                        "2 3\n" +
                        "4 5\n" +
                        "14\n" +
                        "q 1 6\n" +
                        "q 9 2\n" +
                        "d 2 3\n" +
                        "c 3 9\n" +
                        "d 1 9\n" +
                        "q 1 6\n" +
                        "q 5 10\n" +
                        "c 1 9\n" +
                        "q 2 7\n" +
                        "d 1 10\n" +
                        "q 5 3\n" +
                        "c 2 3\n" +
                        "q 6 4\n" +
                        "q 2 3\n";
        String expected =
                "3\n" +
                        "2\n" +
                        "Impossible\n" +
                        "Impossible\n" +
                        "3\n" +
                        "Impossible\n" +
                        "3\n" +
                        "1\n";

        // Set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set stdout
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Solution.landslide(n);

        assertThat(baos.toString(), is(equalTo(expected)));
    }

    @Test
    public void testLargeCaseFinishes() throws IOException {
        int n = 20000;
        String input = "";
        for (int i = 1; i < 20000; i++) {
            input += i + " " + (i + 1) + "\n";
        }
        int q = 19999;
        input += q + "\n";
        for (int i = 1; i < 20000; i++) {
            input += "q " + i + " " + (i + 1) + "\n";
        }

        // Set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set stdout
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Solution.landslide(n);
    }
}
