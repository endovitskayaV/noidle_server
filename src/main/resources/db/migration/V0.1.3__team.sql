create table team (
  id uuid primary key,
  name text unique not null,
  photo text
);

 create table user_data_team(
  user_id uuid references user_data (id),
  team_id uuid references team (id),
  constraint user_data_team_pk primary key (user_id, team_id)
);

--test data

insert into team values
   ('123e4567-e89b-12d3-a456-556642440000', 'new team', null);

insert into user_data_team values
  ('123e4567-e89b-12d3-a456-556642440000','123e4567-e89b-12d3-a456-556642440000'),
  ('123e4567-e89b-12d3-a456-556642440001','123e4567-e89b-12d3-a456-556642440000');
