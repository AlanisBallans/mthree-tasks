package org.example.dao;

import org.example.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {

    Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException;

    Dvd removeDvd(String lastName) throws DvdLibraryDaoException;

    Dvd editDvd(String title, int attribute, String change) throws DvdLibraryDaoException;

    List<Dvd> listDvds() throws DvdLibraryDaoException;

    Dvd getDvd(String lastName) throws DvdLibraryDaoException;

    List<Dvd> searchDvds(String title) throws DvdLibraryDaoException;

    void loadDvds() throws DvdLibraryDaoException;

    void saveDvds() throws DvdLibraryDaoException;

}
