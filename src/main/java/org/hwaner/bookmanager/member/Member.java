package org.hwaner.bookmanager.member;

public interface Member {

    public static final String ADMIN_ID = "root";
    public static final String ADMIN_PW = "root";
    public static final String ADMIN_NAME = "관리자";

    public void signUp();
    public void signIn();
    public void singOut() throws InterruptedException;
    public void printMemberList();
    public void writeMemberList();

//    public void searchMember();
//    public void deleteMember();
}
