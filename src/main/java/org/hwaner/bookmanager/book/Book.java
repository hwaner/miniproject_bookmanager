package org.hwaner.bookmanager.book;

public interface Book {

    public void borrowBook() throws InterruptedException;
    public void returnBook();
    public void insertBook() throws InterruptedException;
    public void deleteBook() throws InterruptedException;
    public void printBookList() throws InterruptedException;
    public void writeBookList() throws InterruptedException;
}
