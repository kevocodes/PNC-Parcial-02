ALTER TABLE public."user" ALTER COLUMN active SET DEFAULT true;

ALTER TABLE public."token" ALTER COLUMN active SET DEFAULT true;
ALTER TABLE public."token" ALTER COLUMN "timestamp" SET DEFAULT CURRENT_TIMESTAMP;

INSERT INTO "role" VALUES ('USER', 'user') ON CONFLICT (id) DO UPDATE set "name" = excluded."name";
INSERT INTO "role" VALUES ('LBRN', 'librarian') ON CONFLICT (id) DO UPDATE set "name" = excluded."name";
INSERT INTO "role" VALUES ('SUDO', 'sysadmin') ON CONFLICT (id) DO UPDATE set "name" = excluded."name";

INSERT INTO "specialty" VALUES ('TEST', 'testjsjsjs') ON CONFLICT DO NOTHING;
