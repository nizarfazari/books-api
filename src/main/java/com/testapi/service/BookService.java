package com.testapi.service;


import java.util.List;

import com.testapi.payload.BookDTO;

public interface BookService {
 BookDTO createBook(BookDTO bookDTO);

 List<BookDTO> getAllBooks();

 BookDTO getBookById(Long bookId);

 BookDTO updateBook(Long bookId, BookDTO BookDTO);

 void deleteBook(Long bookId);
}
