create table statistics (
  id          uuid primary key,
  type        text                           not null,
  sub_type    text                           not null,
  extra_value text,
  value       bigint                         not null,
  user_id     uuid references user_data (id) not null ,
  date        timestamp                      not null,
  unique (type, sub_type, extra_value, user_id)
);