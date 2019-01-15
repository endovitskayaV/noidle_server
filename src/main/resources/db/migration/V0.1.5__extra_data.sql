insert into requirement (id, type, sub_type, achievement_id, extra_value, value)
values  (1000, 'time', 'per_life', 65, null, 1000 * 60 * 60 * 60 * 24 * 17),
        (1001, 'time', 'per_life', 67, null, 1000 * 60 * 60 * 60 * 24 * 30 * 5),

        (1002, 'symbol', 'per_life', 66, null, 100000),
        (1003, 'symbol', 'per_life', 68, null, 1000000),

        (1004, 'commit', 'per_life', 35, 'failed', 1),
        (1005, 'commit', 'per_life', 36, 'failed', 20),
        (1005, 'commit', 'per_life', 37, 'failed', 100),

        (1004, 'commit', 'per_life', 38, 'successful', 1),
        (1005, 'commit', 'per_life', 36, 'failed', 20),
        (1005, 'commit', 'per_life', 37, 'failed', 100),