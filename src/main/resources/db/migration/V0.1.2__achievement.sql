create table achievement (
  id           bigint not null primary key,
  type         text   not null,
  level_number bigint,
  "name"       text   not null
);

insert into achievement (id, type, level_number, "name")
values (1, 'level', 1, 'Junior'),
       (2, 'level', 2, 'Somelevel'),
       (3, 'level', 3, 'Somelevel'),
       (4, 'level', 4, 'Somelevel'),
       (5, 'level', 5, 'Somelevel'),
       (6, 'level', 6, 'Somelevel'),
       (7, 'level', 7, 'Somelevel'),
       (8, 'extra', null, 'Extraaa'),
       (9, 'extra', null, 'Extraaa100500'),
       (10, 'team', null, 'Team100500');

create table notification (
  id              uuid      not null primary key,
  about_user_id   uuid      not null references user_data (id),
  to_whom_user_id uuid      not null references user_data (id),
  achievement_id  bigint    not null references achievement (id),
  is_sent         boolean   not null,
  date            timestamp not null,
  unique (about_user_id, to_whom_user_id, achievement_id)
);

create table requirement (
  id                     bigint primary key,
  type                   text   not null,
  sub_type               text   not null,
  achievement_id         bigint not null references achievement (id),
  extra_value            text,
  value                  bigint not null,
  team_contribution_rate real,
  unique (type, sub_type, achievement_id, extra_value, value, team_contribution_rate)
);

insert into requirement (id, type, sub_type, achievement_id, extra_value, value, team_contribution_rate)
values (1, 'time', 'per_life', 1, null, 0, null),
       (21, 'time', 'per_day', 8, null, 0, null),
       (22, 'time', 'per_day', 9, null, 0, null),
       (23, 'commit', 'per_life', 10, 'successful', 1, 0.5);
--        (2, 'time', 'per_life', 2, null, 100),
--        (3, 'time', 'per_life', 3, null, 200),
--        (4, 'symbol', 'per_life', 1, null, 10),
--        (5, 'symbol', 'per_life', 2, null, 1000),
--        (6, 'symbol', 'per_life', 3, null, 2000),
--        (7, 'time', 'continuous_per_life', 1, null, 0),
--        (8, 'time', 'continuous_per_life', 2, null, 1000 * 60 * 60),
--        (9, 'time', 'continuous_per_life', 3, null, 1000 * 60 * 60 * 2),
--
-- --   (10, 'symbol','continuous_per_life', 1, 1),
-- --   (11 ,'symbol','continuous_per_life', 2, 500),
-- --   (12, 'symbol','continuous_per_life', 3, 1000);
--
--       (13, 'symbol','single_key', 1, 'A', 1),
-- --   (14, 'symbol','single_key', 2, 2000),
-- --   (15, 'symbol','single_key', 3, 5000);
--       (16, 'commit', 'per_life', 1, 'successful', 1),
--       (17, 'exec', 'per_life', 1, 'successful', 1),
--       (18, 'exec', 'per_life', 1, 'failed', 2),
--       (19, 'lang_time', 'per_life', 1, 'Java', 1),
--       (20, 'lang_symbol', 'per_day', 1, 'Kotlin', 10);