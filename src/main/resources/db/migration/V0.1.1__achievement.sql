create table achievement (
  id uuid primary key,
  type text not null,
  sub_type text not null,
  name text,
  value bigint not null,
  user_id uuid references user_data (id) not null,
  unique (type, sub_type, name, user_id)
);

insert into achievement (id, type, sub_type, name, "value", user_id) values
('123e4567-e89b-12d3-a456-556642440000', 'time', 'subType', 'test', 123, '123e4567-e89b-12d3-a456-556642440000');