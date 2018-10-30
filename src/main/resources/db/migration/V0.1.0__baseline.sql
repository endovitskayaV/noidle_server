create table user_data (
  id uuid primary key,
  email text unique not null,
  password text not null,
  name text unique not null,
  photo text
);

insert into  user_data (id,email, password,name) values
('123e4567-e89b-12d3-a456-556642440000', 'email', 'password', 'name');