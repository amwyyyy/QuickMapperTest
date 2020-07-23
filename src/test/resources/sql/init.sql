CREATE TABLE sys_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(32) NOT NULL COMMENT '用户名',
  password varchar(128) NOT NULL COMMENT '密码',
  mobile varchar(32) DEFAULT NULL COMMENT '手机',
  deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
);