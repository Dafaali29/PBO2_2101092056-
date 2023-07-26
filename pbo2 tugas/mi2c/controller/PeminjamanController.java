/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dafa2c.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dafa2c.dao.AnggotaDao;
import dafa2c.dao.AnggotaDaoImpl;
import dafa2c.dao.BukuDao;
import dafa2c.dao.BukuDaoImpl;
import dafa2c.dao.Koneksi;
import dafa2c.dao.PeminjamanDao;
import dafa2c.dao.PeminjamanDaoImpl;
import dafa2c.model.Anggota;
import dafa2c.model.Buku;
import dafa2c.model.Peminjaman;
import dafa2c.view.FormPeminjaman;

/**
 *
 * @author Dafaali
 */
public class PeminjamanController {

    FormPeminjaman formPeminjaman;
    Peminjaman peminjaman;
    PeminjamanDao peminjamanDao;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    Connection connection;

    public PeminjamanController(FormPeminjaman formPeminjaman) {
        try {
            this.formPeminjaman = formPeminjaman;
            peminjamanDao = new PeminjamanDaoImpl();
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            Koneksi k = new Koneksi();
            connection = k.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearForm() {
        formPeminjaman.getTxtTglPinjam().setText("");
        formPeminjaman.getTxtTglKembali().setText("");
    }

    public void isiCombo() {
        try {
            formPeminjaman.getCboKodeanggota().removeAllItems();
            formPeminjaman.getCboKodebuku().removeAllItems();
            List<Anggota> anggotaList = anggotaDao.getAllAnggota(connection);
            List<Buku> bukuList = bukuDao.getAllBuku(connection);
            for (Anggota anggota : anggotaList) {
                formPeminjaman.getCboKodeanggota()
                        .addItem(anggota.getKodeanggota() + "-" + anggota.getNamaanggota());
            }
            for (Buku buku : bukuList) {
                formPeminjaman.getCboKodebuku().addItem(buku.getKodebuku());
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert() {
        try {
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(formPeminjaman.getCboKodeanggota()
                    .getSelectedItem().toString().split("-")[0]);
            peminjaman.setKodebuku(formPeminjaman.getCboKodebuku().getSelectedItem().toString());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglKembali().getText());
            peminjamanDao.insert(connection, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        try {
            peminjaman.setKodeanggota(formPeminjaman.getCboKodeanggota()
                    .getSelectedItem().toString().split("-")[0]);
            peminjaman.setKodebuku(formPeminjaman.getCboKodebuku().getSelectedItem().toString());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglKembali().getText());
            peminjamanDao.update(connection, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Update Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        try {
            peminjamanDao.delete(connection, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Delete Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getPeminjaman() {
        try {
            String kodeanggota = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 0)
                    .toString();
            String kodebuku = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 1)
                    .toString();
            String tglpinjam = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 2)
                    .toString();
            peminjaman = peminjamanDao
                    .getPeminjaman(connection, kodeanggota, kodebuku, tglpinjam);
            if (peminjaman != null) {
                Anggota anggota = anggotaDao
                        .getAnggota(connection, peminjaman.getKodeanggota());
                formPeminjaman.getCboKodeanggota()
                        .setSelectedItem(anggota.getKodeanggota() + "-" + anggota.getNamaanggota());
                formPeminjaman.getCboKodebuku().setSelectedItem(peminjaman.getKodebuku());
                formPeminjaman.getTxtTglPinjam().setText(peminjaman.getTglpinjam());
                formPeminjaman.getTxtTglKembali().setText(peminjaman.getTglkembali());
                int terlambat = peminjamanDao.getSelisihTanggal(connection, "2023-01-03", "2023-01-01");
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tampil() {
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPeminjaman.getTabelPeminjaman().getModel();
            tabel.setRowCount(0);
            List<Peminjaman> list = peminjamanDao.getAllPeminjaman(connection);
            for (Peminjaman peminjaman1 : list) {
                Object[] row = {
                    peminjaman1.getKodeanggota(),
                    peminjaman1.getKodebuku(),
                    peminjaman1.getTglpinjam(),
                    peminjaman1.getTglkembali()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
