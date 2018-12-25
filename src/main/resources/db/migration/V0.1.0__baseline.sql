create table user_data (
  id    uuid primary key,
  email text unique not null,
  name  text unique not null,
  photo text
);

--test data

insert into user_data (id, email, name, photo)
  values ('123e4567-e89b-12d3-a456-556642440000', 'email', 'test_user', 'photo');

insert into user_data (id, email, name, photo)
  values ('123e4567-e89b-12d3-a456-556642440001', 'email1', 'test_user2', 'photo')