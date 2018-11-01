create table achievement (
  id uuid primary key,
  type text not null,
  name text not null,
  value bigint not null,
  user_id uuid references user_data (id) not null
);

