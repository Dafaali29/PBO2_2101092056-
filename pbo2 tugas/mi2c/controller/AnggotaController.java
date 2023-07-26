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
import dafa2c.dao.Koneksi;
import dafa2c.model.Anggota;
import dafa2c.view.FormAnggota;

/**
 *
 * @author Dafaali
 */
public class AnggotaController {
    
    private FormAnggota formAnggota;
    private Anggota anggota;
    private AnggotaDao dao;
    private Connection con;
    private Koneksi k;
    
    public AnggotaController(FormAnggota formAnggota) {
        try {
            this.formAnggota = formAnggota;
            dao = new AnggotaDaoImpl();
            k = new Koneksi();
            con = k.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm() {
        formAnggota.getTxtKodeanggota().setText("");
        formAnggota.getTxtNamaanggota().setText("");
        formAnggota.getTxtAlamat().setText("");
    }
    
    public void insert() {
        try {
            anggota = new Anggota();
            anggota.setKodeanggota(
                    formAnggota.getTxtKodeanggota().getText());
            anggota.setNamaanggota(
                    formAnggota.getTxtNamaanggota().getText());
            anggota.setAlamat(
                    formAnggota.getTxtAlamat().getText());
            anggota.setJeniskelamin(
                    formAnggota.getCboJeniskelamin()
                    .getSelectedItem().toString());
            dao.insert(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Entri Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update() {
        try {
            anggota = new Anggota();
            anggota.setKodeanggota(formAnggota.getTxtKodeanggota().getText());
            anggota.setNamaanggota(formAnggota.getTxtNamaanggota().getText());
            anggota.setAlamat(formAnggota.getTxtAlamat().getText());
            anggota.setJeniskelamin(
                    formAnggota.getCboJeniskelamin().getSelectedItem().toString());
            dao.update(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Update Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete() {
        try {
            dao.delete(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tabelKlik() {
        try {
            String kode = formAnggota.getTblAnggota()
                    .getValueAt(formAnggota.getTblAnggota().getSelectedRow(), 0)
                    .toString();
            anggota = dao.getAnggota(con, kode);
            formAnggota.getTxtKodeanggota().setText(anggota.getKodeanggota()); 
            formAnggota.getTxtNamaanggota()
                    .setText(anggota.getNamaanggota());
            formAnggota.getTxtAlamat().setText(anggota.getAlamat());
            formAnggota.getCboJeniskelamin()
                    .setSelectedItem(anggota.getJeniskelamin());
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari() {
        try {
            String kode = formAnggota.getTxtKodeanggota().getText();
            anggota = dao.getAnggota(con, kode);
            if (anggota != null) {
                formAnggota.getTxtNamaanggota().setText(anggota.getNamaanggota());
                formAnggota.getTxtAlamat().setText(anggota.getAlamat());
                formAnggota.getCboJeniskelamin().setSelectedItem(anggota.getJeniskelamin());
            } else {
                JOptionPane.showMessageDialog(formAnggota, "Data Tidak Ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil() {
        try {
            DefaultTableModel tabel = (DefaultTableModel) formAnggota.getTblAnggota().getModel();
            tabel.setRowCount(0);
            List<Anggota> list = dao.getAllAnggota(con);
            for (Anggota anggota1 : list) {
                Object[] row = {
                    anggota1.getKodeanggota(),
                    anggota1.getNamaanggota(),
                    anggota1.getAlamat(),
                    anggota1.getJeniskelamin()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
