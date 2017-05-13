package com.jnape.palatable.lambda.adt.hlist;

import com.jnape.palatable.lambda.adt.hlist.HList.HCons;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testsupport.traits.ApplicativeLaws;
import testsupport.traits.BifunctorLaws;
import testsupport.traits.FunctorLaws;
import testsupport.traits.TraversableLaws;

import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(Traits.class)
public class Tuple5Test {

    private Tuple5<Integer, String, Character, Boolean, Long> tuple5;

    @Before
    public void setUp() {
        tuple5 = new Tuple5<>(1, new Tuple4<>("2", new Tuple3<>('3', new Tuple2<>(false, new SingletonHList<>(5L)))));
    }

    @TestTraits({FunctorLaws.class, ApplicativeLaws.class, BifunctorLaws.class, TraversableLaws.class})
    public Tuple5 testSubject() {
        return tuple("one", 2, 3d, 4f, '5');
    }

    @Test
    public void head() {
        assertEquals((Integer) 1, tuple5.head());
    }

    @Test
    public void tail() {
        assertEquals(new Tuple4<>("2", new Tuple3<>('3', new Tuple2<>(false, new SingletonHList<>(5L)))), tuple5.tail());
    }

    @Test
    public void cons() {
        assertEquals(new HCons<>(0, tuple5), tuple5.cons(0));
    }

    @Test
    public void accessors() {
        assertEquals((Integer) 1, tuple5._1());
        assertEquals("2", tuple5._2());
        assertEquals((Character) '3', tuple5._3());
        assertEquals(false, tuple5._4());
        assertEquals((Long) 5L, tuple5._5());
    }

    @Test
    public void randomAccess() {
        Tuple4<String, String, String, String> spiedTail = spy(tuple("second", "third", "fourth", "fifth"));
        Tuple5<String, String, String, String, String> tuple5 = new Tuple5<>("first", spiedTail);

        verify(spiedTail, times(1))._1();
        verify(spiedTail, times(1))._2();
        verify(spiedTail, times(1))._3();
        verify(spiedTail, times(1))._4();
        tuple5._1();
        tuple5._2();
        tuple5._3();
        tuple5._4();
        tuple5._5();
        verifyNoMoreInteractions(spiedTail);
    }

    @Test
    public void fill() {
        assertEquals(tuple("foo", "foo", "foo", "foo", "foo"), Tuple5.fill("foo"));
    }
}