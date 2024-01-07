import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

// Parameter koneksi database
public class Pelanggan extends Data {
    static final String DB_URL = "jdbc:mysql://localhost:3306/tbpbo";
    static final String USER = "root";
    static final String PASS = "";
    // Variabel statis untuk koneksi database dan result set
    static Connection connect;
    static ResultSet rs;

    static List<Pelanggan> pelangganList = new ArrayList<>();

    // Method to insert data into the database using isiDataPelanggan
    public void isiDataPelanggan() {
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);

        System.out.print("Nomor = ");
        nomor = scan1.next();
        System.out.print("Nama Pelanggan = ");
        nama_Pelanggan = scan1.next();
        System.out.print("No HP = ");
        noHP_Pelanggan = scan1.next();
        System.out.print("Alamat = ");
        alamat_Pelanggan = scan1.next();
        System.out.print("Jenis Buku = ");
        jenis_Buku = scan1.next();
        System.out.print("Judul Buku = ");
        judul_Buku = scan1.next();
        System.out.print("Harga = ");
        harga_Buku = scan2.nextInt();
        System.out.print("Jumlah = ");
        jumlah = scan2.nextInt();

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.nomor = nomor;
        pelanggan.nama_Pelanggan = nama_Pelanggan;
        pelanggan.noHP_Pelanggan = noHP_Pelanggan;
        pelanggan.alamat_Pelanggan = alamat_Pelanggan;
        pelanggan.judul_Buku = judul_Buku;
        pelanggan.jenis_Buku = jenis_Buku;
        pelanggan.harga_Buku = harga_Buku;
        pelanggan.jumlah = jumlah;

        Integer total_Bayar = harga_Buku * jumlah;
        this.total_Bayar = total_Bayar;
        pelanggan.total_Bayar = total_Bayar;
        pelangganList.add(pelanggan);
    }
    public void tampilkanSemuaData() {
        System.out.println("---------------------------------");
        System.out.println("|        Data Toko Bukuku        |");
        System.out.println("---------------------------------");
        Integer total_Bayar = harga_Buku * jumlah;
        this.total_Bayar = total_Bayar;
        for (Pelanggan pelanggan : pelangganList) {
            System.out.println("Nomor: " + pelanggan.nomor);
            System.out.println("Nama: " + pelanggan.nama_Pelanggan);
            System.out.println("No HP: " + pelanggan.noHP_Pelanggan);
            System.out.println("Alamat: " + pelanggan.alamat_Pelanggan);
            System.out.println("Jenis: " + pelanggan.jenis_Buku);
            System.out.println("Judul: " + pelanggan.judul_Buku);
            System.out.println("Harga: " + pelanggan.harga_Buku);
            System.out.println("Jumlah: " + pelanggan.jumlah);
            System.out.println("Total: " + pelanggan.total_Bayar);
            System.out.println("----------------------------------");
        }
    }
     // method untuk mencetak struk
    @Override
    public void Struk() throws Exception {
        Integer total_Bayar = harga_Buku*jumlah;
        this.total_Bayar = total_Bayar;

        // date
        Date date = new Date();
        SimpleDateFormat hari = new SimpleDateFormat("'Hari/Tanggal \t:' EEEEEEEEEE dd-MM-yy");
        SimpleDateFormat jam = new SimpleDateFormat("'Waktu \t\t:' hh:mm:ss z");

        System.out.println("----------- TOKO BUKUKU -----------");
        System.out.println(hari.format(date));
        System.out.println(jam.format(date));
        System.out.println("Nomor \t        : " + nomor);
        System.out.println("====================================");
        System.out.println("---------- DATA PELANGGAN ----------");
        System.out.println("Nama \t        : " + nama_Pelanggan);
        System.out.println("No HP \t\t: " + noHP_Pelanggan);
        System.out.println("Alamat \t\t: " + alamat_Pelanggan);
        System.out.println("------ DATA PEMBELIAN BARANG -------");
        System.out.println("Jenis \t      : " + jenis_Buku);
        System.out.println("Judul \t      : " + judul_Buku);
        System.out.println("Harga \t\t  : " + harga_Buku);
        System.out.println("Jumlah \t\t: " + jumlah);
        System.out.println("Total  \t        : " + this.total_Bayar);
        System.out.println("------------------------------------");
        System.out.println("Nama Kasir\t: Niken Khalilah Hamuti\n");

        // method string
        System.out.println("toUpperCase\t: " + nama_Pelanggan.toUpperCase());
        System.out.println("length\t\t: " + nama_Pelanggan.length());

        // Call the insertDataNew method to insert data into the database
        insertDataNew();
    }

    public void insertDataNew() {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String sql = "INSERT INTO toko (Nomor, Nama_Pelanggan, No_HP, Alamat, Jenis_Buku, Judul, Harga_Buku, Jumlah, Total_Bayar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, nomor);
                    pstmt.setString(2, nama_Pelanggan);
                    pstmt.setString(3, noHP_Pelanggan);
                    pstmt.setString(4, alamat_Pelanggan);
                    pstmt.setString(5, jenis_Buku);
                    pstmt.setString(6, judul_Buku);
                    pstmt.setInt(7, harga_Buku);
                    pstmt.setInt(8, jumlah);
                    pstmt.setInt(9, total_Bayar);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("\nData inserted successfully into 'toko' table!");
                    } else {
                        System.out.println("\nFailed to insert data into 'toko' table.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public void tampilkanData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM toko";
            rs = stmt.executeQuery(sql);

            System.out.println("==============================================================");
            System.out.println("|                    Data Toko Bukuku                         |");
            System.out.println("==============================================================");
            while (rs.next()) {
                String nomor = rs.getString("Nomor");
                String nama_Pelanggan = rs.getString("Nama_Pelanggan");
                String noHP_Pelanggan = rs.getString("No_HP");
                String alamat_Pelanggan = rs.getString("Alamat");
                String judul_Buku = rs.getString("Judul");
                String jenis_Buku = rs.getString("Jenis_Buku");
                String harga_Buku = rs.getString("Harga_Buku");
                String jumlah = rs.getString("Jumlah");
                String total_Bayar = rs.getString("Total_Bayar");

                System.out.println(String.format("%s. %s -- %s -- %s -- %s -- %s -- %s -- %s -- (%s)", nomor, nama_Pelanggan, noHP_Pelanggan, alamat_Pelanggan, judul_Buku, jenis_Buku, harga_Buku, jumlah, total_Bayar));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            Scanner scan1 = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);

            System.out.print("Input Nomor yang akan diubah = ");
            nomor = scan1.next();
            System.out.print("Input Nama = ");
            nama_Pelanggan = scan1.next();
            System.out.print("Input No HP = ");
            noHP_Pelanggan = scan1.next();
            System.out.print("Input Alamat = ");
            alamat_Pelanggan = scan1.next();
            System.out.print("Input Jenis Buku = ");
            judul_Buku = scan1.next();
            System.out.print("Input Judul = ");
            jenis_Buku = scan1.next();
            System.out.print("Input Harga = ");
            harga_Buku = scan2.nextInt();
            System.out.print("Jumlah = ");
            jumlah = scan2.nextInt();

            Integer total_Bayar = harga_Buku * jumlah;
            this.total_Bayar = total_Bayar;

            String sql = "UPDATE toko SET Nama_Pelanggan=?, No_HP=?, Alamat=?, Jenis_Buku=?, Judul=?, Harga_Buku=?, Jumlah=?, Total_Bayar=? WHERE Nomor=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nama_Pelanggan);
                pstmt.setString(2, noHP_Pelanggan);
                pstmt.setString(3, alamat_Pelanggan);
                pstmt.setString(4, jenis_Buku);
                pstmt.setString(5, judul_Buku);
                pstmt.setInt(6, harga_Buku);
                pstmt.setInt(7, jumlah);
                pstmt.setInt(8, total_Bayar);
                pstmt.setString(9, nomor);

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            Scanner scanner3 = new Scanner(System.in);
            // ambil input dari user
            System.out.print("Nomor yang mau dihapus : ");
            String nomor = (scanner3.nextLine());

            // buat query hapus
            String sql = "DELETE FROM toko WHERE Nomor=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nomor);
                pstmt.executeUpdate();
            }

            System.out.println("Data telah terhapus");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  }

