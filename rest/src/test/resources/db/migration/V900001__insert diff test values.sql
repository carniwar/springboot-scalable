insert into diff values ('DiffControllerTest.test_compare_shouldFailNoLeft', null, 'ABCABC', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffControllerTest.test_compare_shouldFailNoRight', 'ABCABC', null, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffControllerTest.test_compare_shouldSuccess', 'ABCABC', 'ABCABC', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);