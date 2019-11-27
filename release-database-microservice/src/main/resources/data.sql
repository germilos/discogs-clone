INSERT INTO discogs_release_microservice_two.genre(name, id, description)
	VALUES ('Hip Hop', 1, 'Some desc');
INSERT INTO discogs_release_microservice_two.genre(name, id, description)
	VALUES ('Metal', 2, 'Some desc');
INSERT INTO discogs_release_microservice_two.genre(name, id, description)
	VALUES ('Jazz', 3, 'Some desc');
INSERT INTO discogs_release_microservice_two.genre(name, id, description)
	VALUES ('Trap', 4, 'Some desc');

INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('West Side', 1, 1, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('East Side', 2, 1, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('Glam Metal', 3, 2, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('Trash Metal', 4, 2, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('Bebop', 5, 3, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('NY Trap', 6, 4, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('Mumble', 7, 4, 'Some desc');
INSERT INTO discogs_release_microservice_two.style(
    name, id, genere_id, description)
    VALUES ('Shitty shit', 8, 4, 'Some desc');

INSERT INTO discogs_release_microservice_two."user"(
	id, username)
	VALUES (1, 'Milos');
INSERT INTO discogs_release_microservice_two."user"(
	id, username)
	VALUES (2, 'Frankie');

--	LABEL INSERTS
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Atlanta Records', '1990-10-12', '1990-10-12', null, null, null, null, null, null, null, 1, null, null, 'Label');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Freebird Label', '2012-5-11', '2012-5-11', null, null, null, null, null, null, null, 2, null, null, 'Label');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Southside Records', '1999-10-11', '2012-5-11', null, null, null, null, null, null, null, 3, null, null, 'Label');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Berkshire Records', '2019-10-11', '2019-11-11', null, null, null, null, null, null, null, 4, null, null, 'Label');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('HotSpin Records', '2001-5-2', '2011-5-11', null, null, null, null, null, null, null, 5, null, null, 'Label');

--ARTIST INSERTS
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Metallica', '1993-10-12', '1993-10-12', null, null, null, null, null, null, null, 6, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Aerosmith', '1995-10-12', '1996-10-12', null, null, null, null, null, null, null, 7, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Tupac', '1997-3-10', '1999-10-12', null, null, null, null, null, null, null, 8, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Wu-Tang Clan', '1992-10-12', '1998-10-12', null, null, null, null, null, null, null, 9, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('NWA', '2001-1-2', '2005-10-12', null, null, null, null, null, null, null, 10, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Coby', '2017-4-5', '2019-12-12', null, null, null, null, null, null, null, 11, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Fox', '2018-5-12', '2019-10-28', null, null, null, null, null, null, null, 12, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Mili', '2018-10-12', '2019-10-12', null, null, null, null, null, null, null, 13, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Miles Davis', '1974-11-12', '2001-10-12', null, null, null, null, null, null, null, 14, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('John Coltrane', '1977-10-12', '1990-10-12', null, null, null, null, null, null, null, 15, null, null, 'Artist');
INSERT INTO discogs_release_microservice_two.item(
     name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
     VALUES ('Dave Brubeck Quartet', '1986-10-12', '2000-10-12', null, null, null, null, null, null, null, 16, null, null, 'Artist');

--RELEASE INSERTS
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Ride the Lightning', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1996', null, null, 17, 1, 6, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Kill Em All', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '2000', null, null, 18, 1, 6, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Some Aerosmith Song', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1996', null, null, 19, 1, 7, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Some Other Aerosmith Song', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1996', null, null, 20, 1, 7, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Some Tupac Song', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1996', null, null, 21, 2, 8, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Some Other Tupac Song', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1996', null, null, 22, 2, 8, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Wu Tang Song 1', '1996-2-2', '1996-2-2', null, 'Vinyl', 'America', null, '1996', null, null, 23, 2, 9, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('NWA Song 1', '1996-2-2', '1996-2-2', null, 'CD', 'England', null, '1996', null, null, 24, 2, 10, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('NWA Song 2', '1996-2-2', '1996-2-2', null, 'CD', 'England', null, '1996', null, null, 25, 2, 10, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Coby Song 1', '1996-2-2', '1996-2-2', null, 'Vinyl', 'Serbia', null, '1996', null, null, 26, 3, 11, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Coby Song 2', '1996-2-2', '1996-2-2', null, 'Vinyl', 'Serbia', null, '1996', null, null, 27, 3, 11, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Fox Song 1', '1996-2-2', '1996-2-2', null, 'CD', 'Serbia', null, '1996', null, null, 28, 3, 12, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Mili Song 1', '1996-2-2', '1996-2-2', null, 'CD', 'Serbia', null, '2019', null, null, 29, 3, 13, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Mili Song 2', '1996-2-2', '1996-2-2', null, 'CD', 'Serbia', null, '2016', null, null, 30, 3, 13, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Mili Song 3', '1996-2-2', '1996-2-2', null, 'CD', 'Serbia', null, '2019', null, null, 31, 3, 13, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Miles Davis Song 1', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1970', null, null, 32, 4, 14, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Miles Davis Song 2', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1969', null, null, 33, 4, 14, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Miles Davis Song 3', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1996', null, null, 34, 5, 14, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('John Coltrane Song 1', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1989', null, null, 35, 4, 15, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('John Coltrane Song 2', '1996-2-2', '1996-2-2', null, 'CD', 'America', null, '1979', null, null, 36, 4, 15, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Dave Brubeck Song 1', '1996-2-2', '1996-2-2', null, 'CD', 'Italy', null, '2000', null, null, 37, 5, 16, 'Release');
INSERT INTO discogs_release_microservice_two.item(
   	name, date_added, date_last_changed, uri, format, country, notes, released, profile, contact_info, id, label_id, artist_id, item_type)
    VALUES ('Dave Brubeck Song 2', '1996-2-2', '1996-2-2', null, 'CD', 'Italy', null, '2002', null, null, 38, 5, 16, 'Release');

--ITEM-USER INSERTS
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (1, 1, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (2, 2, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (3, 3, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (4, 4, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (5, 5, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (6, 6, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (7, 7, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (8, 8, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (9, 9, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (10, 10, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (11, 11, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (12, 12, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (13, 13, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (14, 14, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (15, 15, 1, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (16, 16, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (17, 17, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (18, 18, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (19, 19, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (20, 20, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (21, 21, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (22, 22, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (23, 23, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (24, 24, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (25, 25, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (26, 26, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (27, 27, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (28, 28, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (29, 29, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (30, 30, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (31, 31, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (32, 32, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (33, 33, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (34, 34, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (35, 35, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (36, 36, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (37, 37, 2, '1999-01-08 04:05:06');
INSERT INTO discogs_release_microservice_two.item_user(
	id, item_id, user_id, contribution_date)
	VALUES (38, 38, 2, '1999-01-08 04:05:06');

--RELEASE-GENRE
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
--INSERT INTO discogs_release_microservice_two.release_genre(
--	id, release_id, genre_id)
--	VALUES (?, ?, ?);
