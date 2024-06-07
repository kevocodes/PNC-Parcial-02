
ALTER TABLE public."sec01_users" ALTER COLUMN active SET DEFAULT true;

ALTER TABLE public."sec01_tokens" ALTER COLUMN active SET DEFAULT true;
ALTER TABLE public."sec01_tokens" ALTER COLUMN "timestamp" SET DEFAULT CURRENT_TIMESTAMP;

INSERT INTO "sec01_roles" VALUES ('USER', 'user') ON CONFLICT (id) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_roles" VALUES ('LBRN', 'librarian') ON CONFLICT (id) DO UPDATE set "name" = excluded."name";
INSERT INTO "sec01_roles" VALUES ('SUDO', 'sysadmin') ON CONFLICT (id) DO UPDATE set "name" = excluded."name";
-- INSERT INTO "sec01_roles" VALUES (̈́'USER','user') ON CONFLICT  (id) DO UPDATE SET "name" = excluded."name";
-- INSERT INTO "sec01_roles" VALUES (̈́'LBRN','librarian') ON CONFLICT  (id) DO UPDATE SET "name" = excluded."name";
-- INSERT INTO "sec01_roles" VALUES (̈́'SUDO','sysadmin') ON CONFLICT  (id) DO UPDATE SET "name" = excluded."name";