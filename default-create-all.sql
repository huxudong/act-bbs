create table bbs_module (
  id                            integer auto_increment not null,
  turn                          integer,
  detail                        varchar(255),
  name                          varchar(255),
  constraint pk_bbs_module primary key (id)
);

create table bbs_post (
  id                            integer auto_increment not null,
  has_reply                     integer,
  topic_id                      integer,
  content                       varchar(255),
  create_time                   datetime(6),
  update_time                   datetime(6),
  user_id                       integer,
  constraint uq_bbs_post_user_id unique (user_id),
  constraint pk_bbs_post primary key (id)
);

create table bbs_reply (
  id                            integer auto_increment not null,
  post_id                       integer,
  content                       varchar(255),
  create_time                   datetime(6),
  user_id                       integer,
  topic_id                      integer,
  constraint uq_bbs_reply_user_id unique (user_id),
  constraint uq_bbs_reply_topic_id unique (topic_id),
  constraint pk_bbs_reply primary key (id)
);

create table bbs_topic (
  id                            integer auto_increment not null,
  emotion                       integer,
  is_nice                       integer,
  is_up                         integer,
  post_count                    integer,
  pv                            integer,
  reply_count                   integer,
  content                       varchar(255),
  create_time                   datetime(6),
  user_id                       integer,
  module_id                     integer,
  constraint uq_bbs_topic_user_id unique (user_id),
  constraint uq_bbs_topic_module_id unique (module_id),
  constraint pk_bbs_topic primary key (id)
);

create table bbs_user (
  id                            integer auto_increment not null,
  level                         integer,
  score                         integer,
  balance                       integer,
  password                      varchar(255),
  email                         varchar(255),
  user_name                     varchar(255),
  corp                          varchar(255),
  constraint pk_bbs_user primary key (id)
);

alter table bbs_post add constraint fk_bbs_post_user_id foreign key (user_id) references bbs_user (id) on delete restrict on update restrict;

alter table bbs_reply add constraint fk_bbs_reply_post_id foreign key (post_id) references bbs_post (id) on delete restrict on update restrict;
create index ix_bbs_reply_post_id on bbs_reply (post_id);

alter table bbs_reply add constraint fk_bbs_reply_user_id foreign key (user_id) references bbs_user (id) on delete restrict on update restrict;

alter table bbs_reply add constraint fk_bbs_reply_topic_id foreign key (topic_id) references bbs_topic (id) on delete restrict on update restrict;

alter table bbs_topic add constraint fk_bbs_topic_user_id foreign key (user_id) references bbs_user (id) on delete restrict on update restrict;

alter table bbs_topic add constraint fk_bbs_topic_module_id foreign key (module_id) references bbs_module (id) on delete restrict on update restrict;

