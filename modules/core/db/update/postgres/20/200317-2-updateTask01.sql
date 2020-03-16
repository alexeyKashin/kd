alter table KD_TASK add constraint FK_KD_TASK_ON_USER foreign key (USER_ID) references SEC_USER(ID);
create index IDX_KD_TASK_ON_USER on KD_TASK (USER_ID);
