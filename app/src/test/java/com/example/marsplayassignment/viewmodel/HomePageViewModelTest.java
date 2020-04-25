package com.example.marsplayassignment.viewmodel;

import android.net.Uri;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.marsplayassignment.repository.HomePageRepository;

import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
class HomePageViewModelTest {

    HomePageViewModel homePageViewModel;

    @Mock
    HomePageRepository homePageRepository;

    @Mock
    Uri uri;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    @BeforeClass
    public static void before(){
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void init() {
        homePageViewModel = new HomePageViewModel(homePageRepository);
    }

    @Test
    public void testUploadToFirebase() throws Exception{
        homePageViewModel.uploadToFirebase(uri);

        verify(homePageRepository, never()).upload(uri, ArgumentMatchers.any(ObservableEmitter.class));
    }
}
