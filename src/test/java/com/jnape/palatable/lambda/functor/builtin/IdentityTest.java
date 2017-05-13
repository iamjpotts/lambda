package com.jnape.palatable.lambda.functor.builtin;

import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;
import testsupport.traits.ApplicativeLaws;
import testsupport.traits.FunctorLaws;
import testsupport.traits.TraversableLaws;

@RunWith(Traits.class)
public class IdentityTest {

    @TestTraits({FunctorLaws.class, ApplicativeLaws.class, TraversableLaws.class})
    public Identity testSubject() {
        return new Identity<>("");
    }
}