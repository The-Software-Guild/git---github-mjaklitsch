package com.dvdlibrary;

import com.dvdlibrary.controller.dvdLibraryController;
// import com.dvdlibrary.dto.DVD;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // DVD d = new DVD("title", "director", "releaseDate", "studio", 5, new SimpleEntry<Integer,String>(5,"hi"));
        dvdLibraryController controller = new dvdLibraryController();
        controller.run();
    }
}
