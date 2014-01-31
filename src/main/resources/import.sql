--Description of tables



-- First scenario
-- Luca, for his birthday, has invited Mario and Peppe to eat a pizza  
-- in Torre Annunziata on March 15th 2013 at 20:30  
-- Giovanni has been invited too, but he was busy with studies so he decided not to participate
-- Mario at 19:45 is in Naples, at 20:00 is on the highway
-- Giuseppe at 20:00 is in Sant'agnello, at 20:15 is still in Sant'Agnello
-- Luca at 20:00 is in Torre Annunziata, at 20:15 is still in Torre Annunziata

-- 03/15/2013 19:45 GMT+1 is 1363373100
-- 03/15/2013 20:00 GMT+1 is 1363374000
-- 03/15/2013 20:15 GMT+1 is 1363374900
-- 03/15/2013 20:30 GMT+1 is 1363375800

-- Torre Annunziata Meeting coordinates: 40° 45.475', 14° 26.354' (40.75791666666667, 14.439233333333334)
insert into latlngs(id,latitude,longitude) values(1,40.75791666666667, 14.439233333333334)

-- Highway coordinates: 40° 48.544', 14° 21.770' (40.809066666666666, 14.362833333333333)
insert into latlngs(id,latitude,longitude) values(2,40.809066666666666, 14.362833333333333)

-- Torre Annunziata coodinates: 40° 45.473', 14° 26.367' (40.75788333333333, 14.43945)
insert into latlngs(id,latitude,longitude) values(3,40.75788333333333, 14.43945)

-- Naples coordinates: 40° 49.092', 14° 10.764' (40.8182, 14.1794)
insert into latlngs(id,latitude,longitude) values(4,40.8182, 14.1794)

-- Sant'Agnello coordinates: 40° 37.904', 14° 23.912' (40.63173333333334, 14.398533333333333)
insert into latlngs(id,latitude,longitude) values(5,40.63173333333334, 14.398533333333333)

-- Sorbillo coordinates, Via dei Tribunali
insert into latlngs(id,latitude,longitude) values(6,40.851501, 14.258927)

insert into users(name,phone) values ('peppe','3391421288');
insert into users(name,phone) values ('mario','3490838036');
insert into users(name,phone) values ('luca','3201522887');
insert into users(name,phone) values ('giovanni','3733912057');
insert into users(name,phone) values ('alfredo','3201424344');

insert into meetings(id,title,address,date,latlng_id,mc) values (1,'Pizza for Luca birthday', 'Corso Umberto I, 274', 1363375800000, 1, '3201522887');
insert into guests values (1,'3733912057');
insert into participants values (1,'3391421288');
insert into participants values (1,'3490838036');
-- insert into locations(id,user_id,timestamp,latlng_id) values (0,'3490838036',1363373100000,4);
insert into locations(id,user_id,timestamp,latlng_id) values (1,'3490838036',1363374000000,2);
-- insert into locations(id,user_id,timestamp,latlng_id) values (2,'3391421288',1363374000000,5);
insert into locations(id,user_id,timestamp,latlng_id) values (3,'3391421288',1363374900000,5);
insert into locations(id,user_id,timestamp,latlng_id) values (4,'3201522887',1363374900000,3);
-- insert into locations(id,user_id,timestamp,latlng_id) values (5,'3201522887',1363374000000,3);

insert into meetings(id,title,address,date,latlng_id,mc) values (2,'A pizza at Sorbillo', 'Via dei Tribunali', 1392751842000, 6, '3490838036');
insert into guests values (2,'3391421288');
insert into guests values (2,'3201522887');
insert into participants values (2,'3733912057');


