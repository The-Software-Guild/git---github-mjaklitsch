package com.dvdlibrary.ui;

import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.dvdlibrary.dto.DVD;

public class dvdLibraryView {

    private UserIO io = new UserIOConsoleImpl();
    private final String DELIMITER_CHARACTER = ":";
    private enum ATTRIBUTE {
        TITLE, RELEASE_DATE, DIRECTOR, STUDIO, MPAA_RATING, USER_RATING;
    };

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
        String DVDTitle = getChoiceDelimiterCatcher(ATTRIBUTE.TITLE),
         releaseDate = getChoiceDelimiterCatcher(ATTRIBUTE.RELEASE_DATE),
         director = getChoiceDelimiterCatcher(ATTRIBUTE.DIRECTOR), 
         studio = getChoiceDelimiterCatcher(ATTRIBUTE.STUDIO), 
         MPAArating = getChoiceDelimiterCatcher(ATTRIBUTE.MPAA_RATING), 
         userRating = getChoiceDelimiterCatcher(ATTRIBUTE.USER_RATING);
        
        String[] userSplit = userRating.split(":");
        Integer userScore = Integer.parseInt(userSplit[0]);
        String userNote = userSplit[1];

        
        
        DVD currentDVD = new DVD(DVDTitle, director, releaseDate, studio, MPAArating, 
          new SimpleEntry<Integer,String>(userScore, userNote));
        
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
        
        if(editChoice == 7){
            return null;
        }

        ATTRIBUTE attributeTarget = ATTRIBUTE.values()[editChoice-1];
        String edit = getChoiceDelimiterCatcher(attributeTarget);

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
    public String getChoiceDelimiterCatcher(ATTRIBUTE attribute){
        String input = "";
        String score = "";

        while(input.isEmpty() || input.contains(DELIMITER_CHARACTER)){
            io.print("DVD data cannot be empty and cannot include ':'");
            switch(attribute){
                case TITLE:
                    input = getDVDTitleChoice();
                    break;
                case DIRECTOR:
                    input = getDirectorChoice();
                    break;
                case STUDIO:
                    input = getStudioChoice();
                    break;
                case RELEASE_DATE:
                    input = getReleaseDateChoice();
                    break;
                case MPAA_RATING:
                    input = getMPAARatingChoice();
                    break;
                case USER_RATING:
                    score = Integer.toString(getUserRatingChoice());
                    input = getUserNoteChoice();
                    break;
                default:
                    input = io.readString("Please enter desired value.");
            }

        }

        if (ATTRIBUTE.USER_RATING == attribute){
            input = score+":"+input;
        }

        return input;
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
