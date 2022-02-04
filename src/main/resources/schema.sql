create database footballmanager;
\c footballmanager;
create table team (
    id serial primary key,
    name character varying(30),
    country character varying(30),
    town character varying(30),
    balance integer
);

create table player(
    id serial primary key,
    first_name character varying(30),
    last_name character varying(30),
    birth_date date,
    start_career date,
    team_id integer,
    foreign key (team_id) references team (id) on delete cascade
);