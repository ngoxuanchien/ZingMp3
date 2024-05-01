create table song_image(
    id varchar(36) primary key unique,
    song_id varchar(36),
    name varchar(255),
    type varchar(255),
    file_path varchar(500)
);

create table playlist_image(
    id varchar(36) primary key unique,
    playlist_id varchar(36),
    name varchar(255),
    type varchar(255),
    file_path varchar(500)
);

create table artist_image(
    id varchar(36) primary key unique,
    artist_id varchar(36),
    name varchar(255),
    type varchar(255),
    file_path varchar(500)
);

create table streaming_file(
    id varchar(36) primary key unique,
    alias varchar(255),
    path128kps varchar(500),
    path320kps varchar(500)
);

