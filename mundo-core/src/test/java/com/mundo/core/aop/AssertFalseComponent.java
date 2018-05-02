package com.mundo.core.aop;

import com.mundo.core.annotation.AssertFalse;

/**
 * AssertFalseComponent
 *
 * @author maodh
 * @since 02/05/2018
 */
public class AssertFalseComponent {

    @AssertFalse(message = "End to the world")
    boolean test1() {
        return true;
    }

    @AssertFalse(message = "I love you more than I can say.")
    boolean test2(int id, String name) {
        return false;
    }
}
