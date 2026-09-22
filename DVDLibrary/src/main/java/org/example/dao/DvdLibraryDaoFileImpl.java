package org.example.dao;

import org.example.dto.Dvd;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private final String DVD_FILE ;
    private static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    public DvdLibraryDaoFileImpl(String fileName) {
        DVD_FILE = fileName;
    }

    public DvdLibraryDaoFileImpl() {
        DVD_FILE = "dvds.txt";
    }


    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException {
        Dvd addedDvd = dvds.put(dvd.getTitle(), dvd);
        return addedDvd;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        Dvd removedDvd = dvds.remove(title);
        return removedDvd;
    }

    @Override
    public Dvd editDvd(String title, int attribute, String change) throws DvdLibraryDaoException {
        Dvd dvdToEdit = dvds.get(title);

        switch (attribute) {
            case 1:
                dvdToEdit.setReleaseDateFromString(change);
                break;
            case 2:
                dvdToEdit.setRating(change);
                break;
            case 3:
                dvdToEdit.setDirector(change);
                break;
            case 4:
                dvdToEdit.setStudio(change);
                break;
            case 5:
                dvdToEdit.setUserNote(change);
                break;
            default:
                throw new DvdLibraryDaoException("Unknown command");
        }

        return dvdToEdit;
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        Dvd dvd = dvds.get(title);
        return dvd;

    }

    @Override
    public List<Dvd> searchDvds(String title) throws DvdLibraryDaoException {
        List<Dvd> searchResults = new ArrayList<Dvd>();
        for (Dvd dvd : dvds.values()) {
            if (dvd.getTitle().contains(title)) searchResults.add(dvd);
        }
        return searchResults;
    }

    @Override
    public List<Dvd> listDvds() throws DvdLibraryDaoException {
        List<Dvd> dvdList = new ArrayList<>(dvds.values());
        return dvdList;
    }


    private Dvd unmarshallDvd(String dvdAsText) {
        String[] dvdArr = dvdAsText.split(DELIMITER);

        Dvd dvd = new Dvd(dvdArr[0]);
        dvd.setReleaseDateFromString(dvdArr[1]);
        dvd.setRating(dvdArr[2]);
        dvd.setDirector(dvdArr[3]);
        dvd.setStudio(dvdArr[4]);
        dvd.setUserNote(dvdArr[5]);

        return dvd;
    }

    private String marshallDvd(Dvd dvd) {
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getRating() + DELIMITER;
        dvdAsText += dvd.getDirector() + DELIMITER;
        dvdAsText += dvd.getStudio()+ DELIMITER;
        dvdAsText += dvd.getUserNote();


        return dvdAsText;
    }

    public void loadDvds() throws DvdLibraryDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("Could not load DVDs into memory.", e);
        }
        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNext()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    @Override
    public void saveDvds() throws DvdLibraryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save dvd data.", e);
        }

        for (Dvd dvd : dvds.values()) {
            String dvdAsText = marshallDvd(dvd);
            out.println(dvdAsText);
            out.flush();
        }

        out.close();
    }
}
