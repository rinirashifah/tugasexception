// Mendefinisikan package untuk kelas-kelas dalam program
package tugaspboexception;

// Mengimport kelas-kelas yang dibutuhkan dari pustaka Java SQL
import java.sql.*;

// Mendefinisikan kelas DBConnection
class DBConnection {
    // Mendeklarasikan variabel instance url yang merupakan URL JDBC untuk mengidentifikasi dan mengakses database
    private String url;//string yang berisi URL JDBC untuk mengidentifikasi dan mengakses database.
    // Mendeklarasikan variabel instance username yang merupakan username untuk mengakses database
    private String username;
    // Mendeklarasikan variabel instance password yang merupakan password untuk mengakses database
    private String password;

    // Mendefinisikan konstruktor untuk kelas DBConnection
    public DBConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Mendefinisikan metode untuk menampilkan data
    public void displayData() {
        try {
            // Memuat kelas driver JDBC yang diperlukan
            Class.forName("com.mysql.jdbc.Driver");
            // Membuat koneksi ke database menggunakan URL, username, dan password
            Connection connection = DriverManager.getConnection(url, username, password);
            // Membuat objek statement untuk melakukan pernyataan SQL
            Statement statement = connection.createStatement();
            // Menjalankan query untuk mendapatkan data dari tabel "data"
            String query = "SELECT * FROM data";
            ResultSet resultSet = statement.executeQuery(query);

            // Mengiterasi setiap baris hasil query
            while (resultSet.next()) {
                // Mendapatkan nilai dari setiap kolom dalam baris
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");

                // Menampilkan nilai kolom ke layar
                System.out.println("ID: " + id);
                System.out.println("Nama: " + name);
                System.out.println("Umur: " + age);
                System.out.println("Alamat: " + address);
                System.out.println();
            }

            // Menutup objek ResultSet, Statement, dan Connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            // Menangani kesalahan jika kelas driver JDBC tidak ditemukan
            System.out.println("Gagal terhubung ke JDBC driver");
            e.printStackTrace();
        } catch (SQLException e) {
            // Menangani kesalahan jika terjadi kesalahan koneksi database
            System.out.println("Database Connection Error");
            e.printStackTrace();
        }
    }

    // Mendefinisikan metode untuk memasukkan data ke dalam tabel
    public void insertData(int id, String name, int age, String address) {
       try {
    // Mengatur driver JDBC yang akan digunakan
    Class.forName("com.mysql.jdbc.Driver");
    
    // Membuat koneksi ke database menggunakan URL, username, dan password
    Connection connection = DriverManager.getConnection(url, username, password);
    
    // Membuat objek Statement untuk mengeksekusi pernyataan SQL
    Statement statement = connection.createStatement();
    
    // Membuat query untuk memeriksa apakah ID sudah ada dalam tabel data
    String checkQuery = "SELECT * FROM data WHERE id = " + id;
    ResultSet checkResult = statement.executeQuery(checkQuery);
    
    // Memeriksa apakah hasil query menghasilkan baris pertama
    if (checkResult.next()) {
        System.out.println("ID already exists. Cannot insert duplicate");
        return;
    }
    
    // Membuat query untuk memasukkan data baru ke dalam tabel
    String query = "INSERT INTO data (id, name, age, address) VALUES (" + id + ", '" + name + "', " + age + ", '" + address + "')";
    
    // Mengeksekusi pernyataan SQL dan mendapatkan jumlah baris yang terpengaruh
    int rowsAffected = statement.executeUpdate(query);
    
    // Memeriksa apakah data berhasil diinsert
    if (rowsAffected > 0) {
        System.out.println("Data dengan ID= " + id + " berhasil di Insert");
        
        // Menampilkan data terbaru setelah insert
        displayData();
    } else {
        System.out.println("Data gagal di Insert");
    }
    
        // Menutup statement dan koneksi ke database
        statement.close();
        connection.close();
    } catch (ClassNotFoundException e) {
        System.out.println("Gagal terhubung ke JDBC DRIVER");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Database connection error");
        e.printStackTrace();
    }

    }

    // Mendefinisikan metode untuk mengupdate data dalam tabel
    public void updateData(int id, String name, int age, String address) {
        try {
    // Mengatur driver JDBC yang akan digunakan
    Class.forName("com.mysql.jdbc.Driver");
    
    // Membuat koneksi ke database menggunakan URL, username, dan password
    Connection connection = DriverManager.getConnection(url, username, password);
    
    // Membuat objek Statement untuk mengeksekusi pernyataan SQL
    Statement statement = connection.createStatement();
    
    // Membuat query untuk mengupdate data berdasarkan ID
    String query = "UPDATE data SET name = '" + name + "', age = " + age + ", address = '" + address + "' WHERE id = " + id;
    
    // Mengeksekusi pernyataan SQL dan mendapatkan jumlah baris yang terpengaruh
    int rowsAffected = statement.executeUpdate(query);
    
    // Memeriksa apakah data berhasil diupdate
    if (rowsAffected > 0) {
        System.out.println("Data dengan ID= " + id + " berhasil di Update");
        
        // Menampilkan data terbaru setelah update
        displayData();
    } else {
        System.out.println("Data gagal di Update");
    }
    
    // Menutup statement dan koneksi ke database
        statement.close();
        connection.close();
    } catch (ClassNotFoundException e) {
        System.out.println("Gagal terhubung ke JDBC driver");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Database Connection Error");
        e.printStackTrace();
    }

    }

    // Mendefinisikan metode untuk menghapus data dari tabel
    public void deleteData(int id) {
       try {
    // Mengatur driver JDBC yang akan digunakan
    Class.forName("com.mysql.jdbc.Driver");
    
    // Membuat koneksi ke database menggunakan URL, username, dan password
    Connection connection = DriverManager.getConnection(url, username, password);
    
    // Membuat objek Statement untuk mengeksekusi pernyataan SQL
    Statement statement = connection.createStatement();
    
    // Membuat query untuk menghapus data berdasarkan ID
    String query = "DELETE FROM data WHERE id = " + id;
    
    // Mengeksekusi pernyataan SQL dan mendapatkan jumlah baris yang terpengaruh
    int rowsAffected = statement.executeUpdate(query);
    
    // Memeriksa apakah data berhasil dihapus
    if (rowsAffected > 0) {
        System.out.println("Data dengan ID= " + id + " berhasil di delete");
        
        // Menampilkan data terbaru setelah delete
        displayData();
    } else {
        System.out.println("Data gagal di delete");
    }
    
    // Menutup statement dan koneksi ke database
    statement.close();
    connection.close();
    } catch (ClassNotFoundException e) {
        System.out.println("Gagal terhubung ke JDBC");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Database Error");
        e.printStackTrace();
    }
    }
}

// Kelas utama TugasOOP
public class TugasOOP {
    // Metode utama
    public static void main(String[] args) {
        // Mendefinisikan URL, username, dan password untuk mengakses database
        String url = "jdbc:mysql://localhost:3306/tugaspboexception"; // Menggunakan nama database yang benar
        String username = "root";
        String password = "";

        // Membuat objek DBConnection dengan menggunakan URL, username, dan password
        DBConnection dbConnection = new DBConnection(url, username, password);
        // Menampilkan data dari tabel "data"
        dbConnection.displayData();

        // Insert data
        int newId = 1;
        String newName = "Rini";
        int newAge = 19;
        String newAddress = "Pasirkoja";
        dbConnection.insertData(newId, newName, newAge, newAddress);

        // Update data
        int updateId = 1;
        String updateName = "Rini Rashifah";
        int updateAge = 19;
        String updateAddress = "Pasirkoja";
        dbConnection.updateData(updateId, updateName, updateAge, updateAddress);

        // Delete data
        int deleteId = 1;
        dbConnection.deleteData(deleteId);
    }
}