-- Running this script will DELETE the existing database and all data it contains.
-- Use with caution.

DROP DATABASE IF EXISTS vinylrecordshop;

CREATE DATABASE vinylrecordshop;

USE vinylrecordshop;

CREATE TABLE IF NOT EXISTS album (
    albumId INT(11) NOT NULL AUTO_INCREMENT,
    albumTitle varchar(50) NOT NULL,
    releaseDate date NOT NULL,
    price float NOT NULL,
    label varchar(50) NOT NULL,
    CONSTRAINT pk_album PRIMARY KEY (albumId)
);

CREATE TABLE artist (
    artistId INT(11) NOT NULL AUTO_INCREMENT,
    fname VARCHAR(40) NOT NULL,
    lname varchar(40) NOT NULL,
    isHallOfFame tinyint(1) NOT NULL,
    CONSTRAINT pk_artist PRIMARY KEY (artistId)
);

CREATE TABLE band (
	bandId INT AUTO_INCREMENT,
	bandName VARCHAR(50) NOT NULL,
	CONSTRAINT pk_band PRIMARY KEY (bandId)
);

CREATE TABLE song (
    songId int NOT NULL AUTO_INCREMENT,
    songTitle varchar(100) NOT NULL,
    videoUrl varchar(100),
    bandId int NOT NULL,
    CONSTRAINT pk_song 
    	PRIMARY KEY (songId),
    CONSTRAINT fk_song_band 
    	FOREIGN KEY (bandID)
    	REFERENCES band(bandId)
);

CREATE TABLE songAlbum (
    songId int,
    albumId int,
    CONSTRAINT pk_songAlbum 
    	PRIMARY KEY (songId, albumId),
    CONSTRAINT fk_songAlbum_song
    	FOREIGN KEY (songId)
    	REFERENCES song(songId),
    CONSTRAINT fk_songAlbum_album
    	FOREIGN KEY (albumId)
    	REFERENCES album(albumId)
);

CREATE TABLE bandArtist (
	bandId INT,
	artistId INT,
	CONSTRAINT pk_bandArtist 
        PRIMARY KEY (bandId, artistId),
	CONSTRAINT fk_bandArtist_band 
        FOREIGN KEY (bandId)
		REFERENCES band(bandId),
	CONSTRAINT fk_bandArtist_artist 
        FOREIGN KEY (artistId)
		REFERENCES artist (artistId)
);