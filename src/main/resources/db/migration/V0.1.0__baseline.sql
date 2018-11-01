create table user_data (
  id uuid primary key,
  email text unique not null,
  name text  not null,
  photo text
);