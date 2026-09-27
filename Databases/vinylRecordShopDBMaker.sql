DROP DATABASE IF EXISTS vinylrecordshop;
CREATE DATABASE vinylrecordshop;
USE vinylrecordshop;

DROP TABLE IF EXISTS album;
CREATE TABLE album (
	albumID INT AUTO_INCREMENT,
    albumTitle VARCHAR(100) NOT NULL,
	label VARCHAR(50),
    releaseDate DATE,
    price DECIMAL(5,2),
    CONSTRAINT pk_album
		PRIMARY KEY (albumID)
);



DROP TABLE IF EXISTS artist;
CREATE TABLE artist (
	artistID INT AUTO_INCREMENT,
    artistFirstName VARCHAR(25),
    artistLastName VARCHAR(50) NOT NULL,
    CONSTRAINT pk_artist
		PRIMARY KEY (artistID)
);

DROP TABLE IF EXISTS band;
CREATE TABLE band (
	bandID INT AUTO_INCREMENT,
    bandName VARCHAR(50),
    CONSTRAINT pk_band
		PRIMARY KEY (bandID)
);

DROP TABLE IF EXISTS song;
CREATE TABLE song (
	songID INT NOT NULL AUTO_INCREMENT,
    songTitle VARCHAR(100) NOT NULL,
    videoURL VARCHAR(100),
    bandID INT NOT NULL,
    CONSTRAINT pk_song
		PRIMARY KEY (songID),
	CONSTRAINT fk_song_band
		FOREIGN KEY (bandID)
		REFERENCES band(bandID)
);

DROP TABLE IF EXISTS songAlbum;
CREATE TABLE songAlbum (
	songID INT,
    albumID INT,
    CONSTRAINT pk_songAlbum
		PRIMARY KEY (songID, albumID),
	CONSTRAINT fk_songAlbum_song
		FOREIGN KEY (songID)
        REFERENCES song(songID),
	CONSTRAINT fk_songAlbum_album
		FOREIGN KEY (albumID)
        REFERENCES album(albumID)
        
);

DROP TABLE IF EXISTS bandArtist;
CREATE TABLE bandArtist (
	bandID INT,
    artistID INT,
    CONSTRAINT pk_bandArtist
		PRIMARY KEY (bandID, artistID),
	CONSTRAINT fk_bandArtist_band
		FOREIGN KEY (bandID)
        REFERENCES band(bandID),
	CONSTRAINT fk_bandArtist_artist
		FOREIGN KEY (artistID)
        REFERENCES artist(artistID)
);



