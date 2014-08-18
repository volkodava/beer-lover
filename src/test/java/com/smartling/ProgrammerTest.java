package com.smartling;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class ProgrammerTest {

    @Test
    public void gotBeerAcceptanceTest() {
        // create a programmer
        Programmer programmer = new Programmer();
        boolean gotBeer;

        // must return true
        gotBeer = programmer.gotBeer(6, 3, 1);
        assertThat(gotBeer, equalTo(true));

        // must return false
        gotBeer = programmer.gotBeer(7, 3, 1);
        assertThat(gotBeer, equalTo(false));

        // must return true
        gotBeer = programmer.gotBeer(6, 3, 2);
        assertThat(gotBeer, equalTo(true));

        // must return true
        gotBeer = programmer.gotBeer(3, 0, 1);
        assertThat(gotBeer, equalTo(true));

        // must return false 
        gotBeer = programmer.gotBeer(1, 0, 1);
        assertThat(gotBeer, equalTo(false));

        // must return true 
        gotBeer = programmer.gotBeer(0, 0, 1);
        assertThat(gotBeer, equalTo(true));
    }
}
