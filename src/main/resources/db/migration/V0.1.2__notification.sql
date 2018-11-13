--DROP EXTENSION "uuid-ossp";
--CREATE EXTENSION "uuid-ossp";

--if not exists ("uuid-ossp") then
  --    CREATE EXTENSION "uuid-ossp";

create table notification (
  id uuid primary key DEFAULT uuid_generate_v4(),
  type text not null,
  sub_type text not null,
  level integer not null,
  value bigint not null,
  unique (type, sub_type, level)
);

insert into notification (type, sub_type, level, value) values
('time','per_life', 1, 50),
('time','per_life', 2, 100),
('time','per_life', 3, 200),

('symbols','per_life', 1, 500),
('symbols','per_life', 2, 1000),
('symbols','per_life', 3, 2000),

('time','continuous_per_life', 1, 1000*60*30),
('time','continuous_per_life', 2, 1000*60*60),
('time','continuous_per_life', 3, 1000*60*60*2),

('symbols','continuous_per_life', 1, 100),
('symbols','continuous_per_life', 2, 500),
('symbols','continuous_per_life', 3, 1000),

('symbols','single_key', 1, 1000),
('symbols','single_key', 2, 2000),
('symbols','single_key', 3, 5000);


create table users_notifications(
  user_id uuid not null,
  notification_id uuid not null,
  sent boolean not null,
  primary key (user_id, notification_id)
)
