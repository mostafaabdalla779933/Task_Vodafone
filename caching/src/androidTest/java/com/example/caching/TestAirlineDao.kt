package com.example.caching

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.db.AirlLineDao
import com.example.db.RoomDatabase
import com.example.entity.AirLineEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class TestAirlineDao {
    private lateinit var airlineDao: AirlLineDao
    private lateinit var db: RoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, RoomDatabase::class.java).allowMainThreadQueries().build()
        airlineDao = db.getAirLineDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun insertAirlineAndReadItFromTheList() = runBlocking {

        val airLine =  AirLineEntity("2", "1955","egypt",".com","cairo")

        airlineDao.insertAirlLine(airLine)

            assert(airlineDao.getAirlLines().contains(airLine))


    }

    @Test
    fun insertListOfAirlinesAndReadItFromTheList() = runBlocking {

        val airLineList = listOf(AirLineEntity("2", "1955","egypt",".com","cairo"),
            AirLineEntity("3", "1955","egypt",".com","alex"))

        airlineDao.insertAirlLines(airLineList)
       airlineDao.getAirlLines().let{
            assert(it.contains(airLineList[0]))
            assert(it.contains(airLineList[1]))
        }
    }


    @Test
    fun clearTable() = runBlocking {
        airlineDao.clearAirlLines()
        assert(airlineDao.getAirlLines()?.size == 0)
    }

    @Test
    fun testConfilctAddTwoItemWithTheSameId() = runBlocking{
        val airLineList = listOf(AirLineEntity("2", "1955","egypt",".com","cairo"),
            AirLineEntity("2", "1955","egypt",".com","alex"))

        airlineDao.insertAirlLines(airLineList)
        airlineDao.getAirlLines().let{
            assert(!it.contains(airLineList[0]))
            assert(it.contains(airLineList[1]))
        }
    }
}

