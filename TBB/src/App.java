import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {


        Data customer = new Pelanggan(); // Polymorphism

        // Membuat objek Admin untuk proses login
        Admin pelanggan = new Admin();
        pelanggan.login();

        // Scanner untuk input pengguna
        Scanner scanner = new Scanner(System.in);
        String opsi;
        boolean isLanjutkan = true;

        // Looping untuk menu
        while (isLanjutkan) {
            System.out.println("========== TOKO BUKUKU ===========\n");
            System.out.println("1.\tLihat Data Pelanggan");
            System.out.println("2.\tTambah Data Pelanggan");
            System.out.println("3.\tEdit Data Pelanggan");
            System.out.println("4.\tHapus Data Pelanggan");
            System.out.println("5.\tExit");
            System.out.println("==================================\n");
            System.out.print("\n Pilih: ");
            opsi = scanner.next();

            switch (opsi) {
                case "1":
                    // Menampilkan data pelanggan
                    System.out.println("\n=================");
                    System.out.println("LIHAT DATA PELANGGAN");
                    System.out.println("\n=================");
                    customer.tampilkanData();
                    break;
                case "2":
                    // Menambah data pelanggan
                    System.out.println("\n=======================");
                    System.out.println("TAMBAH DATA PELANGGAN");
                    System.out.println("\n=======================");
                    customer.isiDataPelanggan();
                    customer.tampilkanSemuaData();
                    customer.Struk();
                    customer.tampilkanData();
                    break;
                case "3":
                    // Mengubah data pelanggan
                    System.out.println("\n================");
                    System.out.println("UBAH DATA PELANGGAN");
                    System.out.println("\n================");
                    customer.updateData();
                    customer.tampilkanData();
                    break;
                case "4":
                    // Menghapus data pelanggan
                    System.out.println("\n==============");
                    System.out.println("HAPUS DATA PELANGGAN");
                    System.out.println("\n==============");
                    customer.deleteData();
                    customer.tampilkanData();
                    break;
                case "5":
                    // Keluar dari program
                    System.out.println("\n=========================================");
                    System.out.println("Silakan Login Kembali");
                    System.out.println("===========================================");
                    System.exit(0);
                    break;
                default:
                    System.err.println("\nInput tidak ditemukan\nSilahkan pilih [1-4]");
            }
        }

        // Exception handling
        try {
            // Mengisi data pelanggan
            customer.isiDataPelanggan();
            // Mencetak struk
            customer.Struk();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan input");
        }
    }
}
