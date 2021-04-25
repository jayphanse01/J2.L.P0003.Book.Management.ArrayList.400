/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubt.daos;

import java.io.Serializable;
import java.util.ArrayList;
import tubt.dtos.BookDTO;

/**
 *
 * @author buith
 */
public class BookDAO implements Serializable {

    //book store
    public static ArrayList<BookDTO> bookList = new ArrayList<>();

    public static boolean createBook(BookDTO dto) throws Exception {
        try {
            bookList.add(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updateBook(BookDTO dto) throws Exception {
        boolean update = false;
        for (BookDTO bookDTO : bookList) {
            if (bookDTO.getBookID().equals(dto.getBookID())) {
                bookDTO.setBookName(dto.getBookName());
                bookDTO.setAuthor(dto.getAuthor());
                bookDTO.setPublisher(dto.getPublisher());
                bookDTO.setPublishedYear(dto.getPublishedYear());
                bookDTO.setForRent(dto.isForRent());
                update = true;
            }
        }
        return update;
    }

    public static boolean removeBook(String id) throws Exception{
        boolean delete = false;
        for (int i = 0; i < bookList.size(); i++) {
            BookDTO removeBook = bookList.get(i);
            if (removeBook.getBookID().equals(id)) {
                delete = true;
                bookList.remove(removeBook);
            }
        }
        return delete;
    }
    
    public static BookDTO findBookById(String id) throws Exception {
        BookDTO foundBook = new BookDTO();
        for (BookDTO dto : bookList) {
            if(id.equals(dto.getBookID())) {
                foundBook.setBookID(dto.getBookID());
                foundBook.setBookName(dto.getBookName());
                foundBook.setAuthor(dto.getAuthor());
                foundBook.setPublisher(dto.getPublisher());
                foundBook.setPublishedYear(dto.getPublishedYear());
                foundBook.setForRent(dto.isForRent());
            }
        }
        return foundBook;
    }
    
    public static ArrayList<BookDTO> searchBookByName(String name) throws Exception {
        ArrayList<BookDTO> searchBookList = new ArrayList<>();
        for (BookDTO bookDTO : bookList) {
            if(bookDTO.getBookName().contains(name)) {
                searchBookList.add(bookDTO);
            }
        }
        return searchBookList;
    }
    
    
    public static boolean checkExistBook(String id) {
        for (BookDTO bookDTO : BookDAO.bookList) {
            if (bookDTO.getBookID().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
