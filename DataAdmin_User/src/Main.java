import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kelas utama
public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        // Membuat admin default
        userManager.createDefaultAdmin();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Registrasi");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userManager.registerUser();
                    break;
                case 2:
                    System.out.println("Program selesai.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

// Kelas dasar untuk User
class User {
    private String username;
    private String password;
    private String creator;

    public User(String username, String password, String creator) {
        this.username = username;
        this.password = password;
        this.creator = creator;
    }

    public String getUsername() {
        return username;
    }

    public String getCreator() {
        return creator;
    }

    public void displayInfo() {
        System.out.println("Username: " + username);
        System.out.println("Dibuat oleh: " + creator);
    }
}

// Subclass untuk Admin
class Admin extends User {
    public Admin(String username, String password, String creator) {
        super(username, password, creator);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Admin");
    }
}

// Subclass untuk Pengguna Biasa
class RegularUser extends User {
    public RegularUser(String username, String password, String creator) {
        super(username, password, creator);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Regular User");
    }
}

// Kelas untuk mengelola operasi pengguna
class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    // Metode untuk membuat admin default
    public void createDefaultAdmin() {
        Admin defaultAdmin = new Admin("admin", "admin123", "System");
        users.add(defaultAdmin);
        System.out.println("Admin default telah dibuat.");
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        System.out.print("Dibuat oleh (Admin/User): ");
        String creator = scanner.nextLine();

        if (creator.equalsIgnoreCase("Admin")) {
            Admin newAdmin = new Admin(username, password, "Admin");
            users.add(newAdmin);
            System.out.println("Admin berhasil didaftarkan.");
            notifyUserCreated(newAdmin);
        } else if (creator.equalsIgnoreCase("User")) {
            RegularUser newUser = new RegularUser(username, password, "User");
            users.add(newUser);
            System.out.println("User biasa berhasil didaftarkan.");
            notifyUserCreated(newUser);
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    // Metode untuk memberi notifikasi setelah pengguna dibuat
    private void notifyUserCreated(User user) {
        System.out.println("Notifikasi: Pengguna " + user.getUsername() + " telah berhasil dibuat oleh " + user.getCreator());
    }
}
