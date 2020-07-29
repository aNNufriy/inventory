insert into ansible_user (uuid, username) values ('33013f57-5b22-47f7-bfe4-da3797825f5c','mike1');
insert into ansible_user (uuid, username) values ('55fac587-07e7-43e0-a723-2f9f59427d6b','mike2');
insert into ansible_user (uuid, username) values ('37353d9d-4846-4f32-a31c-481e9224e845','mike3');
insert into ansible_user (uuid, username) values ('6a99ddbf-a13c-40da-9764-82faf128f0d3','mike4');
insert into ansible_user (uuid, username) values ('0c7fa567-8a76-465d-afc3-7a9e28bb2cee','mike5');
insert into ansible_user (uuid, username) values ('64e27d51-dee1-4329-8d0f-4e10d15889a5','mike6');
insert into ansible_user (uuid, username) values ('e13b7c11-779b-43c6-8217-0e0173bd7a19','mike7');
insert into ansible_user (uuid, username) values ('e366ab6f-2243-4593-8a16-27915c140b44','mike8');
insert into ansible_user (uuid, username) values ('fab56c13-09b9-4ea8-9c8c-882a92db33e0','mike9');
insert into ansible_user (uuid, username) values ('844d6341-d43d-471a-885d-be66b7368837','mike10');
insert into ansible_user (uuid, username) values ('3898a7c6-64f0-4b75-8f5d-abd09755944c','mike11');
insert into ansible_user (uuid, username) values ('278a7256-ceb2-4eb8-a839-feb3978ca893','mike12');
insert into ansible_user (uuid, username) values ('dba7de8e-2ba5-4602-8cb6-ae21ae56df91','mike13');

insert into login_user (uuid,login) values ('1d242cd9-fef8-479b-a789-ffef69ee6da5','host1');
insert into login_user (uuid,login) values ('c0010ac3-6327-4046-b457-bc44c11f8af6','host2');
insert into login_user (uuid,login) values ('381dfcf4-759b-47b4-9812-8960276d52dd','host3');
insert into login_user (uuid,login) values ('dceba7f2-5e25-40e8-bec8-e425b9d7c3e0','host4');
insert into login_user (uuid,login) values ('cfa9425f-b2ec-4a5c-a663-aab679b9b3ee','host5');

insert into login_user_group (uuid, group_name) values ('9bbfd8d8-6a0e-44ce-bb43-8dbd723befbf','group1');
insert into login_user_group (uuid, group_name) values ('a88a641d-7e52-4a21-96b3-3b11f022f50c','group2');

insert into login_user_groups (login_users_uuid, groups_uuid) values ('1d242cd9-fef8-479b-a789-ffef69ee6da5','9bbfd8d8-6a0e-44ce-bb43-8dbd723befbf');
insert into login_user_groups (login_users_uuid, groups_uuid) values ('c0010ac3-6327-4046-b457-bc44c11f8af6','9bbfd8d8-6a0e-44ce-bb43-8dbd723befbf');

insert into login_user_groups (login_users_uuid, groups_uuid) values ('381dfcf4-759b-47b4-9812-8960276d52dd','a88a641d-7e52-4a21-96b3-3b11f022f50c');
insert into login_user_groups (login_users_uuid, groups_uuid) values ('dceba7f2-5e25-40e8-bec8-e425b9d7c3e0','a88a641d-7e52-4a21-96b3-3b11f022f50c');
insert into login_user_groups (login_users_uuid, groups_uuid) values ('cfa9425f-b2ec-4a5c-a663-aab679b9b3ee','a88a641d-7e52-4a21-96b3-3b11f022f50c');