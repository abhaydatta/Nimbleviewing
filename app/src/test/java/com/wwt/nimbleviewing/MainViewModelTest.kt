package com.wwt.nimbleviewing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wwt.nimbleviewing.core.models.NBError
import com.wwt.nimbleviewing.data.api.ApiHelper
import com.wwt.nimbleviewing.data.api.ApiService
import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.util.DataState
import com.wwt.nimbleviewing.domain.repository.MainRepository
import com.wwt.nimbleviewing.presentation.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.IllegalStateException

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
class MainViewModelTest {
    private val apiHelper: ApiHelper = mock()

    private lateinit var repository:MainRepository

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var album: Flow<List<Album>?>

    @Mock
    private lateinit var error: MutableLiveData<NBError>

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coRoutineTestRule = CoroutineTestRule()


    private val mockObserverForStates = mock<Observer<DataState<List<Album>>>>()

    private val mockObserverForError = mock<Observer<NBError>>()

    //Use android.arch.core:core-testing:version
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = MainRepository(apiHelper)
        mainViewModel = MainViewModel(repository).apply {
            album.observeForever(mockObserverForStates)
            error.observeForever(mockObserverForError)
        }



    }

    @Test
    fun testAlbum_ShowSuccess(){
        runBlockingTest {
            `when`(repository.getAlbums(true)).thenReturn(album)

            mainViewModel.fetchAlbumList(true)

            verify(mockObserverForStates).onChanged(DataState.Loading)
            verify(mockObserverForStates, times(1)).onChanged(DataState.Success(any()))
            verifyNoMoreInteractions(mockObserverForStates)
        }
    }

    @After
    fun tearDown() {
    }

    private inline fun <reified T> mock(): T = mock(T::class.java)
}