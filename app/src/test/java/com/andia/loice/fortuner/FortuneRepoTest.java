package com.andia.loice.fortuner;

import com.andia.loice.fortuner.dagger.scheduler.SchedulerManager;
import com.andia.loice.fortuner.model.data.Fortune;
import com.andia.loice.fortuner.model.db.DataSource;
import com.andia.loice.fortuner.model.db.api.ApiService;
import com.andia.loice.fortuner.model.db.api.FortuneRepo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FortuneRepoTest {

    @InjectMocks
    public FortuneRepo fortuneRepo;
    @Mock
    private ApiService apiService;
    @Mock
    private DataSource dataSource;
    @Mock
    private SchedulerManager schedulerMngrImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testFortuneInstanceCreated_ForFOrtune_withsizeof1() {
        Fortune testFortune = fortuneRepo.constructFortune(Arrays.asList("Good Luck"));
        assertEquals("Anon", testFortune.getWriter());
    }

    @Test
    public void testFortuneInstanceCreated_ForFOrtune_withsizegreaterthan1() {
        Fortune testFortune = fortuneRepo.constructFortune(Arrays.asList("Good Luck", "Your life is full of luck", "2015 Loice"));
        assertEquals("Good Luck Your life is full of luck", testFortune.getFortune());
        assertEquals("2015 Loice", testFortune.getWriter());
    }
}
