create table statistics (
  id       uuid primary key,
  type     text                           not null,
  sub_type text                           not null,
  name     text,
  value    bigint                         not null,
  user_id  uuid references user_data (id) not null,
  date     bigint                         not null,
  unique (type, sub_type, name, user_id)
);