use charity_donation;
INSERT INTO role (id, name) VALUES (NULL,'ROLE_USER');
INSERT INTO role (id, name) VALUES (NULL, 'ROLE_ADMIN');

INSERT INTO users (email, firstName, lastName, username, password) VALUES ('frantz12@wp.pl', 'Jarosław', 'Frankiewicz', 'frantz', '$2y$12$pCt916J9z.ntKQne.7ArYeoVkKBV.Z4Qfoz63QQN/mDHA4JzQ5lX6');
INSERT INTO user_role (ROLE_ID, USER_ID) VALUES (2,1);
