create table achievement (
  id uuid primary key,
  type text not null,
  name text not null,
  value bigint not null,
  user_id uuid references user_data (id) not null
);



insert into achievement (id, type, name, "value", user_id) values
('123e4567-e89b-12d3-a456-556642440000', 'time', 'name', 123, '123e4567-e89b-12d3-a456-556642440000');