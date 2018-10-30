ALTER TABLE data ADD COLUMN newcolumn VARCHAR(255);

INSERT INTO data (newcolumn) VALUES
 ('foo2'),
 ('bar3'),
 ('baz4');