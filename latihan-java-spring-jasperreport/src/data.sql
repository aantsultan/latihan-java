CREATE DATABASE latihan_jasperreport;

USE latihan_jasperreport;

CREATE Table s_user (
    id BIGINT AUTO_INCREMENT,
    name nvarchar(100) NOT NULL,
    primary key (id)
) ENGINE InnoDB;

select * from s_user;

CREATE Table s_weaving_sizing (
    id BIGINT AUTO_INCREMENT,
    jenis_set_kain nvarchar(100) NOT NULL,
    jenis_kain nvarchar(100) NOT NULL,
    no_benang nvarchar(100) NOT NULL,
    tanggal nvarchar(100) NOT NULL,
    beam_ke integer NOT NULL,
    no_beam nvarchar(100) NOT NULL ,
    jumlah_pcs_per_beam integer NOT NULL,
    primary key (id)
) ENGINE InnoDB;

drop table s_weaving_sizing;

alter table s_weaving_sizing add column bulan_tahun nvarchar(20) null;

update s_weaving_sizing
set bulan_tahun = 'Januari-2023';

select * from s_weaving_sizing;