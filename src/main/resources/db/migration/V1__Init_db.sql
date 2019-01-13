CREATE TABLE role
(
  id serial NOT NULL,
  type character varying(255),
  CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE user_account
(
  id serial NOT NULL,
  active boolean NOT NULL,
  first_name character varying(255),
  password character varying(255),
  sur_name character varying(255),
  role_id integer,
  username character varying(255),
  CONSTRAINT user_account_pkey PRIMARY KEY (id),
  CONSTRAINT fk4j8uoaeve853dcbl0tjd0yoq0 FOREIGN KEY (role_id)
      REFERENCES role (id)
);

CREATE TABLE test
(
  id serial NOT NULL,
  amount_questions integer,
  description character varying(255),
  duration bigint,
  name character varying(255),
  user_id integer,
  CONSTRAINT test_pkey PRIMARY KEY (id),
  CONSTRAINT fk2vjciaik087w2y362oq7db1bi FOREIGN KEY (user_id)
      REFERENCES user_account (id)
);

CREATE TABLE question
(
  id serial NOT NULL,
  text character varying(255),
  type character varying(255),
  test_id integer,
  CONSTRAINT question_pkey PRIMARY KEY (id),
  CONSTRAINT fk8hejcpbbiq1qje11346akp3uj FOREIGN KEY (test_id)
      REFERENCES test (id)
);

CREATE TABLE answer
(
  id serial NOT NULL,
  correct boolean NOT NULL,
  text character varying(255),
  question_id integer,
  CONSTRAINT answer_pkey PRIMARY KEY (id),
  CONSTRAINT fk8frr4bcabmmeyyu60qt7iiblo FOREIGN KEY (question_id)
      REFERENCES question (id)
);


CREATE TABLE result
(
  id serial NOT NULL,
  active boolean NOT NULL,
  duration bigint,
  grade integer,
  name character varying(255),
  start_test timestamp without time zone,
  test_id integer,
  user_id integer,
  CONSTRAINT result_pkey PRIMARY KEY (id),
  CONSTRAINT fk9msc4h42pspkdvuv65f3ikqsh FOREIGN KEY (user_id)
      REFERENCES user_account (id) ,
  CONSTRAINT fksyvhlvlv6k1d4gkqvu12rha0j FOREIGN KEY (test_id)
      REFERENCES test (id)
);

CREATE TABLE result_question
(
  id serial NOT NULL,
  correctness boolean NOT NULL,
  text character varying(255),
  type character varying(255),
  result_id integer,
  CONSTRAINT result_question_pkey PRIMARY KEY (id)
);

CREATE TABLE user_answer
(
  id serial NOT NULL,
  correct boolean NOT NULL,
  text character varying(255),
  question_id integer,
  result_id integer,
  user_account_id integer,
  result_question_id integer,
  CONSTRAINT user_answer_pkey PRIMARY KEY (id),
  CONSTRAINT fkm87orx1qcxqk2qhjtio98dnkq FOREIGN KEY (result_question_id)
      REFERENCES result_question (id) ,
  CONSTRAINT fkn3mpsvs6635jpoqmtaq2owfmk FOREIGN KEY (user_account_id)
      REFERENCES user_account (id) ,
  CONSTRAINT fkpsk90eok3ounaet92hku3gny1 FOREIGN KEY (question_id)
      REFERENCES question (id)
);