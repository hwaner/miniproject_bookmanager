package org.hwaner.bookmanager.util;

import org.hwaner.bookmanager.book.BookImpl;
import org.hwaner.bookmanager.member.MemberImpl;

import java.util.Arrays;
import java.util.Scanner;

public class StartMenu {

    MemberImpl memberImpl = new MemberImpl();
    BookImpl bookImpl = new BookImpl();
    Scanner sc = new Scanner(System.in);
    boolean bigRun = true;
    boolean smallRun;

    int menuNum;

    public void Run() throws InterruptedException {

        System.out.println(Print.WELCOME);
        while(bigRun) {
            smallRun = true;
            if (memberImpl.logOn() == null) {
                while (smallRun) {
                    System.out.print(Print.MENU_START);
                    menuNum = sc.nextInt();
                    switch (menuNum) {
                        case 1:
                            memberImpl.signIn();
                            smallRun = false; break;
                        case 2:
                            memberImpl.signUp(); break;
                        case 3:
                            System.out.println(Print.EXIT_MENT);
                            for (String num : Arrays.asList("3", "2", "1")) {
                                System.out.println(num);
                                Thread.sleep(1000);
                            }
                            System.out.println(Print.EXIT);
                            smallRun = false;
                            bigRun = false; break;
                        default:
                            System.out.println(Print.INPUTERROR);
                            Thread.sleep(1000);
                    }
                }

            } else if (memberImpl.logOn().getId() == "root") {
                while (smallRun) {
                    System.out.println(Print.MENU_ROOT1);
                    System.out.print(Print.MENU_ROOT2);
                    menuNum = sc.nextInt();
                    switch (menuNum) {
                        case 1:
                            memberImpl.printMemberList(); break;
                        case 2:
                            bookImpl.printBookList(); break;
                        case 3:
                            bookImpl.insertBook(); break;
                        case 4:
                            bookImpl.deleteBook(); break;
                        case 5:
                            memberImpl.singOut();
                            smallRun = false; break;
                        case 6:
                            System.out.println(Print.EXIT_MENT);
                            for (String num : Arrays.asList("3", "2", "1")) {
                                System.out.println(num);
                                Thread.sleep(1000);
                            }
                            System.out.println(Print.EXIT);
                            smallRun = false;
                            bigRun = false; break;
                        case 7:
                            MemberImpl.writeMemberList(); break;
                        default:
                            System.out.println(Print.INPUTERROR);
                            Thread.sleep(1000);
                    }
                }

            } else {
                while (smallRun) {
                    System.out.println(Print.MENU_GENERAL1);
                    System.out.print(Print.MENU_GENERAL2);
                    menuNum = sc.nextInt();
                    switch (menuNum) {
                        case 1:
                            bookImpl.borrowBook(); break;
                        case 2:
                            bookImpl.returnBook(); break;
                        case 3:
                            memberImpl.singOut();
                            smallRun = false; break;
                        case 4:
                            System.out.println(Print.EXIT_MENT);
                            for (String num : Arrays.asList("3", "2", "1")) {
                                System.out.println(num);
                                Thread.sleep(1000);
                            }
                            System.out.println(Print.EXIT);
                            smallRun = false;
                            bigRun = false; break;
                        default:
                            System.out.println(Print.INPUTERROR);
                            Thread.sleep(1000);
                    }
                }
            }
        }
    }
}
