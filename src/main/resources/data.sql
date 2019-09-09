INSERT INTO
  oauth_client_details (
    client_id,
    client_secret,
    resource_ids,
    scope,
    authorized_grant_types,
    authorities,
    access_token_validity,
    refresh_token_validity,
    web_server_redirect_uri
  )
VALUES
  (
    'spa-client',
    '$2a$12$/2qu.ZcymzmwH6rdrxdVGu0TRQhW4BCaBB9AIhjRWQb4YW53GCvXq',
    'petstore',
    'read,write',
    'authorization_code,implicit,check_token,refresh_token',
    'ROLE_CLIENT',
    2500,
    250000,
    'http://example.com.vn'
  );

INSERT INTO users (username,password,enabled,authorities)
    VALUES ('test1','$2b$10$HjKlMjT3Yph7wh3T30oFh.10nPzViLYb.E/JyrqGNp/6tZeyiVniW', TRUE, 'AUTHORIZED_PETSTORE_USER');
INSERT INTO users (username,password,enabled,authorities)
    VALUES ('test2','$2b$10$HjKlMjT3Yph7wh3T30oFh.10nPzViLYb.E/JyrqGNp/6tZeyiVniW', TRUE, 'AUTHORIZED_PETSTORE_ADMIN');
INSERT INTO users (username,password,enabled,authorities)
    VALUES ('test3','$2b$10$HjKlMjT3Yph7wh3T30oFh.10nPzViLYb.E/JyrqGNp/6tZeyiVniW', TRUE, 'AUTHORIZED_PETSTORE_ADMIN');