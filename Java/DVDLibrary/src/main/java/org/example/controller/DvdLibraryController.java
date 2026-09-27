package org.example.controller;

import org.example.dao.DvdLibraryDao;
import org.example.dao.DvdLibraryDaoException;
import org.example.dto.Dvd;
import org.example.ui.DvdLibraryView;

import java.util.List;

public class DvdLibraryController {

    private DvdLibraryDao dao;
    private DvdLibraryView view;

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {


                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        getDvd();
                        break;
                    case 6:
                        searchDvds();
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            dao.saveDvds();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }


        exitMessage();
    }

    private void searchDvds() throws DvdLibraryDaoException {
        view.displaySearchBanner();
        String title = view.getTitle();
        List<Dvd> searchResults = dao.searchDvds(title);
        view.displayDvdList(searchResults);
    }

    private void getDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getTitle();
        Dvd retrievedDvd  = dao.getDvd(title);
        view.displayDvd(retrievedDvd);
    }


    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        Dvd dvdToAdd =  view.getNewDvd();
        dao.addDvd(dvdToAdd);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String lastName = view.getTitle();
        dao.removeDvd(lastName);
    }

    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditBanner();
        String title = view.getTitle();
        int attribute = view.printEditMenuAndGetSelection();
        String change = view.getChange();
        dao.editDvd(title, attribute, change);
        
    }

    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.listDvds();
        view.displayDvdList(dvdList);
    }

}
