\c footballmanager;

insert into team (name, country, town, balance) VALUES
('FC Barcelona', 'Spain', 'Barcelona', 300000000),
('FC Real Madrid', 'Spain', 'Madrid', 500000000),
('FC Dynamo Kyiv', 'Ukraine', 'Kyiv',150000000),
('FC Bayern Munich', 'Germany', 'Munich', 350000000);

insert into player (first_name, last_name, birth_date, start_career, team_id) VALUES
('Lionel', 'Messi', '1985-09-11','2005-10-10',1),
('Gerard', 'Pique', '1985-10-11', '2005-11-10',1),
('Iker', 'Cassilias', '1991-10-11', '2010-08-10',2),
('Cristiano', 'Ronaldo', '1985-07-20', '2005-02-15',2),
('Andrii', 'Shevchenko', '1990-12-11', '2008-08-21',3);

