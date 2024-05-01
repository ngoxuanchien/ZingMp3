CREATE TABLE song (
    id VARCHAR(36) NOT NULL UNIQUE,
    title VARCHAR(255),
    alias VARCHAR(255) UNIQUE,
    is_official BOOLEAN,
    artists_names VARCHAR(255),
    is_world_wide BOOLEAN,
    thumbnail VARCHAR(255),
    duration INT,
    is_private BOOLEAN,
    release_date INT,
    distributor VARCHAR(255),
    has_lyric BOOLEAN,
    likes INT DEFAULT 0,
    listen INT DEFAULT 0,
    liked BOOLEAN DEFAULT false,
    comment INT DEFAULT 0,
    streaming VARCHAR(36),

    created_date DATETIME,
    created_by VARCHAR(36),
    last_modified DATETIME,
    last_modified_by VARCHAR(36),
    PRIMARY KEY(id, alias)
);

CREATE TABLE artist (
    id VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(255),
    link VARCHAR(255),
    alias VARCHAR(255) NOT NULL unique,
    playlist_id VARCHAR(36),
    thumbnail VARCHAR(255),
    total_follow INT,
    biography mediumtext,
    sort_biography VARCHAR(255),
    national VARCHAR(255),
    birthday DATE,
    real_name VARCHAR(255),

    created_by VARCHAR(36),
    last_modified DATETIME,
    lastModified_by VARCHAR(36),
    created_date DATETIME,
    PRIMARY KEY(`id`, `alias`)
);

CREATE TABLE song_artist (
    id VARCHAR(36) NOT NULL unique ,
    artist_id VARCHAR(36) NOT NULL,
    song_id VARCHAR(36) NOT NULL,
    PRIMARY KEY(id, artist_id, song_id)
);

CREATE TABLE song_genre (
    id VARCHAR(36) NOT NULL UNIQUE,
    song_id VARCHAR(36) NOT NULL,
    genre_id VARCHAR(36) NOT NULL,
    PRIMARY KEY(id, song_id, genre_id)
);

CREATE TABLE song_composer (
    id VARCHAR(36) NOT NULL UNIQUE,
    song_id VARCHAR(36) NOT NULL,
    composer_id VARCHAR(36) NOT NULL,
    PRIMARY KEY(id, song_id, composer_id)
);

CREATE TABLE song_user (
    id VARCHAR(36) NOT NULL UNIQUE,
    user_id VARCHAR(36),
    song_id VARCHAR(36),
    liked BOOLEAN DEFAULT false,
    last_listen DATETIME,
    PRIMARY KEY(id)
);

CREATE TABLE streaming (
    id VARCHAR(36) NOT NULL UNIQUE,
    url_128kps VARCHAR(255),
    url_320kps VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE genre (
    id VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(255),
    title VARCHAR(255),
    alias VARCHAR(255) UNIQUE,
    link VARCHAR(255),
    createdDate DATETIME,
    createdBy VARCHAR(36),
    lastModified DATETIME,
    lastModifiedBy VARCHAR(36),
    PRIMARY KEY(id)
);

CREATE TABLE award (
    id VARCHAR(36) NOT NULL UNIQUE,
    artist_id VARCHAR(36),
    name VARCHAR(255),
    PRIMARY KEY(id)
);