create table playlist (
    id varchar(36) not null unique default (uuid()),
    title CHAR(255) CHARACTER SET UTF8MB4,
    thumbnail varchar(255),
    is_official boolean,
    link CHAR(255) CHARACTER SET UTF8MB4,
    release_date date,
    sort_description text CHARACTER SET UTF8MB4,
    released_at int,
    artists_names CHAR(255) CHARACTER SET UTF8MB4,
    is_private boolean,
    is_album boolean,
    text_type varchar(255),
    distributor CHAR(255) CHARACTER SET UTF8MB4,
    description mediumtext CHARACTER SET UTF8MB4,
    alias_title varchar(255) UNIQUE,
    likes int,
    listen int,
    primary key (id)
);

create table playlist_song (
    id varchar(36) not null unique,
    song_id varchar(36) not null,
    playlist_id varchar(36) not null,
    primary key (id, song_id, playlist_id)
);

create table playlist_genre (
    id varchar(36) not null unique,
    playlist_id varchar(36) not null,
    genre_id varchar(36) not null,
    primary key (id, playlist_id, genre_id)
);

create table playlist_user (
    id varchar(36) not null unique,
    user_id varchar(36) not null,
    playlist_id varchar(36) not null,
    last_listen datetime,
    liked boolean,
    primary key (id, user_id, playlist_id)
);

CREATE TABLE playlist_artist (
    id VARCHAR(36) NOT NULL UNIQUE,
    playlist_id VARCHAR(36) NOT NULL,
    artist_id VARCHAR(36) NOT NULL,
    PRIMARY KEY(id, playlist_id, artist_id)
);

create table album_song (
    id varchar(36) not null unique,
    song_id varchar(36) not null,
    album_id varchar(36) not null,
    primary key (id, song_id, album_id)
);
