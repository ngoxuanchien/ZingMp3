CREATE TABLE `Song` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`title` VARCHAR(255),
	`alias` VARCHAR(255) UNIQUE,
	`isOfficial` BOOLEAN,
	`artistsNames` VARCHAR(255),
	`isWorldWide` BOOLEAN,
	`thumbnail` VARCHAR(255),
	`duration` INT,
	`isPrivate` BOOLEAN,
	`releaseDate` INT,
	`distributor` VARCHAR(255),
	`hasLyric` BOOLEAN,
	`like` INT DEFAULT 0,
	`listen` INT DEFAULT 0,
	`liked` BOOLEAN DEFAULT false,
	`comment` INT DEFAULT 0,
	`streaming` VARCHAR(36),
	`createdDate` DATETIME NOT NULL,
	`createdBy` VARCHAR(36) NOT NULL,
	`lastModified` DATETIME,
	`lastModifiedBy` VARCHAR(36),
	PRIMARY KEY(`id`, `alias`)
);

CREATE TABLE `Song_Artist` (
	`id` VARCHAR(36) NOT NULL,
	`artistId` VARCHAR(36) NOT NULL,
	`songId` VARCHAR(36) NOT NULL UNIQUE,
	PRIMARY KEY(`id`, `artistId`, `songId`)
);

CREATE TABLE `Song_Genre` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`songId` VARCHAR(36) NOT NULL,
	`genreId` VARCHAR(36) NOT NULL,
	PRIMARY KEY(`id`, `songId`, `genreId`)
);

CREATE TABLE `Song_Composer` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`songId` VARCHAR(36) NOT NULL,
	`composerId` VARCHAR(36) NOT NULL,
	PRIMARY KEY(`id`, `songId`, `composerId`)
);

CREATE TABLE `Song_Playlist` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`songId` VARCHAR(36) NOT NULL,
	`playlistId` VARCHAR(36) NOT NULL,
	PRIMARY KEY(`id`, `songId`, `playlistId`)
);

CREATE TABLE `User_Song` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`userId` VARCHAR(36),
	`songId` VARCHAR(36),
	`liked` BOOLEAN DEFAULT false,
	`lastListen` DATETIME,
	PRIMARY KEY(`id`)
);

CREATE TABLE `Streaming` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`url128kps` VARCHAR(255),
	`url320kps` VARCHAR(255),
	PRIMARY KEY(`id`)
);

CREATE TABLE `User` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`email` VARCHAR(255) UNIQUE,
	`password` VARBINARY(255),
	`name` VARCHAR(255),
	PRIMARY KEY(`id`)
);

CREATE TABLE `PlaylistOrAlbum` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`title` VARCHAR(255),
	`thumbnail` VARCHAR(255),
	`isOffical` BOOLEAN,
	`link` VARCHAR(255),
	`releaseDate` VARCHAR(255),
	`sortDescription` VARCHAR(255),
	`releasedAt` INT,
	`artistsNames` VARCHAR(255),
	`isPrivate` BOOLEAN,
	`isAlbum` BOOLEAN,
	`textType` SET("Playlist", "Album"),
	`distributor` VARCHAR(255),
	`description` VARCHAR(1000),
	`aliasTitle` VARCHAR(255) NOT NULL,
	`like` INT,
	`listen` DATETIME,
	`createBy` VARCHAR(36),
	`modifiedDate` DATETIME,
	`modifiedBy` VARCHAR(36),
	`createdDate` DATETIME,
	`createdBy` VARCHAR(36),
	`lastModified` DATETIME,
	`lastModifiedBy` VARCHAR(36),
	PRIMARY KEY(`id`, `aliasTitle`)
);

CREATE TABLE `Playlist_Genre` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`playlistId` VARCHAR(36) NOT NULL,
	`genreId` VARCHAR(36) NOT NULL,
	PRIMARY KEY(`id`, `playlistId`, `genreId`)
);

CREATE TABLE `Playlist_Artist` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`playlistId` VARCHAR(36) NOT NULL,
	`artistId` VARCHAR(36) NOT NULL,
	PRIMARY KEY(`id`, `playlistId`, `artistId`)
);

CREATE TABLE `User_PlaylistOrAlbum` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`userId` VARCHAR(36) NOT NULL,
	`playlistOrAlbumId` VARCHAR(36) NOT NULL,
	`lastListen` DATETIME,
	`liked` BOOLEAN,
	PRIMARY KEY(`id`, `userId`, `playlistOrAlbumId`)
);

CREATE TABLE `Song_Album` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`songId` VARCHAR(36) NOT NULL,
	`albumId` VARCHAR(36) NOT NULL,
	PRIMARY KEY(`id`, `songId`, `albumId`)
);

CREATE TABLE `Genre` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`name` VARCHAR(255),
	`title` VARCHAR(255),
	`alias` VARCHAR(255),
	`link` VARCHAR(255),
	`createdDate` DATETIME,
	`createdBy` VARCHAR(36),
	`lastModified` DATETIME,
	`lastModifiedBy` VARCHAR(36),
	PRIMARY KEY(`id`)
);

CREATE TABLE `ArtistOrComposer` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`name` VARCHAR(255),
	`link` VARCHAR(255),
	`alias` VARCHAR(255) NOT NULL,
	`playlistId` VARCHAR(36),
	`thumbnail` VARCHAR(255),
	`totalFollow` INT,
	`biography` VARCHAR(255),
	`createdBy` VARCHAR(36),
	`lastModified` DATETIME,
	`lastModifiedBy` VARCHAR(36),
	`createdDate` DATETIME,
	`sortBiography` VARCHAR(255),
	`national` VARCHAR(255),
	`birthday` DATE,
	`realname` VARCHAR(255),
	PRIMARY KEY(`id`, `alias`)
);

CREATE TABLE `User_ArtistOrComposer` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`userId` VARCHAR(36) NOT NULL,
	`artistOrComposerId` VARCHAR(36) NOT NULL,
	`isFollow` BOOLEAN,
	PRIMARY KEY(`id`, `userId`, `artistOrComposerId`)
);

CREATE TABLE `Award` (
	`id` VARCHAR(36) NOT NULL UNIQUE,
	`artistId` VARCHAR(36),
	`name` VARCHAR(255),
	PRIMARY KEY(`id`)
);

ALTER TABLE `Song_Artist`
ADD FOREIGN KEY(`songId`) REFERENCES `Song`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Genre`
ADD FOREIGN KEY(`songId`) REFERENCES `Song`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Composer`
ADD FOREIGN KEY(`songId`) REFERENCES `Song`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Playlist`
ADD FOREIGN KEY(`songId`) REFERENCES `Song`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `User_Song`
ADD FOREIGN KEY(`songId`) REFERENCES `Song`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song`
ADD FOREIGN KEY(`streamming`) REFERENCES `Streaming`(`id`)
ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE `User_Song`
ADD FOREIGN KEY(`userId`) REFERENCES `User`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song`
ADD FOREIGN KEY(`createdBy`) REFERENCES `User`(`id`)
ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE `Song`
ADD FOREIGN KEY(`lastModifiedBy`) REFERENCES `User`(`id`)
ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE `Playlist_Genre`
ADD FOREIGN KEY(`playlistId`) REFERENCES `PlaylistOrAlbum`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Playlist_Artist`
ADD FOREIGN KEY(`playlistId`) REFERENCES `PlaylistOrAlbum`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `User_PlaylistOrAlbum`
ADD FOREIGN KEY(`userId`) REFERENCES `User`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `User_PlaylistOrAlbum`
ADD FOREIGN KEY(`playlistOrAlbumId`) REFERENCES `PlaylistOrAlbum`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Playlist`
ADD FOREIGN KEY(`playlistId`) REFERENCES `PlaylistOrAlbum`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Album`
ADD FOREIGN KEY(`songId`) REFERENCES `Song`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Album`
ADD FOREIGN KEY(`albumId`) REFERENCES `PlaylistOrAlbum`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Genre`
ADD FOREIGN KEY(`genreId`) REFERENCES `Genre`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Composer`
ADD FOREIGN KEY(`composerId`) REFERENCES `ArtistOrComposer`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Song_Artist`
ADD FOREIGN KEY(`artistId`) REFERENCES `ArtistOrComposer`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `ArtistOrComposer`
ADD FOREIGN KEY(`playlistId`) REFERENCES `PlaylistOrAlbum`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Playlist_Genre`
ADD FOREIGN KEY(`genreId`) REFERENCES `Genre`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Playlist_Artist`
ADD FOREIGN KEY(`artistId`) REFERENCES `ArtistOrComposer`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `User_ArtistOrComposer`
ADD FOREIGN KEY(`userId`) REFERENCES `User`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `User_ArtistOrComposer`
ADD FOREIGN KEY(`artistOrComposerId`) REFERENCES `ArtistOrComposer`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `Award`
ADD FOREIGN KEY(`artistId`) REFERENCES `ArtistOrComposer`(`id`)
ON UPDATE CASCADE ON DELETE CASCADE;