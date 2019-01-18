insert into requirement (id, type, sub_type, achievement_id, extra_value, value, team_contribution_rate)
values (5000, 'commit', 'per_life', 79, 'successful', 10, 0.3),

       (5001, 'exec', 'per_day', 80, 'failed', 50, 0.6),
       (5002, 'exec', 'per_day', 80, 'successful', 80, 0.7),

       (5003, 'commit', 'per_life', 81, 'successful', 100, 0.5),
       (5004, 'commit', 'per_life', 82, 'successful', 500, 0.5),
       (5005, 'commit', 'per_life', 83, 'successful', 1000, 0.7),

       (5006, 'single_key', 'per_life', 84, 'Ctrl', 500, 0.5),
       (5007, 'single_key', 'per_life', 84, 'C', 500, 0.5),
       (5008, 'single_key', 'per_life', 84, 'V', 500, 0.5),

       (5009, 'single_key', 'per_life', 85, 'Ctrl', 10000, 0.7),
       (5010, 'single_key', 'per_life', 85, 'C', 10000, 0.7),
       (5011, 'single_key', 'per_life', 85, 'V', 10000, 0.7),

       (5012, 'single_key', 'per_life', 86, 'Ctrl', 10000, 0.8),
       (5013, 'single_key', 'per_life', 86, 'C', 10000, 0.8),
       (5014, 'single_key', 'per_life', 86, 'V', 10000, 0.8),

       (5015, 'time', 'per_day', 87, null, 21600000, 0.8), --6h

       (5016, 'commit', 'per_day', 88, 'successful', 10, 0.8),
       (5017, 'commit', 'per_day', 89, 'successful', 20, 0.8),
       (5018, 'commit', 'per_day', 90, 'successful', 30, 0.8);