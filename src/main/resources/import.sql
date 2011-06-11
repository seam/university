insert into IdentityRoleName(id, name) values (1, 'admin');
insert into IdentityRoleName(id, name) values (2, 'manager');

insert into IdentityObjectType(id, name) values (1, 'USER');
insert into IdentityObjectType(id, name) values (2, 'GROUP');

insert into IdentityObject (id, name, identity_object_type_id) values (1, 'shane', 1);
insert into IdentityObject (id, name, identity_object_type_id) values (2, 'demo', 1);
insert into IdentityObject (id, name, identity_object_type_id) values (3, 'Head Office', 2);
insert into IdentityObject (id, name, identity_object_type_id) values (4, 'foo', 1);

insert into IdentityObjectCredentialType (id, name) values (1, 'PASSWORD');

insert into IdentityObjectCredential (id, identity_object_id, credential_type_id, value) values (1, 1, 1, 'password');
insert into IdentityObjectCredential (id, identity_object_id, credential_type_id, value) values (2, 2, 1, 'demo');

insert into IdentityObjectRelationshipType (id, name) values (1, 'JBOSS_IDENTITY_MEMBERSHIP');
insert into IdentityObjectRelationshipType (id, name) values (2, 'JBOSS_IDENTITY_ROLE');

insert into IdentityObjectRelationship (id, name, relationship_type_id, from_identity_id, to_identity_id) values (1, 'admin', 2, 3, 2);

insert into Category (id, name, localEditor, remoteEditor, privileged) values (1, 'Article', 0, 1, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (2, 'Blog', 0, 1, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (3, 'Tip', 4, null, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (4, 'FAQ', 3, null, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (5, 'Reference Documentation', null, 1, true);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (6, 'API Documentation', null, 1, true);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (7, 'News', 0, 1, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (8, 'IRC Log', 5, 1, true);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (9, 'Video', 2, 1, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (10, 'Podcast', 5, 1, false);
insert into Category (id, name, localEditor, remoteEditor, privileged) values (11, 'Presentation', 5, 1, false);
