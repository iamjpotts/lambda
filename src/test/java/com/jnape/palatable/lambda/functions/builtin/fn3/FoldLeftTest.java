package com.jnape.palatable.lambda.functions.builtin.fn3;

import com.jnape.palatable.lambda.functions.Fn1;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.Test;
import org.junit.runner.RunWith;
import testsupport.traits.EmptyIterableSupport;

import static com.jnape.palatable.lambda.functions.builtin.fn3.FoldLeft.foldLeft;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static testsupport.functions.ExplainFold.explainFold;

@RunWith(Traits.class)
public class FoldLeftTest {

    @TestTraits({EmptyIterableSupport.class})
    public Fn1<Iterable<Object>, Iterable<Object>> createTestSubject() {
        return foldLeft((objects, o) -> objects, singletonList(new Object()));
    }

    @Test
    public void foldLeftAccumulatesLeftToRight() {
        assertThat(
                foldLeft(explainFold(), "1", asList("2", "3", "4", "5")),
                is("((((1 + 2) + 3) + 4) + 5)")
        );
    }
}
