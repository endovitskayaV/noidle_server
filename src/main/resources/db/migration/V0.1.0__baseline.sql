create table user_data (
  id uuid primary key,
  email text unique not null,
  password text not null,
  name text unique not null default email,
  photo text
);

create table team (
  id uuid primary key,
  name text unique not null default concat('New team', (select row_number() from team)),
  company_id uuid references company (id),
  photo text
);

create table user_data_team(
  user_id uuid references user_data (id),
  team_id uuid references team (id),
  constraint user_data_team_pk primary key (user_id, team_id)
);

create table project (
  id uuid primary key,
  name text not null, --check if it is unique in team
  team_id uuid references team (id)
);

create table role (
  code integer primary key,
  name text
);

create  table user_data_role(
  user_id uuid references user_data (id),
  role integer references role (code),
  project_id uuid references project (id),
  constraint user_data_team_pk primary key (user_id, role, project_id)
);

create table company (
  id uuid primary key,
  name text unique not null,
  photo text
);

create table achievement_type (
  code integer primary key,
  name text
);

create table achievement (
  id uuid primary key,
  type integer references achievement_type (code) not null,
  name text not null,
  value bigint not null,
  user_id uuid references user_data (id),
  photo text
);

insert into role (code, name) values
(0, 'developer'),
(1, 'lead');

insert into role (code, name) values
(0, 'time'),
(1, 'symbol'),
(2, 'test');