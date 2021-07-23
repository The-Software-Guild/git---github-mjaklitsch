package com.dvdlibrary.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.dvdlibrary.dto.DVD;

public class dvdLibraryDaoFileImpl implements dvdLibraryDao{
    private Map<String,DVD> DVDCollection = new HashMap<String,DVD>();
    
    public static final String DVD_LIBRARY_FILE = "asmt/src/main/java/com/dvdlibrary/dvdlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDVD(DVD dvd) throws dvdLibraryDaoException{
        loadDVDLibrary();
        DVD newDVD = this.DVDCollection.put(dvd.getTitle(), dvd);
        writeDVDLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws dvdLibraryDaoException{
        loadDVDLibrary();
        DVD removedDVD = this.DVDCollection.remove(title);
        writeDVDLibrary();
        return removedDVD;
    }

    @Override
    public DVD getDVD(String title) throws dvdLibraryDaoException{
        loadDVDLibrary();
        return this.DVDCollection.get(title);
    }

    @Override
    public List<DVD> getAllDVDs() throws dvdLibraryDaoException{
        loadDVDLibrary();
        return new ArrayList<DVD>(this.DVDCollection.values());
    }

    @Override
    public List<DVD> searchByTitle(String search) throws dvdLibraryDaoException{
        List<DVD> results = new ArrayList<DVD>();

        for(DVD dvd : getAllDVDs()){
            
            if(dvd.getTitle().toLowerCase().contains(search.toLowerCase())){
                results.add(dvd);
            }
        }

        return results;
    }

    @Override
    public DVD modifyDVD(String title, String userEdit, int target) throws dvdLibraryDaoException{
        DVD dvd = getDVD(title); // loads library file
        switch(target){
        case 1: // title
            // Collection of DVD's is stored as a map from title-dvd
            // on changing the name we need to re-add the dvd and 
            // remove its old entry
            this.DVDCollection.put(userEdit, this.DVDCollection.remove(dvd.getTitle()));
            dvd.setTitle(userEdit);
            break;
        case 2: // date
            dvd.setReleaseDate(userEdit);
            break;
        case 3: // director
            dvd.setDirector(userEdit);
            break;
        case 4: // studio
            dvd.setStudio(userEdit);
            break;
        case 5: // mpaa rating
            dvd.setMPAArating(userEdit);
            break;
        case 6: // user rating
            int RATING = 0, NOTE = 1;
            String[] splitRating = userEdit.split(":");
            dvd.setUserRating(Integer.parseInt(splitRating[RATING]), splitRating[NOTE]);
        }

        writeDVDLibrary();
        return dvd;
    }

    private DVD unmarshalDVDFromString(String dvdString) {
        String[] dvdData = dvdString.split(DELIMITER);

        String title = dvdData[0], director = dvdData[1], 
         releaseDate = dvdData[2], studio = dvdData[3],
         MPAArating = dvdData[4], userReview = dvdData[5];

        final int RATING = 0, NOTE = 1;
        String[] splitRating = userReview.split(":");
        SimpleEntry<Integer,String> userRating = new SimpleEntry<Integer,String>(
            Integer.parseInt(splitRating[RATING]), splitRating[NOTE]);

        return new DVD(title, director, releaseDate, studio, MPAArating, userRating);
    }

    private String marshalDVDToString(DVD dvd){
        // split SimpleEntry and recombine as string
        String userRating = dvd.getUserRating().getKey() 
         + ":" + dvd.getUserRating().getValue();

        List<String> attributes = Arrays.asList(
            dvd.getTitle(), dvd.getDirector(),
            dvd.getReleaseDate(), dvd.getStudio(),
            dvd.getMPAArating(), userRating);

        return String.join(DELIMITER, attributes);
    }

    private void loadDVDLibrary() throws dvdLibraryDaoException {
        Scanner scanner;
    
        try {
            // File f=new File("icecreamTopping.txt");
            // System.out.println(f.getAbsolutePath());
            // Create Scanner for reading the file
            scanner = new Scanner(
                       new BufferedReader(
                        new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new dvdLibraryDaoException(
                    "-_- Could not load dvdlibrary data into memory.", e);
        }

        String currentLine;
        DVD currentDVD;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshalDVDFromString(currentLine);

            this.DVDCollection.put(currentDVD.getTitle(), currentDVD);
        }

        scanner.close();

    }

    private void writeDVDLibrary() throws dvdLibraryDaoException {
        PrintWriter out;
    
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new dvdLibraryDaoException(
                    "Could not save student data.", e);
        }

        String dvdString;
        List<DVD> dvdList = new ArrayList<DVD>(this.DVDCollection.values());

        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String and write to file with newline
            dvdString = marshalDVDToString(currentDVD);
            out.println(dvdString);
            out.flush();
        }

        out.close();
    }
    
    
}
