import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class InfoPribadi {
    private String nama;
    private int umur;
    private String alamat;
    
    public InfoPribadi(String nama, int umur, String alamat) {
        this.nama = nama;
        this.umur = umur;
        this.alamat = alamat;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getUmur() {
        return umur;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setUmur(int umur) {
        this.umur = umur;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Umur: " + umur);
        System.out.println("Alamat: " + alamat);
    }
    
}

class Karyawan extends InfoPribadi {
    private String posisi;
    private double gaji;
    private int id;

    public Karyawan(String nama, int umur, String alamat, int id, String posisi, double gaji) {
        super(nama, umur, alamat);
        this.id = id;
        this.posisi = posisi;
        this.gaji = gaji;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }
    
    public void setGaji(double gaji, double bonus) {
        this.gaji = gaji + bonus;
    }
    
    public int getId() {
        return id;
    }
    
    public String getPosisi() {
        return posisi;
    }
    
    public double getGaji() {
        return gaji;
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.println("Karyawan ID: " + getId());
        System.out.println("Nama: " + getNama());
        System.out.println("Umur: " + getUmur());
        System.out.println("Alamat: " + getAlamat());
        System.out.println("Posisi: " + posisi);
        System.out.println("Gaji: " + gaji);
    }
}

class ListKaryawan {
    private List<Karyawan> karyawanList = new ArrayList<>();
    private int idCounter = 1;
    
    // Create
    public void tambahKaryawan(Karyawan karyawan) {
        karyawan.setId(idCounter);
        this.idCounter = this.idCounter + 1;
        karyawanList.add(karyawan);
    }
    
    // Read
    public Optional<Karyawan> tampilkanKaryawan(int id) {
        return karyawanList.stream().filter(karyawan -> karyawan.getId() == id).findFirst();
    }
    
    public List<Karyawan> semuaKaryawan() {
        if (karyawanList == null) {
            return null;
        }
        return new ArrayList<>(karyawanList);
    }
    
    // Update
    public boolean updateKaryawan(Karyawan karyawan) {
        Optional<Karyawan> karyawanLama = tampilkanKaryawan(karyawan.getId());
        if (karyawanLama.isPresent()) {
            Karyawan updatedKaryawan = karyawanLama.get();
            updatedKaryawan.setNama(karyawan.getNama());
            updatedKaryawan.setUmur(karyawan.getUmur());
            updatedKaryawan.setAlamat(karyawan.getAlamat());
            updatedKaryawan.setPosisi(karyawan.getPosisi());
            updatedKaryawan.setGaji(karyawan.getGaji());
            return true;
        }
        return false;
    }
    
    // Delete
    public boolean deleteKaryawan(int id) {
        return karyawanList.removeIf(karyawan -> karyawan.getId() == id);
    }
}

public class Main {
    public static void main(String[] args) {
        ListKaryawan listKaryawan = new ListKaryawan();
        
        // CRUD
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=========================");
            System.out.println("Halaman Data Karyawan");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Tampilkan Semua Karyawan");
            System.out.println("3. Tampilkan 1 Karyawan");
            System.out.println("4. Update Data Karyawan");
            System.out.println("5. Hapus Karyawan");
            System.out.println("6. Keluar");
            System.out.print("Pilih: ");
            int pilih = scanner.nextInt();
            System.out.println("=========================");
            
            if (pilih > 0 && pilih <= 6) {
                switch (pilih) {
                    case 1:
                        func_tambahKaryawan(scanner, listKaryawan);
                        break;
                    case 2:
                        func_tampilkanAllKaryawan(listKaryawan);
                        break;
                    case 3:
                        func_tampilkanKaryawan(scanner, listKaryawan);
                        break;
                    case 4:
                        func_updateKaryawan(scanner, listKaryawan);
                        break;
                    case 5:
                        func_deleteKaryawan(scanner, listKaryawan);
                        break;
                    case 6:
                        System.exit(0);
                }
            } else {
                System.out.println("Pilihan Tidak Valid");
            }
        }
    }
    
    public static void func_tambahKaryawan(Scanner scanner, ListKaryawan listKaryawan) {
        // Tambah Karyawan
        System.out.print("Nama Karyawan: ");
        String nama = scanner.next();
        System.out.print("Umur Karyawan: ");
        int umur = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Alamat Karyawan: ");
        String alamat = scanner.nextLine();
        System.out.print("Posisi Karyawan: ");
        String posisi = scanner.nextLine();
        System.out.print("Gaji Karyawan: ");
        double gaji = scanner.nextDouble();
        
        Karyawan karyawanBaru = new Karyawan(nama, umur, alamat, 0, posisi, gaji);
        listKaryawan.tambahKaryawan(karyawanBaru);
    }
    
    public static void func_tampilkanAllKaryawan(ListKaryawan listKaryawan) {
        List<Karyawan> listsKaryawan = listKaryawan.semuaKaryawan();
            
        if (listsKaryawan != null) {
            for (Karyawan karyawan : listsKaryawan) {
                karyawan.tampilkanInfo();
            }
        } else {
            System.out.println("Karyawan Kosong");
        }
    }

    
    public static void func_tampilkanKaryawan(Scanner scanner, ListKaryawan listKaryawan) {
        // Menampilkan Karyawan
        if (listKaryawan == null) {
            System.out.println("Karyawan Kosong");
            return;
        }
        System.out.print("Pilih Karyawan Berdasarkan ID: ");
        int idKaryawan = scanner.nextInt();
        
        Karyawan karyawan = listKaryawan.tampilkanKaryawan(idKaryawan).orElse(null);
        if (karyawan != null) {
            karyawan.tampilkanInfo();
        } else {
            System.out.println("Karyawan Tidak Ditemukan");
        }
    }
    
    public static void func_updateKaryawan(Scanner scanner, ListKaryawan listKaryawan) {
        // Update Data Karyawan
        if (listKaryawan == null) {
            System.out.println("Karyawan Kosong");
            return;
        }
        System.out.print("Pilih Karyawan Berdasarkan ID: ");
        int idKaryawan = scanner.nextInt();
        
        Karyawan karyawan = listKaryawan.tampilkanKaryawan(idKaryawan).orElse(null);
        
        if (karyawan != null) {
            scanner.nextLine();
            System.out.print("Nama Karyawan: ");
            String nama = scanner.nextLine();
            System.out.print("Umur Karyawan: ");
            int umur = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Alamat Karyawan: ");
            String alamat = scanner.nextLine();
            System.out.print("Posisi Karyawan: ");
            String posisi = scanner.nextLine();
            System.out.print("Gaji Karyawan: ");
            double gaji = scanner.nextDouble();
            System.out.print("Bonus Gaji Karyawan: ");
            double bonusgaji = scanner.nextDouble();
            
            karyawan.setNama(nama);
            karyawan.setUmur(umur);
            karyawan.setAlamat(alamat);
            karyawan.setPosisi(posisi);
            karyawan.setGaji(gaji + bonusgaji);
            listKaryawan.updateKaryawan(karyawan);
            System.out.println("Berhasil Di Update");
        } else {
            System.out.println("Karyawan Tidak Ditemukan");
        }
    }
    
    public static void func_deleteKaryawan(Scanner scanner, ListKaryawan listKaryawan) {
        // Menghapus Karyawan
        if (listKaryawan == null) {
            System.out.println("Karyawan Kosong");
            return;
        }
        System.out.print("Pilih Karyawan Berdasarkan ID: ");
        int idKaryawan = scanner.nextInt();
        
        Karyawan karyawan = listKaryawan.tampilkanKaryawan(idKaryawan).orElse(null);
        
        if (karyawan != null) {
            listKaryawan.deleteKaryawan(idKaryawan);
            System.out.println("Berhasil Di Hapus");
        } else {
            System.out.println("Karyawan Tidak Ditemukan");
        }
    }
}
