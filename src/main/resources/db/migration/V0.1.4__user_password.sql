alter table user_data
  add column password varchar;

update user_data
set password = ''
where password isnull;

alter table user_data
  alter column password set not null;