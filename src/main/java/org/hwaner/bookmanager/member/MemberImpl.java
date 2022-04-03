package org.hwaner.bookmanager.member;

import org.hwaner.bookmanager.util.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MemberImpl implements Member {

    Scanner sc = new Scanner(System.in);

    private ArrayList<MemberVO> memberList = new ArrayList<>();
    private MemberVO memberVO;

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
            Thread.sleep(1000);
            System.out.println(num);
        }
        Thread.sleep(1000);
        memberVO = null;
        System.out.println(Print.SIGNOUT);
    }

    @Override
    public void printMemberList() {

        System.out.println(Print.SHOWALLOFMEMBER);

        for (MemberVO vo : memberList) {
            System.out.print(vo.getId() + " | ");
            System.out.println(vo.getName());
        }
        System.out.println(Print.COMPLETE);
    }
}
