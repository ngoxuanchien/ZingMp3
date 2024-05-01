CREATE TABLE playlistEntity
(
    id VARCHAR(36) NOT NULL UNIQUE PRIMARY KEY,
    title NVARCHAR(255),
    thumbnail VARCHAR(255),
    is_official BOOLEAN,
    link VARCHAR(255),
    release_date DATE,
    sort_description NVARCHAR(255),
    release_at INT,
    artists_names NVARCHAR(255),
    is_private BOOLEAN,
    is_album BOOLEAN,
    text_type VARCHAR(255),
    distributor VARCHAR(255),
    description NVARCHAR(1000),
    alias_title VARCHAR(255) NOT NULL,
    likes INT,
    listen INT
);