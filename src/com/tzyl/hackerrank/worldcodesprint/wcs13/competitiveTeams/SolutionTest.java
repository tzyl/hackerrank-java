package com.tzyl.hackerrank.worldcodesprint.wcs13.competitiveTeams;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SolutionTest {
    @Test
    public void testBasicCase() {
        int n = 3;
        int q = 3;
        String input = "1 2 1\n" + "2 2\n" + "2 1\n";
        String expected = "0\n" + "1\n";

        // Set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set stdout
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Solution.competitiveTeams(n, q);

        assertThat(baos.toString(), is(equalTo(expected)));
    }

    @Test
    public void testMultipleQueries() {
        int n = 7;
        int q = 7;
        String input = "1 1 2\n" + "1 2 3\n" + "1 4 5\n" +"2 0\n" + "2 1\n" + "2 2\n" + "2 3\n";
        String expected = "6\n" + "5\n" + "2\n" + "0\n";

        // Set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set stdout
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Solution.competitiveTeams(n, q);

        assertThat(baos.toString(), is(equalTo(expected)));
    }
}
