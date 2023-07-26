/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi2c.model;

/**
 *
 * @author Dafaali
 */
public class Anggota {
    private String kodeanggota;
    private String namaanggota;
    private String alamat;
    private String jeniskelamin;

    public Anggota() {
    }

    public Anggota(String kodeanggota, String namaanggota, String alamat, String jeniskelamin) {
        this.kodeanggota = kodeanggota;
        this.namaanggota = namaanggota;
        this.alamat = alamat;
        this.jeniskelamin = jeniskelamin;
    }
    

    public String getKodeanggota() {
        return kodeanggota;
    }

    public void setKodeanggota(String kodeanggota) {
        this.kodeanggota = kodeanggota;
    }

    public String getNamaanggota() {
        return namaanggota;
    }

    public void setNamaanggota(String namaanggota) {
        this.namaanggota = namaanggota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }
    
    public static void main(String[] args) {
        Anggota ali = new Anggota("A001","Ali","Padang","L");
        ali.setKodeanggota("001");
        ali.kodeanggota="002";
        
        System.out.println("Kode    "+ali.getKodeanggota());
    }
    
}
