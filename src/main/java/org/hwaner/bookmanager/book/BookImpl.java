package org.hwaner.bookmanager.book;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hwaner.bookmanager.util.Print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class BookImpl implements Book {

    Map<String, BookVO> bookMap = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    @Override
    public void borrowBook() throws InterruptedException {

        System.out.println(Print.BORROWBOOK);

        System.out.print(Print.BOOK_TITLE);
        String bookTitle = sc.nextLine();

        Iterator<String> itr = bookMap.keySet().iterator();

        while (itr.hasNext()) {
            String ptr = itr.next();
            BookVO vo = bookMap.get(ptr);

            if (vo.getBookTitle().startsWith(bookTitle)) {
                if (vo.isBorrowed()) {
                    System.out.println(Print.ISBORROW);
                    break;
                }
                vo.setQty(vo.getQty() - 1);

                System.out.println(Print.COMPLETE);
                Thread.sleep(1500);

                vo.setBorrowed(true);
            }
        }
    }

    @Override
    public void returnBook() {

        System.out.println(Print.RETURNBOOK);

        System.out.println(Print.BOOK_TITLE);
        String bookTitle = sc.nextLine();

        Iterator<String> itr = bookMap.keySet().iterator();

        while (itr.hasNext()) {
            String key = itr.next();
            BookVO value = bookMap.get(key);

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
    public void insertBook() throws InterruptedException {

        System.out.println(Print.INSERTBOOK);

        BookVO vo = new BookVO();
        String idx;

        System.out.print(Print.BOOK_INDEX);
        idx = sc.nextLine();//iterator
        if (bookMap.containsKey(idx)) {
            System.out.println(Print.DUPLICATE);
            Thread.sleep(1000);
            System.out.println(Print.INSERTBOOKQTY);
            Thread.sleep(2000);

            BookVO value = bookMap.get(idx);
            vo.setBookTitle(value.getBookTitle());
            vo.setBookPublisher(value.getBookPublisher());
            vo.setBookAuthor(value.getBookAuthor());
            vo.setQty(value.getQty() + 1);
            bookMap.put(idx, vo);
            return;
        }
        System.out.print(Print.BOOK_TITLE);
        vo.setBookTitle(sc.nextLine());
        System.out.print(Print.BOOK_PUBLISHER);
        vo.setBookPublisher(sc.nextLine());
        System.out.print(Print.BOOK_AUTHOR);
        vo.setBookAuthor(sc.nextLine());

        vo.setQty(vo.getQty() + 1);

        bookMap.put(idx, vo);
        System.out.println(Print.COMPLETE);

    }

    @Override
    public void deleteBook() throws InterruptedException {

        System.out.println(Print.DELETEBOOK);
        System.out.print(Print.BOOK_TITLE);
        String bookTitle = sc.nextLine();

        Iterator<String> itr = bookMap.keySet().iterator();

        while (itr.hasNext()) {
            String key = itr.next();
            BookVO value = bookMap.get(key);
            if (bookTitle.equals(value.getBookTitle())) {
                itr.remove();
                System.out.println(Print.COMPLETE);
                Thread.sleep(1500);
            }
        }
    }

    @Override
    public void printBookList() throws InterruptedException {

        System.out.println(Print.SHOWALLOFBOOK);

        Iterator<String> itr = bookMap.keySet().iterator();

        while (itr.hasNext()) {

            String key = itr.next();
            BookVO value = bookMap.get(key);
            System.out.println("ID: " + key + "\t|" + "Title: " + value.getBookTitle()
                    + "\t|" + "Publisher: " + value.getBookPublisher() + "\t|"
                    + "Author: " + value.getBookAuthor() + "\t|" + "Qty: " + value.getQty());
        }
        System.out.println(Print.COMPLETE);
        Thread.sleep(2000);
    }

    @Override
    public void writeBookList() throws InterruptedException {

        System.out.println(Print.PRINTEXCEL);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;

        // 인덱스 구성
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("Title");
        cell = row.createCell(2);
        cell.setCellValue("Publisher");
        cell = row.createCell(3);
        cell.setCellValue("Author");
        cell = row.createCell(4);
        cell.setCellValue("Qty");

        Iterator<String> itr = bookMap.keySet().iterator();
        int rowIdx = 1;
        while (itr.hasNext()) {

            String key = itr.next();
            BookVO value = bookMap.get(key);

            row = sheet.createRow(rowIdx);

            cell = row.createCell(0);
            cell.setCellValue(key);
            cell = row.createCell(1);
            cell.setCellValue(value.getBookTitle());
            cell = row.createCell(2);
            cell.setCellValue(value.getBookPublisher());
            cell = row.createCell(3);
            cell.setCellValue(value.getBookAuthor());
            cell = row.createCell(4);
            cell.setCellValue(value.getQty());

            rowIdx++;
        }

        // 파일로 저장
        File file = new File("/Users/hwan/Documents/workspace/" +
                "projects/miniprojects/bookmanager/excel/bookList.xls");
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (String num : Arrays.asList("3", "2", "1")) {
            System.out.println(num);
            Thread.sleep(1000);
        }
        System.out.println(Print.COMPLETE);
    }
}
