create table level(
  "order" bigint not null primary key,
  name text not null
);

insert into level ("order", name) values
  (0, 'Somelevel'),
  (1, 'Junior'),
  (2, 'Somelevel'),
  (3, 'Somelevel'),
  (4, 'Somelevel'),
  (5, 'Somelevel'),
  (6, 'Somelevel'),
  (7, 'Somelevel');


alter table user_data add column level_order bigint not null default 0 references level("order");

create table requirement (
  id bigint primary key,
  type text not null,
  sub_type text not null,
  level_order bigint not null references level("order"),
  name text,
  value bigint not null,
  unique (type, sub_type, name)
);

insert into requirement (id, type, sub_type, level_order, value) values
  (1, 'time','per_life', 1, 0),
  (2, 'time','per_life', 2, 100),
  (3, 'time','per_life', 3, 200),

  (4, 'symbol','per_life', 1, 10),
  (5, 'symbol','per_life', 2, 1000),
  (6, 'symbol','per_life', 3, 2000),

  (7, 'time','continuous_per_life', 1, 0),
  (8, 'time','continuous_per_life', 2, 1000*60*60),
  (9, 'time','continuous_per_life', 3, 1000*60*60*2);

--   (10, 'symbol','continuous_per_life', 1, 1),
--   (11 ,'symbol','continuous_per_life', 2, 500),
--   (12, 'symbol','continuous_per_life', 3, 1000);

--   (13, 'symbol','single_key', 1, 0),
--   (14, 'symbol','single_key', 2, 2000),
--   (15, 'symbol','single_key', 3, 5000);