create table user_data (
  id    uuid primary key,
  email text unique not null,
  name  text unique not null,
  photo text
);

alter table user_data
  add column password varchar;

update user_data
set password = ''
where password isnull;

alter table user_data
  alter column password set not null;