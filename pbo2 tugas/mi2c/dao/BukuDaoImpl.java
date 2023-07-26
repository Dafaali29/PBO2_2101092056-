/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dafa2c.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dafa2c.model.Buku;

/**
 *
 * @author Dafaali
 */
public class BukuDaoImpl implements BukuDao {

    @Override
    public void insert(Connection con, Buku buku) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection con, Buku buku) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection con, Buku buku) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Buku getBuku(Connection con, String kode) throws Exception {
        String sql = "select * from buku where kodebuku = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Buku buku = null;
        if (rs.next()) {
            buku = new Buku();
            buku.setKodebuku(rs.getString(1));
            buku.setJudulbuku(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
        }
        return buku;
    }

    @Override
    public List<Buku> getAllBuku(Connection con) throws Exception {
        String sql = "select * from buku";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Buku> list = new ArrayList<>();
        Buku buku = null;
        while (rs.next()) {
            buku = new Buku();
            buku.setKodebuku(rs.getString(1));
            buku.setJudulbuku(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            list.add(buku);
        }
        return list;
    }
    
}
