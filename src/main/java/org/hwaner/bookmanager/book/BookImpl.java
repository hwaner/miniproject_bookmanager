package org.hwaner.bookmanager.book;

import org.hwaner.bookmanager.util.Print;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class BookImpl implements Book {

    Scanner sc = new Scanner(System.in);

    Map<String, BookVO> map = new HashMap<>();

    @Override
    public void borrowBook() {

        System.out.println(Print.BORROWBOOK);

        System.out.println(Print.BOOK_TITLE);
        String bookTitle = sc.nextLine();

        Iterator<String> itr = map.keySet().iterator();

        while (itr.hasNext()) {
            String ptr = itr.next();
            BookVO vo = map.get(ptr);

            if (vo.getBookTitle().startsWith(bookTitle)) {
                if (vo.isBorrowed()) {
                    System.out.println(Print.ISBORROW);
                    break;
                }
                vo.setQty(vo.getQty() - 1);

                System.out.println(ptr + "\t");
                System.out.println(vo.getBookTitle() + "|");

                vo.setBorrowed(true);
            }
        }
    }

    @Override
    public void returnBook() {

        System.out.println(Print.RETURNBOOK);

        System.out.println(Print.BOOK_TITLE);
        String bookTitle = sc.nextLine();

        Iterator<String> itr = map.keySet().iterator();

        while (itr.hasNext()) {
            String key = itr.next();
            BookVO value = map.get(key);

            if (bookTitle.equals(value.getBookTitle())) {
                if (value.isBorrowed()) {
                    System.out.println(Print.COMPLETE);
                    value.setQty(value.getQty() + 1);
                    value.setBorrowed(false);
                } else
                    System.out.println(Print.NOTCORRESPOND);
            }
        }
    }

    @Override
    public void insertBook() {

        System.out.println(Print.INSERTBOOK);

        BookVO vo = new BookVO();
        String idx;

        System.out.print(Print.BOOK_INDEX);
        idx = sc.nextLine();
        if (map.containsKey(idx)) {
            System.out.println(Print.DUPLICATE);
            System.out.println(Print.INSERTBOOKQTY);
            BookVO value = map.get(idx);
            vo.setBookTitle(value.getBookTitle());
            vo.setBookPublisher(value.getBookPublisher());
            vo.setBookAuthor(value.getBookAuthor());
            vo.setQty(value.getQty() + 1);
            map.put(idx, vo);
            return;
        }
        System.out.print(Print.BOOK_TITLE);
        vo.setBookTitle(sc.nextLine());
        System.out.print(Print.BOOK_PUBLISHER);
        vo.setBookPublisher(sc.nextLine());
        System.out.print(Print.BOOK_AUTHOR);
        vo.setBookAuthor(sc.nextLine());

        vo.setQty(vo.getQty() + 1);

        map.put(idx, vo);
        System.out.println(Print.COMPLETE);

    }

    @Override
    public void deleteBook() {

        System.out.println(Print.DELETEBOOK);
        System.out.print(Print.BOOK_TITLE);
        String bookTitle = sc.nextLine();

        Iterator<String> itr = map.keySet().iterator();

        while (itr.hasNext()) {
            String key = itr.next();
            BookVO value = map.get(key);
            if (bookTitle.equals(value.getBookTitle())) {
                map.remove(key);
                value.setQty(value.getQty() - 1);
                System.out.println(Print.COMPLETE);
            }
        }
    }

    @Override
    public void printBookList() {

        System.out.println(Print.SHOWALLOFBOOK);

        Iterator<String> itr = map.keySet().iterator();

        while (itr.hasNext()) {
            String key = itr.next();
            BookVO value = map.get(key);
            System.out.println(key + " | " + value.getBookTitle() + " | "
                    + value.getBookPublisher() + " | " + value.getBookAuthor() + " | "
                    + "수량: " + value.getQty());
        }
        System.out.println(Print.COMPLETE);
    }
}
