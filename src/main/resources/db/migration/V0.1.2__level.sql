create table level (
  "order" bigint not null primary key,
  name    text   not null
);

insert into level ("order", name)
values (0, 'Somelevel'),
       (1, 'Junior'),
       (2, 'Somelevel'),
       (3, 'Somelevel'),
       (4, 'Somelevel'),
       (5, 'Somelevel'),
       (6, 'Somelevel'),
       (7, 'Somelevel');


alter table user_data
  add column level_order bigint not null default 0 references level ("order");

create table requirement (
  id          bigint primary key,
  type        text   not null,
  sub_type    text   not null,
  level_order bigint not null references level ("order"),
  name        text,
  value       bigint not null,
  unique (type, sub_type, name)
);

insert into requirement (id, type, sub_type, level_order, name, value)
values (1, 'time', 'per_life', 1, null, 0),
       (2, 'time', 'per_life', 2, null, 100),
       (3, 'time', 'per_life', 3, null, 200),
       (4, 'symbol', 'per_life', 1, null, 10),
       (5, 'symbol', 'per_life', 2, null, 1000),
       (6, 'symbol', 'per_life', 3, null, 2000),
       (7, 'time', 'continuous_per_life', 1, null, 0),
       (8, 'time', 'continuous_per_life', 2, null, 1000 * 60 * 60),
       (9, 'time', 'continuous_per_life', 3, null, 1000 * 60 * 60 * 2),

--   (10, 'symbol','continuous_per_life', 1, 1),
--   (11 ,'symbol','continuous_per_life', 2, 500),
--   (12, 'symbol','continuous_per_life', 3, 1000);

      (13, 'symbol','single_key', 1, 'A', 1),
--   (14, 'symbol','single_key', 2, 2000),
--   (15, 'symbol','single_key', 3, 5000);
      (16, 'commit', 'per_life', 1, 'successful', 1),
      (17, 'exec', 'per_life', 1, 'successful', 1),
      (18, 'exec', 'per_life', 1, 'failed', 2),
      (19, 'lang_time', 'per_life', 1, 'Java', 1),
      (20, 'lang_symbol', 'per_day', 1, 'Kotlin', 10);