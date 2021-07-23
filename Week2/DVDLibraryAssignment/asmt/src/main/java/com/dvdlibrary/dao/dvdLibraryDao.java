package com.dvdlibrary.dao;

import com.dvdlibrary.dto.DVD;
import java.util.List;

public interface dvdLibraryDao {
    public DVD addDVD(DVD dvd) throws dvdLibraryDaoException;
    public DVD removeDVD(String title) throws dvdLibraryDaoException;
    public DVD getDVD(String title) throws dvdLibraryDaoException;
    public DVD modifyDVD(String title, String userEdit, int target) throws dvdLibraryDaoException;
    public List<DVD> getAllDVDs() throws dvdLibraryDaoException;
    public List<DVD> searchByTitle(String search) throws dvdLibraryDaoException;
}
