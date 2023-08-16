package com.testapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testapi.payload.BookDTO;
import com.testapi.service.BookService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/books")
public class BookController {
 private BookService bookService;

 public BookController(BookService bookService) {
  this.bookService = bookService;
 }

 @GetMapping
 public List<BookDTO> getAllBook(){
  return  bookService.getAllBooks();
 }

 @PostMapping
 public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
  return new ResponseEntity<BookDTO>(bookService.createBook(bookDTO), HttpStatus.CREATED);
 }

 @GetMapping("/{id}")
 public ResponseEntity<BookDTO> getBookById(@PathVariable(name = "id") long id) {
     return ResponseEntity.ok(bookService.getBookById(id));
 }
 
 @PutMapping("/{id}")
 public ResponseEntity<BookDTO> updateBookById(@RequestBody BookDTO bookDTO, @PathVariable(name = "id") long id) {
     return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteBook(@PathVariable(name = "id") long id){
  bookService.deleteBook(id);
  return  new ResponseEntity<>("Post Entity deleted successfuly" , HttpStatus.OK);
}

}