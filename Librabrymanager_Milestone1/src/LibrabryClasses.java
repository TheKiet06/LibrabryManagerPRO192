


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NV
 */
import java.util.ArrayList;
import java.util.Scanner;

class Sach {
    int bookID;
    String title;
    String author;
    String category;

    private boolean isBorrowed;

    public Sach(String title) {
        this.title = title;
        this.isBorrowed = false;
    }

    public Sach() {
        this.isBorrowed = false;
    }

    public void nhapBook(Scanner sc) {
        System.out.println(" Nhap Thong Tin Sach ");
        System.out.print("Nhap ID: ");
        bookID = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ten sach: ");
        title = sc.nextLine();

        System.out.print("Nhap tac gia: ");
        author = sc.nextLine();

        System.out.print("Nhap the loai: ");
        category = sc.nextLine();
    }

    public void xuatBook() {
        System.out.println("[ID: " + bookID + " | Ten: " + title + " | Tac gia: " + author + " | The loai: " + category + "]");
    }

    public String getBookName() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }
}

    class Borrow {

   private String studentName;
   private String bookName;
   private String borrowDate;
   private String returnDate;

    public Borrow(String studentName,
                  String bookName,
                  String borrowDate) {

        this.studentName = studentName;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
    }

    public void borrowBook() {
        System.out.println(studentName +
                " borrowed " +
                bookName);
    }

    public void returnBook(String returnDate) {
        this.returnDate = returnDate;
    }

    public void showInfo() {

        System.out.println("Student: "
                + studentName);

        System.out.println("Book Name: "
                + bookName);

        System.out.println("Borrow Date: "
                + borrowDate);

        if (returnDate == null) {

            System.out.println(
                    "Return Date: Not returned yet");

        } else {

            System.out.println(
                    "Return Date: "
                    + returnDate);
        }
    }

    public String getStudentName() {
        return studentName;
    }
        public String getBookName() {
        return bookName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}
class BorrowManager {

    Borrow[] list = new Borrow[100];
    int count = 0;

    public void addBorrow(Borrow b) {

        list[count] = b;
        count++;
    }

    public void searchBorrow(String studentName) {

        for (int i = 0; i < count; i++) {

            if (list[i].getStudentName()
                    .equalsIgnoreCase(studentName)) {

                list[i].showInfo();
                return;
            }
        }

        System.out.println("Student not found!");
    }
}
class Report {
    public void generateBorrowReport(ArrayList<Sach> danhSachSach) {
        System.out.println("===== Library Borrowing Report =====");
        int borrowedCount = 0;
        int availableCount = 0;
        System.out.println("\nBorrowed Books:");

        for (Sach b : danhSachSach) {
            if (b.isBorrowed()) {
                System.out.println("- " + b.getBookName());
                borrowedCount++;
            }
        }

        System.out.println("\nAvailable Books:");
        for (Sach b : danhSachSach) {
            if (!b.isBorrowed()) {
                System.out.println("- " + b.getBookName());
                availableCount++;
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Total books: " + danhSachSach.size());
        System.out.println("Borrowed books: " + borrowedCount);
        System.out.println("Available books: " + availableCount);
        System.out.println("====================================");
    }
}




