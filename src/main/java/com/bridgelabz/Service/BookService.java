package com.bridgelabz.Service;

import com.bridgelabz.DAOImpl.BookDAOImpl;
import com.bridgelabz.DAO.BookDAO;

public class BookService {
    BookDAO dao=new BookDAOImpl();
    public void addBook()
    {
        dao.addBook();
    }
    public void updateBook()
    {
        dao.updateBook();
    }
    public void deleteBook()
    {
        dao.deleteBook();
    }
    public void getBookDetails()
    {
        dao.getAllBooks();
    }

}
