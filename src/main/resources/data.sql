INSERT INTO tutorials(id, title, description, published) VALUES (1, 'Angular 12 Tutorial', 'This is angular tutorial series.', true);
INSERT INTO tutorials(id, title, description, published) VALUES (2, '.Net Core Web API Tutorial', 'This is .net core tutorial series.', true);
INSERT INTO tutorials(id, title, description, published) VALUES (3, 'Spring Boot MVC Tutorial', 'This is spring boot tutorial series.', true);
INSERT INTO tutorials(id, title, description, published) VALUES (4, 'VueJS 3 Tutorial Series', 'This is VueJS tutorial series.', false);
INSERT INTO tutorials(id, title, description, published) VALUES (5, 'Flutter Tutorial', 'This is flutter tutorial series.', false);

INSERT INTO COMMENTS(id, content, tutorial_id) VALUES (1, 'Good article', 1);
INSERT INTO COMMENTS(id, content, tutorial_id) VALUES (2, 'Nice series', 3);
INSERT INTO COMMENTS(id, content, tutorial_id) VALUES (3, 'Thanks you for your sharing', 1);
INSERT INTO COMMENTS(id, content, tutorial_id) VALUES (4, 'Very exciting post', 2);