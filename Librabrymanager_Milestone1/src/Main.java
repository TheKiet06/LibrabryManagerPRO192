import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = new User("kiet", "123");

        ArrayList<Sach> danhSachSach = new ArrayList<>();
        BorrowManager manager = new BorrowManager();
        Report report = new Report();

        int choice;
        int chon;
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Login");
            System.out.println("2. Logout");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            chon = Integer.parseInt(sc.nextLine());
            try {
                switch (chon) {
                    case 1:
                        System.out.print("Enter username: ");
                        String name = sc.nextLine();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        if (name.equals(user.userName) && password.equals(user.password)) {
                            do {
                                System.out.println("\n===== Library Menu =====");
                                System.out.println("1. Add new book");
                                System.out.println("2. Show all books");
                                System.out.println("3. Search book by name");
                                System.out.println("4. Delete book by name");
                                System.out.println("5. Update book information");
                                System.out.println("6. Borrow a book");
                                System.out.println("7. Return a book");
                                System.out.println("8. Search borrow info by student");
                                System.out.println("9. Generate report");
                                System.out.println("10. Exit");
                                System.out.print("Enter your choice: ");
                                choice = sc.nextInt();
                                sc.nextLine();

                                switch (choice) {
                                    case 1:
                                        Sach sachMoi = new Sach();
                                        sachMoi.nhapBook(sc);
                                        danhSachSach.add(sachMoi);
                                        System.out.println("=> Add book success!");
                                        break;

                                    case 2:
                                        if (danhSachSach.isEmpty()) {
                                            System.out.println("=>List of book is empty!!");
                                        } else {
                                            System.out.println("Available books:");
                                            for (Sach x : danhSachSach) {
                                                x.xuatBook();
                                            }
                                        }
                                        break;

                                    case 3:
                                        System.out.print("Input book's name: ");
                                        String tenTim = sc.nextLine();
                                        boolean timThay = false;
                                        for (Sach x : danhSachSach) {
                                            if (x.title.equalsIgnoreCase(tenTim)) {
                                                x.xuatBook();
                                                timThay = true;
                                            }
                                        }
                                        if (!timThay) System.out.println("=> Name book not exist!!");
                                        break;

                                    case 4:
                                        System.out.print("Input book's name to delete: ");
                                        String tenXoa = sc.nextLine();
                                        boolean daXoa = false;
                                        for (int i = danhSachSach.size() - 1; i >= 0; i--) {
                                            if (danhSachSach.get(i).title.equalsIgnoreCase(tenXoa)) {
                                                danhSachSach.remove(i);
                                                daXoa = true;
                                            }
                                        }
                                        if (daXoa) System.out.println("=> Delete Success!");
                                        else System.out.println("=> Can't find to delete!");
                                        break;
                                        case 5:
                                        if (danhSachSach.isEmpty()) {
                                            System.out.println("=> There are no books to update.");
                                        } else {
                                            System.out.println("Enter the book ID to update: ");
                                            int idTim = sc.nextInt();
                                            sc.nextLine(); 
                                            boolean CheckUpdate = false;
                                             for (Sach x : danhSachSach){
                                            if (x.bookID == idTim){
                                                x.updateBook(sc);
                                                CheckUpdate = true;
                                                break;
                                            }
                                        }
                                             if (!CheckUpdate) {
                                                 System.out.println("=> No books with that ID were found: "+ idTim);
                                             }
                                            
                                        }
                                        break;

                                    case 6:
                                        System.out.print("Enter student name: ");
                                        String student = sc.nextLine();
                                        System.out.print("Enter book name: ");
                                        String bookName = sc.nextLine();
                                        System.out.print("Enter borrow date: ");
                                        String borrowDate = sc.nextLine();

                                        for (Sach b : danhSachSach) {
                                            if (b.getBookName().equalsIgnoreCase(bookName) && !b.isBorrowed()) {
                                                b.borrow();
                                                Borrow borrow = new Borrow(student, bookName, borrowDate);
                                                borrow.borrowBook();
                                                manager.addBorrow(borrow);
                                                break;
                                            }
                                        }
                                        break;

                                    case 7:
                                        System.out.print("Enter book name to return: ");
                                        String returnBookName = sc.nextLine();
                                        System.out.print("Enter return date: ");
                                        String returnDate = sc.nextLine();

                                        for (Sach b : danhSachSach) {
                                            if (b.getBookName().equalsIgnoreCase(returnBookName) && b.isBorrowed()) {
                                                b.returnBook();
                                                for (int i = 0; i < manager.count; i++) {
                                                    if (manager.list[i].bookName.equalsIgnoreCase(returnBookName)
                                                            && manager.list[i].returnDate == null) {
                                                        manager.list[i].returnBook(returnDate);
                                                        System.out.println("Book returned successfully!");
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        break;

                                    case 8:
                                        System.out.print("Enter student name to search: ");
                                        String searchName = sc.nextLine();
                                        manager.searchBorrow(searchName);
                                        break;

                                    case 9:
                                        report.generateBorrowReport(danhSachSach);
                                         manager.viewBorrowHistory(name);
                                        break;

                                    case 10:
                                        System.out.println("Exiting...");
                                        break;

                                    default:
                                        System.out.println("Invalid choice! Please try again.");
                                }
                            } while (choice != 10);
                        } else {
                            System.out.println("Wrong username or password!");
                        }
                        break;
                    case 2:
                        System.out.println("Logout success!!");
                        break;
                    default:
                        System.out.println("GoodBye!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }

        } while (chon != 3);

        sc.close();
    }
}
public class LibraryApp {
     public static void main(String[] args) {
        // listMember đóng vai trò là kho lưu trữ thẻ thành viên
        ArrayList<ClassMember> listMember = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- membership card issuance system ---");
            System.out.println("1. Create a new card ");
            System.out.println("2. List of issued cards");
            System.out.println("0. Exit");
            System.out.print("Manager select command: ");
            
            int choice = Integer.parseInt(sc.nextLine()); // Dùng cách này để tránh lỗi trôi lệnh

            if (choice == 1) {
                // CHỨC NĂNG 1: LẬP THẺ
                System.out.println("\n[Enter customer informationg]");
                System.out.print("- FULL NAME: ");
                String name = sc.nextLine();
                System.out.print("- SÐT: ");
                String phone = sc.nextLine();
                System.out.print("- Email: ");
                String email = sc.nextLine();

                // Tạo đối tượng thẻ mới và bỏ vào danh sách
                ClassMember newCard = new ClassMember(name, phone, email);
                listMember.add(newCard);
                
                System.out.println("=> Card has been successfully created for: " + name);

            } else if (choice == 2) {
                // CHỨC NĂNG 2: XUẤT DANH SÁCH
                if (listMember.isEmpty()) {
                    System.out.println("=> No members have been created yet..");
                } else {
                    System.out.println("\n========= LIST OF PERMANENT MEMBERSHIP CARDS =========");
                    for (ClassMember m : listMember) {
                        System.out.println(m);
                    }
                    System.out.println("==================================================");
                    System.out.println("Total number of cards: " + listMember.size());
                }
            } else if (choice == 0) {
                System.out.println("The system is off...");
                break;
            } else {
                System.out.println("Invalid order!");
            }
        }
    }
}
