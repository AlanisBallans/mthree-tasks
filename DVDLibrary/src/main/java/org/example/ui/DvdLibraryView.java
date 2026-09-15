package org.example.ui;

import org.example.dto.Dvd;

import java.time.LocalDate;
import java.util.List;

public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add New DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. List DVDs");
        io.print("5. Display DVD info");
        io.print("6. Search DVDs");

        io.print("7. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Dvd getNewDvd() {
        String title = io.readString("Please enter Title");
        String release = io.readString("Please enter Release Date");
        String rating = io.readString("Please enter Rating");
        String director = io.readString("Please enter Director");
        String studio = io.readString("Please enter Studio");
        String userNote = io.readString("Please enter your note");
        Dvd currentDvd = new Dvd(title);

        currentDvd.setReleaseDateFromString(release);
        currentDvd.setRating(rating);
        currentDvd.setDirector(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }

    public String getTitle() {
        return io.readString("Please enter the Title.");
    }

    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddSuccessBanner() {
        io.readString("DVD successfully added. Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== DVD List ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayEditBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displaySearchBanner() {
        io.print("=== Search DVDs ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {

            io.print(dvd.getTitle());
            io.print(dvd.getFormattedReleaseDate());
            io.print(dvd.getRating());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such address.");
        }
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            displayDvd(currentDvd);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayOneDvd(Dvd dvd) {
        displayDvd(dvd);
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("Dvd successfully removed");
        } else {
            io.print("No such address.");
        }
        io.readString("Please hit enter to continue.");
    }




    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public String getChange() {
        String change = io.readString("Please enter your edit.");
        return change;
    }

    public int printEditMenuAndGetSelection() {
        io.print("Attributes to edit:");
        io.print("1. Release date");
        io.print("2. Rating");
        io.print("3. Director");
        io.print("4. Studio");
        io.print("5. Note");

        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
}
