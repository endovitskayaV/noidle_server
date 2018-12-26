create table team (
  id      uuid        not null primary key,
  name    text unique not null,
  photo   text,
  created timestamp
);

create table user_data_team (
  user_id uuid references user_data (id) ON DELETE CASCADE,
  team_id uuid references team (id) ON DELETE CASCADE,
  constraint user_data_team_pk primary key (user_id, team_id)
);

--test data

insert into team
values ('123e4567-e89b-12d3-a456-556642440000', 'new team', null);

insert into team
values ('123e4567-e89b-12d3-a456-556642440001', 'team 2', null);

insert into user_data_team
values ('123e4567-e89b-12d3-a456-556642440000', '123e4567-e89b-12d3-a456-556642440000'),
       ('123e4567-e89b-12d3-a456-556642440001', '123e4567-e89b-12d3-a456-556642440000'),
       ('1f752e8b-a78a-476c-be03-4c927953e488', '123e4567-e89b-12d3-a456-556642440000');

alter table statistics
  add column
  team_id uuid references team (id);

alter table notification
  add column
  team_id uuid references team (id);