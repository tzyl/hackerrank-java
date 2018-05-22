package com.tzyl.hackerrank.algorithms.dynamicprogramming.constructTheArray;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SolutionTest {
    @Test
    public void testBasicCase() {
        assertThat(Solution.countArray(4, 3, 2), is(equalTo(3L)));
    }

    @Test
    public void testLargeCase() {
        assertThat(Solution.countArray(10000, 10000, 2), is(equalTo(259150193L)));
    }
}
