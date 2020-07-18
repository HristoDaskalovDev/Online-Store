CREATE TABLE IF NOT EXISTS role
(
  id         BIGSERIAL PRIMARY KEY,
  name       VARCHAR(128)             NOT NULL,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS category
(
  id         BIGSERIAL PRIMARY KEY,
  name       VARCHAR(128)             NOT NULL,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS sub_category
(
  id          BIGSERIAL PRIMARY KEY,
  name        VARCHAR(128)             NOT NULL,
  category_id BIGINT                   NOT NULL REFERENCES category (id),
  created_ts  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS user_picture
(
  id         BIGSERIAL PRIMARY KEY,
  name       VARCHAR(128)             NOT NULL,
  path       VARCHAR(128)             NOT NULL,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "user"
(
  id                 BIGSERIAL PRIMARY KEY,
  username           VARCHAR(128)             NOT NULL,
  email              VARCHAR(128)             NOT NULL,
  password           VARCHAR(128)             NOT NULL,
  phone              VARCHAR(16),
  role_id            BIGINT                   NOT NULL REFERENCES role (id),
  profile_picture_id BIGINT REFERENCES user_picture (id),
  created_ts         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS item
(
  id              BIGSERIAL PRIMARY KEY,
  name            VARCHAR(128)             NOT NULL,
  merchant_id     BIGINT                   NOT NULL REFERENCES "user" (id),
  stock           BIGINT                   NOT NULL DEFAULT 0,
  price           NUMERIC(6, 2)            NOT NULL,
  discount        SMALLINT                          DEFAULT 0,
  sub_category_id BIGSERIAL                NOT NULL REFERENCES sub_category (id),
  is_inactive     BOOLEAN                  NOT NULL DEFAULT FALSE,
  created_ts      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS cart
(
  id          BIGSERIAL PRIMARY KEY,
  user_id     BIGINT                   NOT NULL REFERENCES "user" (id),
  price_total NUMERIC(6, 2)            NOT NULL DEFAULT 0.00,
  created_ts  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS review
(
  id         BIGSERIAL PRIMARY KEY,
  rating     FLOAT(2)                 NOT NULL DEFAULT 0.00,
  content    VARCHAR(1024)                     DEFAULT NULL,
  item_id    BIGINT                   NOT NULL REFERENCES item (id),
  user_id    BIGINT                   NOT NULL REFERENCES "user" (id),
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS item_picture
(
  id         BIGSERIAL PRIMARY KEY,
  name       VARCHAR(128)             NOT NULL,
  path       VARCHAR(128)             NOT NULL,
  item_id    BIGINT                   NOT NULL REFERENCES item (id),
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS cart_item
(
  id         BIGSERIAL PRIMARY KEY,
  item_id    BIGINT                   NOT NULL REFERENCES item (id),
  cart_id    BIGINT                   NOT NULL REFERENCES cart (id),
  quantity   BIGINT                   NOT NULL,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "order"
(
  id               BIGSERIAL PRIMARY KEY,
  cart_item_id     BIGINT                   NOT NULL REFERENCES cart_item (id),
  user_id          BIGINT                   NOT NULL REFERENCES "user" (id),
  status           VARCHAR(128)             NOT NULL,
  delivery_address VARCHAR(128)             NOT NULL,
  created_ts       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);


