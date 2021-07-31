package com.wwt.nimbleviewing.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.wwt.nimbleviewing.data.db.AlbumDao
import com.wwt.nimbleviewing.data.db.AlbumDatabase
import com.wwt.nimbleviewing.data.model.Album
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AlbumDaoTest {
    private lateinit var database: AlbumDatabase
    private lateinit var albumDao: AlbumDao
    private val album1=Album(1,13,"ab rerum non rerum consequatur ut ea unde")
    private val album2 =Album(6,67,"ad voluptas nostrum et nihil")
    private val album3 =Album(4,31,"adipisci laborum fuga laboriosam")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java).build()
        albumDao = database.getAlbumDAO()
        albumDao.insertAll(listOf(album1,album2,album3))

    }

    @Test
    fun testAlbumList_Sorted() = runBlocking {
        val albumList = albumDao.getAllAlbum()
        Assert.assertThat(albumList?.size, Matchers.equalTo(3))

        // Ensure album list is sorted by title
        Assert.assertThat(albumList?.get(0), Matchers.equalTo(album1))
        Assert.assertThat(albumList?.get(1), Matchers.equalTo(album2))
        Assert.assertThat(albumList?.get(2), Matchers.equalTo(album3))
    }

    @Test fun testGetAlbum() = runBlocking {
        Assert.assertThat(albumDao.getAllAlbum()?.first(), Matchers.equalTo(album1))
    }

    @After
    fun closeDb() {
        database.close()
    }

}