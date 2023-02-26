USE [master]
GO

/****** Object:  Database [Lab_0]    Script Date: 25/02/2023 05:12:42 p. m. ******/
CREATE DATABASE [Lab_0]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'Lab_0', FILENAME = N'/var/opt/mssql/data/Lab_0.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON
( NAME = N'Lab_0_log', FILENAME = N'/var/opt/mssql/data/Lab_0_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Lab_0].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [Lab_0] SET ANSI_NULL_DEFAULT OFF
GO

ALTER DATABASE [Lab_0] SET ANSI_NULLS OFF
GO

ALTER DATABASE [Lab_0] SET ANSI_PADDING OFF
GO

ALTER DATABASE [Lab_0] SET ANSI_WARNINGS OFF
GO

ALTER DATABASE [Lab_0] SET ARITHABORT OFF
GO

ALTER DATABASE [Lab_0] SET AUTO_CLOSE OFF
GO

ALTER DATABASE [Lab_0] SET AUTO_SHRINK OFF
GO

ALTER DATABASE [Lab_0] SET AUTO_UPDATE_STATISTICS ON
GO

ALTER DATABASE [Lab_0] SET CURSOR_CLOSE_ON_COMMIT OFF
GO

ALTER DATABASE [Lab_0] SET CURSOR_DEFAULT  GLOBAL
GO

ALTER DATABASE [Lab_0] SET CONCAT_NULL_YIELDS_NULL OFF
GO

ALTER DATABASE [Lab_0] SET NUMERIC_ROUNDABORT OFF
GO

ALTER DATABASE [Lab_0] SET QUOTED_IDENTIFIER OFF
GO

ALTER DATABASE [Lab_0] SET RECURSIVE_TRIGGERS OFF
GO

ALTER DATABASE [Lab_0] SET  DISABLE_BROKER
GO

ALTER DATABASE [Lab_0] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO

ALTER DATABASE [Lab_0] SET DATE_CORRELATION_OPTIMIZATION OFF
GO

ALTER DATABASE [Lab_0] SET TRUSTWORTHY OFF
GO

ALTER DATABASE [Lab_0] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO

ALTER DATABASE [Lab_0] SET PARAMETERIZATION SIMPLE
GO

ALTER DATABASE [Lab_0] SET READ_COMMITTED_SNAPSHOT OFF
GO

ALTER DATABASE [Lab_0] SET HONOR_BROKER_PRIORITY OFF
GO

ALTER DATABASE [Lab_0] SET RECOVERY FULL
GO

ALTER DATABASE [Lab_0] SET  MULTI_USER
GO

ALTER DATABASE [Lab_0] SET PAGE_VERIFY CHECKSUM
GO

ALTER DATABASE [Lab_0] SET DB_CHAINING OFF
GO

ALTER DATABASE [Lab_0] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO

ALTER DATABASE [Lab_0] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO

ALTER DATABASE [Lab_0] SET DELAYED_DURABILITY = DISABLED
GO

ALTER DATABASE [Lab_0] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO

ALTER DATABASE [Lab_0] SET QUERY_STORE = ON
GO

ALTER DATABASE [Lab_0] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO

ALTER DATABASE [Lab_0] SET  READ_WRITE
GO

USE [Lab_0]
GO

/*==============================================================*/
/* Table: MUNICIPIO                                             */
/*==============================================================*/
create table MUNICIPIO (
   id_mun               int                  not null,
   nombre               varchar(45)          not null,
   area                 decimal(18,2)        null,
   presupesto           int                  null,
   constraint PK_MUNICIPIO primary key (id_mun)
)
go

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA (
   di                   int                  not null,
   CdF_di               int                  null,
   id_viv               int                  null,
   id_mun               int                  null,
   nombre               varchar(45)          not null,
   telefono             int                  null,
   edad                 int                  not null,
   sexo                 varchar(45)          null,
   constraint PK_PERSONA primary key (di)
)
go

/*==============================================================*/
/* Index: CDF_FK                                                */
/*==============================================================*/




create nonclustered index CDF_FK on PERSONA (CdF_di ASC)
go

/*==============================================================*/
/* Index: HABITA_FK                                             */
/*==============================================================*/




create nonclustered index HABITA_FK on PERSONA (id_viv ASC)
go

/*==============================================================*/
/* Index: GOBIERNA_FK                                           */
/*==============================================================*/




create nonclustered index GOBIERNA_FK on PERSONA (id_mun ASC)
go

/*==============================================================*/
/* Table: PROPIEDAD                                             */
/*==============================================================*/
create table PROPIEDAD (
   id_viv               int                  not null,
   di                   int                  not null,
   constraint PK_PROPIEDAD primary key (id_viv, di)
)
go

/*==============================================================*/
/* Index: POSEE_FK                                             */
/*==============================================================*/




create nonclustered index POSEE_FK on PROPIEDAD (di ASC)
go

/*==============================================================*/
/* Index: PERTENECE_FK                                          */
/*==============================================================*/




create nonclustered index PERTENECE_FK on PROPIEDAD (id_viv ASC)
go

/*==============================================================*/
/* Table: VIVIENDA                                              */
/*==============================================================*/
create table VIVIENDA (
   id_viv               int                  not null,
   id_mun               int                  not null,
   direccion            varchar(45)          not null,
   capacidad            int                  not null,
   niveles              int                  not null,
   constraint PK_VIVIENDA primary key (id_viv)
)
go

/*==============================================================*/
/* Index: UBICADOEN_FK                                          */
/*==============================================================*/




create nonclustered index UBICADOEN_FK on VIVIENDA (id_mun ASC)
go

alter table PERSONA
   add constraint FK_PERSONA_CDF_PERSONA foreign key (CdF_di)
      references PERSONA (di)
go

alter table PERSONA
   add constraint FK_PERSONA_GOBIERNA_MUNICIPIO foreign key (id_mun)
      references MUNICIPIO (id_mun)
go

alter table PERSONA
   add constraint FK_PERSONA_HABITA_VIVIENDA foreign key (id_viv)
      references VIVIENDA (id_viv)
go

alter table PROPIEDAD
   add constraint FK_VIVIENDA_PERTENECE_PROPIEDAD foreign key (id_viv)
      references VIVIENDA (id_viv)
go

alter table PROPIEDAD
   add constraint FK_PERSONA_POSEE_PROPIEDAD foreign key (di)
      references PERSONA (di)
go

alter table VIVIENDA
   add constraint FK_VIVIENDA_UBICADOEN_MUNICIPIO foreign key (id_mun)
      references MUNICIPIO (id_mun)
go
