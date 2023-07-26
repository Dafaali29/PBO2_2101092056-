-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 05 Jul 2023 pada 08.36
-- Versi Server: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pustaka`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE IF NOT EXISTS `anggota` (
  `kodeanggota` varchar(5) NOT NULL,
  `namaanggota` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `nohp` varchar(15) NOT NULL,
  `jekel` varchar(1) NOT NULL,
  `idprodi` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`kodeanggota`, `namaanggota`, `alamat`, `email`, `nohp`, `jekel`, `idprodi`) VALUES
('A001', 'Tri Lestari', 'palimo residence', '503@gmail.com', '0097970907', 'P', 'A001'),
('A002', 'Irine', 'solok', 'kjkjkj', '097907', 'P', 'A002'),
('A003', 'Melya', 'padang', 'kdhldsldkjs9798', '98098', 'P', 'A001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE IF NOT EXISTS `buku` (
  `kodebuku` varchar(10) NOT NULL,
  `judul` text NOT NULL,
  `pengarang` varchar(30) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `tahun` varchar(4) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`kodebuku`, `judul`, `pengarang`, `penerbit`, `tahun`, `status`) VALUES
('a001', 'buku1', 'dito', 'airlangga', '2010', 1),
('B001', 'Pemrograman Jaringan', 'Ali', 'Erlangga', '2010', 0),
('B002', 'Android Dasar', 'Lisa', 'Erlangga', '2013', 0),
('B003', 'Pemrograman Web HTML5', 'Ika', 'Erlangga', '2015', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE IF NOT EXISTS `peminjaman` (
  `kodeanggota` varchar(10) NOT NULL,
  `kodebuku` varchar(10) NOT NULL,
  `tglpinjam` date NOT NULL,
  `tglkembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`kodeanggota`, `kodebuku`, `tglpinjam`, `tglkembali`) VALUES
('A001', 'B001', '2016-09-20', '2016-09-29'),
('A001', 'B002', '2017-06-13', '2017-06-15'),
('a001', 'b002', '2019-05-12', '2019-05-15'),
('A001', 'B003', '2017-06-13', '2017-06-15'),
('A002', 'B002', '2016-10-04', '2016-10-11'),
('A003', 'B001', '2016-10-01', '2016-10-09');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE IF NOT EXISTS `pengembalian` (
  `kodeanggota` varchar(10) NOT NULL,
  `kodebuku` varchar(10) NOT NULL,
  `tglpinjam` date NOT NULL,
  `tgldikembalikan` date NOT NULL,
  `terlambat` int(11) DEFAULT NULL,
  `denda` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`kodeanggota`, `kodebuku`, `tglpinjam`, `tgldikembalikan`, `terlambat`, `denda`) VALUES
('A001', 'B001', '2016-09-20', '2016-09-30', NULL, NULL),
('A002', 'B002', '2016-10-04', '2016-10-20', NULL, NULL),
('A003', 'B001', '2016-10-01', '2016-10-10', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `programstudi`
--

CREATE TABLE IF NOT EXISTS `programstudi` (
  `id` varchar(5) NOT NULL DEFAULT '',
  `nama` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `programstudi`
--

INSERT INTO `programstudi` (`id`, `nama`) VALUES
('A001', 'Teknik Komputer'),
('A002', 'Manajemen Informatik'),
('A003', 'Rekayasa Perangkat L'),
('A2001', 'Teknik Elektro');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `jenis` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `password`, `jenis`) VALUES
('admin', '12345', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
 ADD PRIMARY KEY (`kodeanggota`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
 ADD PRIMARY KEY (`kodebuku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
 ADD PRIMARY KEY (`kodeanggota`,`kodebuku`,`tglpinjam`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
 ADD PRIMARY KEY (`kodeanggota`,`kodebuku`,`tglpinjam`);

--
-- Indexes for table `programstudi`
--
ALTER TABLE `programstudi`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
