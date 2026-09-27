package org.example.dao;

import org.example.dto.Dvd;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryDaoFileImplTest {

    DvdLibraryDao testDao;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        String testFile = "testDvds.txt";
        new FileWriter(testFile);
        testDao = new DvdLibraryDaoFileImpl(testFile);
    }

    @org.junit.jupiter.api.Test
    void testAddGetDvd() throws DvdLibraryDaoException {
        String title = "The Imitation Game";
        Dvd dvd = new Dvd(title);
        dvd.setReleaseDateFromString("29-08-2014");
        dvd.setRating("PG-13");
        dvd.setDirector("Morten Tyldum");
        dvd.setStudio("Black Bear Pictures");
        dvd.setUserNote("I love Alan Turing");

        Dvd addedDvd = testDao.addDvd(dvd);
        Dvd retrievedDvd = testDao.getDvd(title);
        assertEquals(dvd, retrievedDvd, "Checking The Imitation Game was retrieved");
    }

    @org.junit.jupiter.api.Test
    void testRemoveListDvds() throws DvdLibraryDaoException {
        String title = "The Imitation Game";
        Dvd dvd = new Dvd(title);
        dvd.setReleaseDateFromString("29-08-2014");
        dvd.setRating("PG-13");
        dvd.setDirector("Morten Tyldum");
        dvd.setStudio("Black Bear Pictures");
        dvd.setUserNote("I love Alan Turing");
        testDao.addDvd(dvd);

        title = "Hidden Figures";
        Dvd dvd2 = new Dvd(title);
        dvd2.setReleaseDateFromString("10-12-2016");
        dvd2.setRating("PG");
        dvd2.setDirector("Theodore Melfi");
        dvd2.setStudio("Fox 2000 Pictures");
        dvd2.setUserNote("Extremely important history lesson");
        testDao.addDvd(dvd2);

        Dvd removedDvd = testDao.removeDvd("The Imitation Game");
        assertEquals(dvd, removedDvd, "Checking the DVD was removed");

        assertEquals(1, testDao.listDvds().size(), "Checking the DVD is no longer in the list");
        assertTrue(testDao.listDvds().contains(dvd2));
        assertFalse(testDao.listDvds().contains(dvd));


    }

    @org.junit.jupiter.api.Test
    void searchDvds() throws DvdLibraryDaoException {
        String title = "The Imitation Game";
        Dvd dvd = new Dvd(title);
        dvd.setReleaseDateFromString("29-08-2014");
        dvd.setRating("PG-13");
        dvd.setDirector("Morten Tyldum");
        dvd.setStudio("Black Bear Pictures");
        dvd.setUserNote("I love Alan Turing");
        testDao.addDvd(dvd);

        title = "Hidden Figures";
        Dvd dvd2 = new Dvd(title);
        dvd2.setReleaseDateFromString("10-12-2016");
        dvd2.setRating("PG");
        dvd2.setDirector("Theodore Melfi");
        dvd2.setStudio("Fox 2000 Pictures");
        dvd2.setUserNote("Extremely important history lesson");
        testDao.addDvd(dvd2);

        List<Dvd> searchResults = testDao.searchDvds("The");
        assertTrue(searchResults.contains(dvd));
        assertFalse(searchResults.contains(dvd2));

        searchResults = testDao.searchDvds("Hid");
        assertTrue(searchResults.contains(dvd2));
        assertFalse(searchResults.contains(dvd));

        searchResults = testDao.searchDvds("e");
        assertTrue(searchResults.contains(dvd));
        assertTrue(searchResults.contains(dvd2));

    }
}