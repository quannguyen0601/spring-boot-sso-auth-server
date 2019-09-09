drop table oauth_client_details if exists;
create table oauth_client_details (
    client_id varchar(256) primary key,
    resource_ids varchar(256),
    client_secret varchar(256),
    scope varchar(256),
    authorized_grant_types varchar(256),
    web_server_redirect_uri varchar(256),
    authorities varchar(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information varchar(4096),
    autoapprove varchar(256),

);

drop table oauth_access_token if exists;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

drop table oauth_refresh_token if exists;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);


create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication LONGVARBINARY
);

create table if not exists oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

drop table users if exists;
create table users(
    username varchar_ignorecase(256) not null primary key,
    password varchar_ignorecase(256) not null,
    enabled boolean not null default true,
    authorities varchar(2500) not null
);

