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
  team_id uuid references team (id) on delete cascade;

alter table statistics
  alter column
  team_id set not null;

alter table statistics
  drop constraint unique_statistics;

alter table statistics
  add constraint unique_statistics unique (type, sub_type, extra_value, user_id, team_id);


alter table notification
  add column
  team_id uuid references team (id) on delete cascade;

alter table notification
  alter column
  team_id set not null;

alter table notification
  drop constraint unique_notification;

alter table notification
  add constraint unique_notification unique (about_user_id, to_whom_user_id, achievement_id, team_id);