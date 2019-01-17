insert into requirement (id, "type", sub_type, achievement_id, extra_value, "value")
values  (1, 'time', 'per_day', 1, null, 36000000), --10m
        (2, 'symbol', 'per_day', 1, null, 50),

--         (3, 'time', 'per_day', 2, null, 216000000), --1h
--         (4, 'symbol', 'per_day', 2, null, 150),
--         (5, 'time', 'continuous_per_day', 2, null, 3600000), --1m
--         (6, 'symbol', 'continuous_per_day', 2, null, 100),

        (7, 'time', 'per_day', 2, null, 432000000), --2h
        (9, 'time', 'continuous_per_day', 2, null, 18000000), --5m
        (8, 'symbol', 'per_day', 2, null, 300),
        (10, 'symbol', 'continuous_per_day', 2, null, 100),
        (11, 'single_key', 'per_day', 2, 'Ctrl', 10),
        (12, 'single_key', 'per_day', 2, 'C', 10),
        (13, 'single_key', 'per_day', 2, 'V', 10),
        (14, 'single_key', 'per_day', 2, 'Shift', 4),
        (15, 'single_key', 'per_day', 2, 'F', 3),


        (17, 'time', 'per_day', 3, null, 648000000), --3h
        (19, 'time', 'continuous_per_day', 3, null, 25200000), --7m
        (18, 'symbol', 'per_day', 3, null, 500),
        (20, 'symbol', 'continuous_per_day', 3, null, 150),
        (21, 'single_key', 'per_day', 3, 'Ctrl', 15),
        (22, 'single_key', 'per_day', 3, 'C', 20),
        (23, 'single_key', 'per_day', 3, 'V', 17),
        (24, 'single_key', 'per_day', 3, 'Shift', 7),
        (25, 'single_key', 'per_day', 3, 'F', 8),
        (26, 'single_key', 'per_day', 3, 'L', 30),
        (27, 'single_key', 'per_day', 3, 'D', 30),
        (28, 'single_key', 'per_day', 3, 'U', 30),
        (29, 'single_key', 'per_day', 3, 'P', 30),
        (16, 'exec', 'per_day', 3, 'successful', 5),

        (30, 'time', 'per_day', 4, null, 864000000), --4h
        (32, 'time', 'continuous_per_day', 4, null, 28800000), --8m
        (31, 'symbol', 'per_day', 4, null, 600),
        (33, 'symbol', 'continuous_per_day', 4, null, 200),
        (34, 'single_key', 'per_day', 4, 'Ctrl', 25),
        (35, 'single_key', 'per_day', 4, 'C', 30),
        (36, 'single_key', 'per_day', 4, 'V', 27),
        (37, 'single_key', 'per_day', 4, 'Shift', 17),
        (38, 'single_key', 'per_day', 4, 'F', 18),
        (39, 'single_key', 'per_day', 4, 'L', 40),
        (40, 'single_key', 'per_day', 4, 'D', 40),
        (41, 'single_key', 'per_day', 4, 'U', 40),
        (42, 'single_key', 'per_day', 4, 'P', 40),
        (43, 'single_key', 'per_day', 4, 'H', 20),
        (44, 'single_key', 'per_day', 4, 'K', 40),
        (45, 'single_key', 'per_day', 4, 'O', 40),
        (46, 'exec', 'per_day', 4, 'successful', 7),
        (47, 'exec', 'per_day', 4, 'failed', 3),

        (48, 'time', 'per_day', 5, null, 1080000000), --5h
        (50, 'time', 'continuous_per_day', 4, null, 32400000), --9m
        (49, 'symbol', 'per_day', 5, null, 610),
        (51, 'symbol', 'continuous_per_day', 4, null, 300),
        (52, 'single_key', 'per_day', 5, 'Ctrl', 26),
        (53, 'single_key', 'per_day', 5, 'C', 31),
        (54, 'single_key', 'per_day', 5, 'V', 28),
        (55, 'single_key', 'per_day', 5, 'Shift', 19),
        (56, 'single_key', 'per_day', 5, 'F', 16),
        (57, 'single_key', 'per_day', 5, 'L', 44),
        (58, 'single_key', 'per_day', 5, 'D', 40),
        (59, 'single_key', 'per_day', 5, 'U', 43),
        (60, 'single_key', 'per_day', 5, 'P', 45),
        (61, 'single_key', 'per_day', 5, 'H', 48),
        (62, 'single_key', 'per_day', 5, 'K', 46),
        (63, 'single_key', 'per_day', 5, 'O', 49),
        (64, 'single_key', 'per_day', 5, 'Z', 46),
        (65, 'single_key', 'per_day', 5, 'N', 49),
        (66, 'exec', 'per_day', 5, 'successful', 8),
        (67, 'exec', 'per_day', 5, 'failed', 5),
        (68, 'commit', 'per_day', 5, 'successful', 1),

        (69, 'time', 'per_life', 6, null, 1944000000), --9h
        (71, 'time', 'continuous_per_day', 6, null, 36000000), --10m
        (90, 'lang_time', 'per_day', 6, 'Java', 7200000), --2m
        (70, 'symbol', 'per_day', 6, null, 910),
        (72, 'symbol', 'continuous_per_day', 6, null, 400),
        (91, 'lang_symbol', 'per_day', 6, 'Java', 300),
        (73, 'single_key', 'per_day', 6, 'Ctrl', 36),
        (74, 'single_key', 'per_day', 6, 'C', 31),
        (75, 'single_key', 'per_day', 6, 'V', 28),
        (76, 'single_key', 'per_day', 6, 'Shift', 19),
        (77, 'single_key', 'per_day', 6, 'F', 16),
        (78, 'single_key', 'per_day', 6, 'L', 44),
        (79, 'single_key', 'per_day', 6, 'D', 48),
        (80, 'single_key', 'per_day', 6, 'U', 49),
        (81, 'single_key', 'per_day', 6, 'P', 45),
        (82, 'single_key', 'per_day', 6, 'H', 48),
        (83, 'single_key', 'per_day', 6, 'K', 46),
        (84, 'single_key', 'per_day', 6, 'E', 49),
        (85, 'single_key', 'per_day', 6, 'W', 46),
        (86, 'single_key', 'per_day', 6, 'N', 49),
        (87, 'exec', 'per_day', 6, 'successful', 9),
        (88, 'exec', 'per_day', 6, 'failed', 6),
        (89, 'commit', 'per_day', 6, 'successful', 3),

        (92, 'time', 'per_life', 7, null, 2160000000), --10h
        (94, 'time', 'continuous_per_life', 7, null, 64800000), --13m
        (113, 'lang_time', 'per_life', 7, 'Java', 108000000), --30m
        (93, 'symbol', 'per_life', 7, null, 2000),
        (95, 'symbol', 'continuous_per_life', 7, null, 500),
        (114, 'lang_symbol', 'per_life', 7, 'Java', 500),
        (96, 'single_key', 'per_day', 7, 'Ctrl', 38),
        (97, 'single_key', 'per_day', 7, 'C', 39),
        (98, 'single_key', 'per_day', 7, 'V', 99),
        (99, 'single_key', 'per_day', 7, 'Shift', 39),
        (100, 'single_key', 'per_day', 7, 'F', 36),
        (101, 'single_key', 'per_day', 7, 'L', 54),
        (102, 'single_key', 'per_day', 7, 'D', 78),
        (103, 'single_key', 'per_day', 7, 'U', 109),
        (104, 'single_key', 'per_day', 7, 'P', 55),
        (105, 'single_key', 'per_day', 7, 'H', 58),
        (106, 'single_key', 'per_day', 7, 'K', 56),
        (107, 'single_key', 'per_day', 7, 'E', 59),
        (108, 'single_key', 'per_day', 7, 'W', 56),
        (109, 'single_key', 'per_day', 7, 'N', 59),
        (110, 'exec', 'per_life', 7, 'successful', 19),
        (111, 'exec', 'per_life', 7, 'failed', 16),
        (112, 'commit', 'per_day', 7, 'successful', 4),

        (115, 'time', 'per_life', 8, null, 2808000000), --13h
        (117, 'time', 'continuous_per_life', 8, null, 50400000), --14m
        (122, 'lang_time', 'per_life', 8, 'Java', 648000000), --3h
        (116, 'symbol', 'per_life', 8, null, 2500),
        (118, 'symbol', 'continuous_per_life', 8, null, 510),
        (119, 'exec', 'per_life', 8, 'successful', 29),
        (120, 'exec', 'per_life', 8, 'failed', 26),
        (121, 'commit', 'per_life', 8, 'successful', 14),
        (123, 'lang_symbol', 'per_life', 8, 'Java', 1500),

        (124, 'time', 'per_life', 9, null, 4320000000), --20h
        (125, 'symbol', 'per_life', 9, null, 5500),
        (126, 'exec', 'per_life', 9, 'successful', 49),
        (127, 'exec', 'per_life',  9, 'failed', 36),
        (128, 'commit', 'per_life', 9, 'successful', 34),

        (129, 'time', 'per_life', 10, null, 5400000000), --25h
        (134, 'time', 'continuous_per_day', 10, null, 54000000), --15m
        (130, 'symbol', 'per_life', 10, null, 5800),
        (135, 'symbol', 'continuous_per_day', 10, null, 610),
        (131, 'exec', 'per_life', 10, 'successful', 67),
        (132, 'exec', 'per_life',  10, 'failed', 37),
        (133, 'commit', 'per_life', 10, 'successful', 35);