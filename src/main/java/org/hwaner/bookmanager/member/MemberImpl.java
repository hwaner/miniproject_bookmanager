package org.hwaner.bookmanager.member;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hwaner.bookmanager.util.Print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MemberImpl implements Member {

    private ArrayList<MemberVO> memberList = new ArrayList<>();
    private MemberVO memberVO;

    Scanner sc = new Scanner(System.in);

    public MemberImpl() {

        MemberVO vo = new MemberVO();

        vo.setId(Member.ADMIN_ID);
        vo.setPw(Member.ADMIN_PW);
        vo.setName(Member.ADMIN_NAME);
        memberList.add(vo);
    }

    /********** 중복검사 **********/
    private MemberVO crawlMember(String id) {

        MemberVO vo = null;

        for (MemberVO find : memberList)
            if (find.getId().equals(id)) {
                vo = find;
                break;
            }
        return vo;
    }

    /********** 로그온 확인 **********/
    public MemberVO logOn() {

        return memberVO;
    }

    @Override
    public void signUp() {

        System.out.println(Print.SIGNUP);

        MemberVO vo = new MemberVO();

        System.out.print(Print.SIGN_ID);
        vo.setId(sc.nextLine());
        if (crawlMember(vo.getId()) != null) {
            System.out.println(Print.DUPLICATE);
            return;
        }

        System.out.print(Print.SIGN_PW);
        vo.setPw(sc.nextLine());
        System.out.print(Print.SIGN_NAME);
        vo.setName(sc.nextLine());

        memberList.add(vo);
        System.out.println(Print.COMPLETE);
    }

    @Override
    public void signIn() {

        System.out.println(Print.SIGNIN);

        System.out.print(Print.SIGN_ID);
        String id = sc.nextLine();
        System.out.print(Print.SIGN_PW);
        String pw = sc.nextLine();

        MemberVO vo = crawlMember(id);
        if (vo != null) {
            if (vo.getPw().equals(pw)) {
                memberVO = vo;
                System.out.println(Print.COMPLETESIGNIN);
                return;
            }
        }
        System.out.println(Print.NOTCORRESPOND);
    }

    @Override
    public void singOut() throws InterruptedException {

        System.out.println(Print.SIGNOUT_MENT);
        for (String num : Arrays.asList("3", "2", "1")) {
            System.out.println(num);
            Thread.sleep(1000);
        }
        memberVO = null;
        System.out.println(Print.SIGNOUT);
    }

    @Override
    public void printMemberList() throws InterruptedException {

        System.out.println(Print.SHOWALLOFMEMBER);

        for (MemberVO vo : memberList) {
            System.out.println("ID: " + vo.getId() + "\t|"
                    + "Password: "+ vo.getPw() + "\t|" + vo.getName());
        }
        System.out.println(Print.COMPLETE);
        Thread.sleep(2000);
    }

    @Override
    public void writeMemberList() throws InterruptedException {

        System.out.println(Print.PRINTEXCEL);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;

        // 인덱스 구성
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("Password");
        cell = row.createCell(2);
        cell.setCellValue("Name");

        // size만큼 row를 생성하고 입력
        for (MemberVO vo : memberList) {

            for (int rowIdx = 0; rowIdx < memberList.size(); rowIdx++) {

                vo = memberList.get(rowIdx);

                row = sheet.createRow(rowIdx + 1);

                cell = row.createCell(0);
                cell.setCellValue(vo.getId());
                cell = row.createCell(1);
                cell.setCellValue(vo.getPw());
                cell = row.createCell(2);
                cell.setCellValue(vo.getName());
            }
        }

        // 파일로 저장
        File file = new File("/Users/hwan/Documents/workspace/" +
                "projects/miniprojects/bookmanager/excel/memberList.xls");
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
