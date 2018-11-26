insert into diff values ('DiffRepositoryTest.test_findById_shouldSuccess', 'ABCDEF', 'ABCDEF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

insert into diff values ('DiffServiceTest.test_findById_shouldSuccess', 'ABCDEF', 'ABCDEF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

insert into diff values ('DiffServiceTest.test_compare_shouldSuccess', 'ABCDEF', 'ABCDEF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffServiceTest.test_compare_shouldSuccessEquals', 'ABCDEF', 'ABCDEF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffServiceTest.test_compare_shouldSuccessSameSize', 'ABCDEF', '123456', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffServiceTest.test_compare_shouldSuccessFirstNoIndex', 'ABCDEF', 'ABCDEF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffServiceTest.test_compare_shouldSuccessFirstIndex3', 'ABCDEF', 'ABCABC', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

insert into diff values ('DiffServiceTest.test_saveLeft_shouldSuccessUpdate', null, 'ABCABC', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
insert into diff values ('DiffServiceTest.test_saveRight_shouldSuccessUpdate', 'ABCABC', null, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);