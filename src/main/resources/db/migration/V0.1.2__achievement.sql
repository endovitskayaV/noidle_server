create table achievement (
  id           bigint not null primary key,
  type         text   not null,
  level_number bigint,
  "name"       text   not null
);

create table notification (
  id              uuid      not null primary key,
  about_user_id   uuid      not null references user_data (id),
  to_whom_user_id uuid      not null references user_data (id),
  achievement_id  bigint    not null references achievement (id),
  is_sent         boolean   not null,
  date            timestamp not null,
  unique (about_user_id, to_whom_user_id, achievement_id)
);

create table requirement (
  id                     bigint primary key,
  type                   text   not null,
  sub_type               text   not null,
  achievement_id         bigint not null references achievement (id),
  extra_value            text,
  value                  bigint not null,
  team_contribution_rate real,
  unique (type, sub_type, achievement_id, extra_value, value, team_contribution_rate)
);
