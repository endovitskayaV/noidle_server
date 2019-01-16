alter table user_data
  add column update_role varchar;

update user_data
set update_role = 'user'
where update_role isnull;

alter table user_data
  alter column update_role set not null;