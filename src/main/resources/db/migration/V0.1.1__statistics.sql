create table statistics (
  id          uuid primary key,
  type        text                           not null,
  subtype    text                           not null,
  extravalue text,
  value       bigint                         not null,
  user_id     uuid references user_data (id) not null ,
  date        timestamp                      not null,
 constraint unique_statistics unique (type, subtype, extravalue, user_id)
);