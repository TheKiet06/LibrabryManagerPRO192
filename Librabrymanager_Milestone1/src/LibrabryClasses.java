
import java.util.ArrayList;
import java.util.List;
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
public void updateBook(Scanner sc) {
        System.out.println("\nWhat do you want to update? 1. Update Title | 2. Update Author | 3. Update Category");
        System.out.print("Enter your choice (1-3): ");
        int luaChon = sc.nextInt();
        sc.nextLine(); 

        switch (luaChon) {
            case 1:
                System.out.print("Enter new book title: ");
                title = sc.nextLine(); 
                System.out.println("=> Book title updated successfully!");
                break;
            case 2:
                System.out.print("Enter new author name: ");
                author = sc.nextLine(); 
                System.out.println("=> Author name updated successfully!");
                break;
            case 3:
                System.out.print("Enter new category: ");
                category = sc.nextLine();
                System.out.println("=> Category updated successfully!");
                break;
            default:
                System.out.println("=> Invalid choice! No information was changed.");
                break;
        }
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
    String bookName;
    private String borrowDate;
    String returnDate;

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

        System.out.println("Member: "
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
    if(count < list.length){
        list[count] = b;
        count++;
    }else{
        System.out.println("Borrow list is full");
    }
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
    public void showAllBorrow(){
    for(int i=0; i<count;i++){
        list[i].showInfo();
        System.out.println("---------");
    }
}

    public int getCount() {
        return count;
    }
    public void viewBorrowHistory(String studentName){
        boolean found = false;
        System.out.println("\n===== Borrow History");
        for(int i=0; i<count ;i++){
            if(list[i].getStudentName()
                    .equalsIgnoreCase(studentName)){
                list[i].showInfo();
                System.out.println("---------");
                found = true;
            }
        }
        if(!found){
            System.out.println("Not borrowing history found!");
        }
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
    public void generateTopBorrowerReport(List<Member> members) {
    System.out.println("===== Top Borrower Report =====");

    Member topBorrower = null;
    int maxBorrowed = 0;

    for (Member m : members) {
        if (m.getBorrowCount() > maxBorrowed) {
            maxBorrowed = m.getBorrowCount();
            topBorrower = m;
        }
    }

    if (topBorrower != null && maxBorrowed > 0) {
        System.out.println("Member with most borrowed books: " + topBorrower.getName());
        System.out.println("Number of books borrowed: " + maxBorrowed);
    } else {
        System.out.println("No books borrowed yet.");
    }

    System.out.println("================================");
    }
}
class Member {
    private String name;
    private String phone;
    private String email;
    private int borrowCount;

    public Member(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.borrowCount = 0;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public int getBorrowCount() { return borrowCount; }

    public void increaseBorrowCount() { borrowCount++; }
    public void decreaseBorrowCount() {
        if (borrowCount > 0) borrowCount--;
    }

    @Override
    public String toString() {
        return String.format("| NAME: %-15s | PHONE NUMBER: %-12s | EMAIL: %-20s | BORROW: %d quyen |",
                name, phone, email, borrowCount);
    }
}




