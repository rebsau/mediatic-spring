-- test init data


insert into adherent (id, prenom, nom, date_naissance, email) values (10, 'zino', 'kholladi', '2005-11-11', 'add@gmail.com');
insert into adherent (id, prenom, nom, date_naissance, email) values (20, 'reb', 'sau', '2005-11-11', 'add@gmail.com');
insert into adherent (id, prenom, nom, date_naissance, email) values (30, 'jean', 'nuit', '2005-11-11', 'add@gmail.com');
insert into adherent (id, prenom, nom, date_naissance, email) values (40, 'lily', 'kholladi', '2005-11-11', 'add@gmail.com');


insert into media (id, titre, auteur, type) values (1,'tt','aa','DVD');
insert into media (id, titre, auteur, type) values (2,'titre','blabla','DVD');
insert into media (id, titre, auteur, type) values (3,'autrechose','aa','CD');
insert into media (id, titre, auteur, type) values (4,'tit','aa','Livre');

insert into emprunt (id, adherent_id, media_id, date_emprunt) values (11, 10, 2, '2016-12-04');
insert into emprunt (id, adherent_id, media_id, date_emprunt) values (12, 20, 1, '2016-10-04');
insert into emprunt (id, adherent_id, media_id, date_emprunt) values (13, 40, 2, '2016-12-04');

update media set emprunt_id = 12 where id = 1;
update media set emprunt_id = 11 where id = 2;

