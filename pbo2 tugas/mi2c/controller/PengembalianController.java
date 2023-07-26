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
import javax.swing.table.DefaultTableModel;
import dafa2c.dao.AnggotaDao;
import dafa2c.dao.AnggotaDaoImpl;
import dafa2c.dao.BukuDao;
import dafa2c.dao.BukuDaoImpl;
import dafa2c.dao.Koneksi;
import dafa2c.dao.PeminjamanDao;
import dafa2c.dao.PeminjamanDaoImpl;
import dafa2c.dao.PengembalianDao;
import dafa2c.dao.PengembalianDaoImpl;
import dafa2c.model.Anggota;
import dafa2c.model.Peminjaman;
import dafa2c.model.Pengembalian;
import dafa2c.view.FormPengembalian;

/**
 *
 * @author Dafaali
 */
public class PengembalianController {
    FormPengembalian formPengembalian;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    PeminjamanDao peminjamanDao;
    PengembalianDao pengembalianDao;
    Pengembalian pengembalian;
    Connection connection;

    public PengembalianController(FormPengembalian formPengembalian) {
        try {
            this.formPengembalian = formPengembalian;
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            peminjamanDao = new PeminjamanDaoImpl();
            pengembalianDao = new PengembalianDaoImpl();
            Koneksi k = new Koneksi();
            connection = k.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void clearForm(){
        formPengembalian.getTxtTglPinjam().setText("");
        formPengembalian.getTxtTglKembali().setText("");
        formPengembalian.getTxtTglDikembalikan().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxtDenda().setText("");
        formPengembalian.getTxtKodeAnggota().setText("");
        formPengembalian.getTxtKodebuku().setText("");
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel)
                    formPengembalian.getTabelPengembalian().getModel();
            tabelModel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAllPengembalian(connection);
            for (Pengembalian p : list) {
                Anggota anggota = anggotaDao.getAnggota(connection, 
                        p.getKodeanggota());
                Peminjaman pinjam = peminjamanDao
                        .getPeminjaman(connection, p.getKodeanggota(), 
                                p.getKodebuku(), p.getTglpinjam());
                Object[] row = {
                    p.getKodeanggota(),
                    anggota.getNamaanggota(),
                    p.getKodebuku(),
                    pinjam.getTglpinjam(),
                    pinjam.getTglkembali(),
                    p.getTgldikembalikan(),
                    p.getTerlambat(),
                    p.getDenda()
                };
                tabelModel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPengembalian(){
        try {
            String kodeAnggota = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 0).toString();
            String kodebuku = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 2).toString();
            String tglpinjam = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 3).toString();
            pengembalian = new Pengembalian();
            Peminjaman peminjaman = peminjamanDao
                    .getPeminjaman(connection, kodeAnggota, kodebuku, tglpinjam);
            int terlambat = pengembalianDao
                    .selisihTanggal(connection, pengembalian.getTgldikembalikan(),
                            peminjaman.getTglkembali());
            pengembalian.setTerlambat(terlambat);
            double denda = pengembalian.getDenda();
            formPengembalian.getTxtKodeAnggota().setText(kodeAnggota);
            formPengembalian.getTxtKodebuku().setText(kodebuku);
            formPengembalian.getTxtTglPinjam().setText(tglpinjam);
            formPengembalian.getTxtTglKembali().setText(peminjaman.getTglkembali()); 
            formPengembalian.getTxtTglDikembalikan().setText(pengembalian.getTgldikembalikan());
            formPengembalian.getTxtTerlambat().setText(terlambat+""); 
            formPengembalian.getTxtDenda().setText(denda+"");
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
