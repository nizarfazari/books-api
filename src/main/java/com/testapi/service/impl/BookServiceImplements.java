package com.testapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.testapi.entity.Book;
import com.testapi.exception.ResourceNotFound;
import com.testapi.payload.BookDTO;
import com.testapi.repository.BookRepository;
import com.testapi.service.BookService;


@Service
public class BookServiceImplements implements BookService {

 private BookRepository bookRepository;
 private ModelMapper mapper;

 public BookServiceImplements(BookRepository bookRepository, ModelMapper mapper) {
  this.bookRepository = bookRepository;
  this.mapper = mapper;
 }

 @Override
 public BookDTO createBook( BookDTO bookDTO) {
  Book book = mapToEntity(bookDTO);
  Book newBook = bookRepository.save(book);

  BookDTO bookResponse = mapToDto(newBook);
  return bookResponse;
 }

 @Override
 public void deleteBook(Long bookId) {
  Book book  = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFound("Post" , "id" , bookId));
  bookRepository.delete(book);

 }

 @Override
 public BookDTO getBookById(Long bookId) {
  Book book  = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFound("Post" , "id" , bookId));

  return mapToDto(book);
 }

 @Override
 public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
  Book book  = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFound("Post" , "id" , bookId));

  book.setTitle(bookDTO.getTitle());
  book.setDescription(bookDTO.getDescription());


  Book updatePost = bookRepository.save(book);

  return mapToDto(updatePost);
 }

 private Book mapToEntity(BookDTO bookDTO){
  Book book = mapper.map(bookDTO, Book.class);
  return book;
}


private  BookDTO mapToDto(Book book){
  BookDTO bookDTO = mapper.map(book, BookDTO.class);
  return  bookDTO;
}

@Override
public List<BookDTO> getAllBooks() {
 List<Book> books = bookRepository.findAll();
 return  books.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
}


 
}
