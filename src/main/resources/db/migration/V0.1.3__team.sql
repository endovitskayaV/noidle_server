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

alter table statistics
  add column
  team_id uuid references team (id);

alter table statistics
  drop constraint unique_statistics;

alter table statistics
  add constraint unique_statistics unique (type, subtype, extravalue, user_id, team_id);

alter table notification
  add column
  team_id uuid references team (id);