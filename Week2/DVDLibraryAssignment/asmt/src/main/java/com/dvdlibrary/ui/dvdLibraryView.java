package com.dvdlibrary.ui;

import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.dvdlibrary.dto.DVD;

public class dvdLibraryView {

    private UserIO io = new UserIOConsoleImpl();
    private final String DELIMITER = "::";

    // ===== Information Displays =====
    public int displayMenuAndGetSelection() {
        io.print("Main Menu"
            + "\n1. Add DVD to collection"
            + "\n2. Remove DVD from collection"
            + "\n3. Display DVD information"
            + "\n4. Edit DVD information"
            + "\n5. List all DVDs"
            + "\n6. Search DVD by title"
            // + "\n7. Load collection from file"
            // + "\n8. Save collection to file"
            + "\n7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public void displayDVD(DVD dvd, String header){
        io.print(header);
        displayDVD(dvd);
    }

    public void displayDVD(DVD dvd){
        SimpleEntry<Integer,String> userRating = dvd.getUserRating();

        String DVDInfo = String.format("%s, Rated %s, Released %s, A %s film, Directed by %s; User Review: %s %s/5 stars",
            dvd.getTitle(),
            dvd.getMPAArating(), 
            dvd.getReleaseDate(),
            dvd.getStudio(),
            dvd.getDirector(),
            userRating.getValue(),
            Integer.toString(userRating.getKey()));

        io.print(DVDInfo);
    }

    public void displayDVDList(List<DVD> DVDList, String header) {
        io.print(header);
        displayDVDList(DVDList);
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            displayDVD(currentDVD);
        }
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    // ===== End Information Displays =====


    // ===== Data Builders =====
    public DVD createNewDVDFromInput() {
        String DVDTitle, releaseDate, director, studio, MPAArating, userNote;
        // Use do-while loops to remove chance of corrupting
        // file data which is marshalled and unmarshalled 
        // using "::" as a delimiter
        do {
            DVDTitle = getDVDTitleChoice();
        } while(DVDTitle.contains(DELIMITER));

        do {
            releaseDate = getReleaseDateChoice();
        } while(releaseDate.contains(DELIMITER));

        do {
            director = getDirectorChoice();
        } while(director.contains(DELIMITER));

        do {
            studio = getStudioChoice();
        } while(studio.contains(DELIMITER));

        do {
            MPAArating = getMPAARatingChoice();
        } while(MPAArating.contains(DELIMITER));

        do {
            userNote = getUserNoteChoice();
        } while(userNote.contains(DELIMITER));
         

        Integer userRating = getUserRatingChoice();
        
        
        DVD currentDVD = new DVD(DVDTitle, director, releaseDate, studio, MPAArating, 
          new SimpleEntry<Integer,String>(userRating, userNote));
        
        return currentDVD;
    }

    public SimpleEntry<Integer,String> editDVD(DVD targetDVD){
        io.print("Here is the DVD being edited:");
        displayDVD(targetDVD);

        io.print("What do you want to change?"
            + "\n1. Modify title(" + targetDVD.getTitle()
            + ")\n2. Modify release date(" + targetDVD.getReleaseDate()
            + ")\n3. Modify director name(" + targetDVD.getDirector()
            + ")\n4. Modify studio name(" + targetDVD.getStudio()
            + ")\n5. Modify MPAA rating(" + targetDVD.getMPAArating()
            + ")\n6. Modify user rating(" + targetDVD.getUserRating().getValue() 
            + ", " + targetDVD.getUserRating().getKey()
            + ")\n7. Return to Main Menu");

        int editChoice = io.readInt("Please select from the above choices.", 1, 7);
        String edit = "";
        switch(editChoice){
        // Use do-while loops to remove chance of corrupting
        // file data which is marshalled and unmarshalled 
        // using "::" as a delimiter
        case 1: // title
            do {
                if(edit.contains(DELIMITER)){
                    io.print("DVD data cannot include '::'");
                }
                edit = getDVDTitleChoice();
            } while(edit.contains(DELIMITER));
            break;
        case 2: // date
            do {
                if(edit.contains(DELIMITER)){
                    io.print("DVD data cannot include '::'");
                }
                edit = getReleaseDateChoice();
            } while(edit.contains(DELIMITER));
            break;
        case 3: // director
            do {
                if(edit.contains(DELIMITER)){
                    io.print("DVD data cannot include '::'");
                }
                edit = getDirectorChoice();
            } while(edit.contains(DELIMITER));
            break;
        case 4: // studio
            do {
                if(edit.contains(DELIMITER)){
                    io.print("DVD data cannot include '::'");
                }
                edit = getStudioChoice();
            } while(edit.contains(DELIMITER));
            break;
        case 5: // mpaa rating
            do {
                if(edit.contains(DELIMITER)){
                    io.print("DVD data cannot include '::'");
                }
                edit = getMPAARatingChoice();
            } while(edit.contains(DELIMITER));
            break;
        case 6: // user rating
            do {
                if(edit.contains(DELIMITER)){
                    io.print("DVD data cannot include '::' anywhere or ':' at the start of your note");
                }
                String rating = Integer.toString(getUserRatingChoice());
                String note = getUserNoteChoice();
                edit = rating+":"+note;
            } while(edit.contains(DELIMITER));
            break;
        case 7: // calling function must handle null as exit
            return null;
            
        }

        return new SimpleEntry<Integer,String>(editChoice, edit);
    }
    // ===== End Data Builders =====


    // ===== Input getters =====
    public String getDVDSearchTerm(){
        return io.readString("Type part of or all of a title to display matching items");
    }
    public String getReleaseDateChoice(){
        Integer releaseYear = io.readInt("Please enter the year of release.");
        Integer releaseMonth = io.readInt("Please enter the month of release(1-12).",1,12);

        int monthLength;

        if (Arrays.asList(4,6,9,11).contains(releaseMonth)){ 
            monthLength = 30; // 30 days has sep, apr, jun, nov
        } else if (releaseMonth == 2){ // february is special
            if (releaseYear % 4 != 0){ // leap years are divisible by 4
                monthLength = 28; // normally 28 days
            } else {
                monthLength = 29; // but a leap year has 29
            }
        } else { // all the rest have 31
            monthLength = 31;
        }

        Integer releaseDay = io.readInt("Please enter the day of release(1-"+monthLength+").",1,monthLength);
        String releaseDate = releaseMonth + "-" + releaseDay + "-" + releaseYear;
        return releaseDate;
    }
    public String getDVDTitleChoice(){
        return io.readString("Please enter the DVD title.");
    }
    public String getDVDTitleChoice(String addition){
        return io.readString("Please enter the DVD title " + addition);
    }
    public String getDirectorChoice(){
        return io.readString("Please enter the director\'s name.");
    }
    public String getStudioChoice(){
        return io.readString("Please enter the studio\'s name.");
    }
    public String getMPAARatingChoice(){
        return io.readString("Please enter the MPAA rating.");
    }
    public Integer getUserRatingChoice(){
        return io.readInt("Please enter your rating 1-5.", 1, 5);
    }
    public String getUserNoteChoice(){
        return io.readString("Please enter your own note or feedback.");
    }
    // ===== End Input getters =====


    // ===== Prompts for Continuation =====
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue.");
    }
    public void displayContinue() {
        io.readString("Please hit enter to continue.");
    }
    // public void displayLoadSuccessBanner() {
    //     io.readString("Successfully loaded. Please hit enter to continue.");
    // }
    // public void displaySaveSuccessBanner() {
    //     io.readString("Successfully saved. Please hit enter to continue.");
    // }
    public String searchForFailedLookup(List<DVD> searchResults){
        io.print("That title doesn't exist.");
        if(searchResults.size() > 0){
            io.print("Here's a list of similar items:");
            displayDVDList(searchResults);
        }

        return io.readString("Press Enter to try again or 'q' to quit");
    }
    // ===== End of Prompts for Continuation =====


    // ===== Banners =====
    public void displayBanner(String display){
        io.print("=== " + display + " ===");
    }
    public void displayCreateDVDBanner() {
        displayBanner("Create DVD");
    }
    public void displayRemoveBanner() {
        displayBanner("Remove DVD");
    }
    public void displayEditBanner() {
        displayBanner("Edit DVD");
    }
    public void displaySearchBanner() {
        displayBanner("Search For DVD");
    }
    public void displayDisplayBanner() {
        displayBanner("Display DVD");
    }
    public void displayDisplayAllBanner() {
        displayBanner("Display All DVDs");
    }
    // public void displaySaveDVDs() {
    //     displayBanner("Save DVDs");
    // }
    // public void displayLoadDVDs() {
    //     displayBanner("Load DVDs");
    // }
    // ===== End of Banners =====
}
