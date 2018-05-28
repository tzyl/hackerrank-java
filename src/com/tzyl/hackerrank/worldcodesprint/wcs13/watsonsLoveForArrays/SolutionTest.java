package com.tzyl.hackerrank.worldcodesprint.wcs13.watsonsLoveForArrays;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SolutionTest {
    @Test
    public void testBasicCase() {
        int[] A = new int[] { 2, 2, 2, 2, 2 };
        int m = 3;
        int k = 2;
        assertThat(Solution.howManyGoodSubarrays(A, m, k), is(equalTo(9L)));
    }

    @Test
    public void testZeroTarget() {
        int[] A = new int[] { 1, 0, 3, 0, 4 };
        int m = 3;
        int k = 0;
        assertThat(Solution.howManyGoodSubarrays(A, m, k), is(equalTo(13L)));
    }
}
