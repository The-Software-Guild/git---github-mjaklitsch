package com.dvdlibrary.controller;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import com.dvdlibrary.dao.dvdLibraryDao;
import com.dvdlibrary.dao.dvdLibraryDaoException;
import com.dvdlibrary.dao.dvdLibraryDaoFileImpl;
import com.dvdlibrary.dto.DVD;
import com.dvdlibrary.ui.UserIO;
import com.dvdlibrary.ui.UserIOConsoleImpl;
import com.dvdlibrary.ui.dvdLibraryView;

public class dvdLibraryController {

    private dvdLibraryView view = new dvdLibraryView();
    private dvdLibraryDao dao = new dvdLibraryDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepRunning = true;
        int menuSelection = 0;
        try{
            while(keepRunning){
                menuSelection = view.displayMenuAndGetSelection();
                
                switch (menuSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        displayDVD();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        listDVDs();
                        break;
                    case 6:
                        searchDVDs();
                        break;
                    // case 7:
                    //     loadDVDs();
                    //     break;
                    // case 8:
                    //     saveDVDs();
                    //     break;
                    case 7:
                        keepRunning = false;
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");
                }
                
            }

            io.print("Good Bye!");
        } catch (dvdLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }

    }

    // implemented automatically
    // private void saveDVDs() throws dvdLibraryDaoException{
    //     view.displaySaveDVDs();

    //     view.displaySaveSuccessBanner();
    // }

    // private void loadDVDs() {
    //     view.displayLoadDVDs();

    //     view.displayLoadSuccessBanner();
    // }

    private void searchDVDs() throws dvdLibraryDaoException {
        view.displaySearchBanner();
        String search = view.getDVDSearchTerm();
        List<DVD> results = dao.searchByTitle(search);
        view.displayDVDList(results, "These options match your search:");
        view.displayContinue();
    }

    private void editDVD() throws dvdLibraryDaoException{
        view.displayEditBanner();
        // DVD target = dao.getDVD(title);

        boolean wantsToEdit = true;

        while (wantsToEdit){

            String title = view.getDVDTitleChoice("or '?' to list options.");
            
            if ("?".equals(title)){ // avoid potential null pointer exception
                view.displayDVDList(dao.getAllDVDs());
                continue;
            }

            DVD targetDVD = dao.getDVD(title);
            if(targetDVD == null){
                List<DVD> searchResults = dao.searchByTitle(title);
                
                wantsToEdit = !view.searchForFailedLookup(searchResults).equals("q");

                continue;
            } 
            
            SimpleEntry<Integer,String> edit = view.editDVD(targetDVD);
            if(edit == null){ // user option to quit returns null
                return;
            }
            DVD result = dao.modifyDVD(title, edit.getValue(), edit.getKey());
            view.displayDVD(result, "Here is the modified DVD:");
            wantsToEdit = ("1".equals(io.readString("Type '1' to continue editing or anything else to exit to menu.")));
            
        }
        // view.displayContinue();
    }

    private void displayDVD() throws dvdLibraryDaoException{
        view.displayDisplayBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(title);
        view.displayDVD(dvd, "Here\'s our result for " + title + ":");
        view.displayContinue();
    }

    private void removeDVD() throws dvdLibraryDaoException{
        view.displayRemoveBanner();
        String title = view.getDVDTitleChoice("or 'q' to return to menu.");
        if(!title.equals("q")){
            DVD removed = dao.removeDVD(title);
            if(removed == null){
                io.print("Nothing was found with that name.");
            } else {
                view.displayDVD(removed, "Successfully removed the following:");
            }
            view.displayContinue();
        }
    }

    private void createDVD() throws dvdLibraryDaoException{
        view.displayCreateDVDBanner();
        DVD newDVD = view.createNewDVDFromInput();
        dao.addDVD(newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws dvdLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> DVDList = dao.getAllDVDs();
        view.displayDVDList(DVDList);
        view.displayContinue();
    }

    // Allow the user to add a DVD to the collection
    // Allow the user to remove a DVD from the collection
    // Allow the user to edit the information for an existing DVD in the collection
    // Allow the user to list the DVDs in the collection
    // Allow the user to display the information for a particular DVD
    // Allow the user to search for a DVD by title
    // Load the DVD library from a file
    // Save the DVD library back to the file when the program completes
    // Allow the user to add, edit, or delete many DVDs in one session
}
