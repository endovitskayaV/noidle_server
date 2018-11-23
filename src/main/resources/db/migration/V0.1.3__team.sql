create table team (
  id uuid primary key,
  name text unique not null default concat('New team', (select row_number() from team)),
  photo text
);

 create table user_data_team(
  user_id uuid references user_data (id),
  team_id uuid references team (id),
  constraint user_data_team_pk primary key (user_id, team_id)
);