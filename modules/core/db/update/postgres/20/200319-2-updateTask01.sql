alter table KD_TASK add constraint FK_KD_TASK_ON_INITIATOR foreign key (INITIATOR_ID) references SEC_USER(ID);
create index IDX_KD_TASK_ON_INITIATOR on KD_TASK (INITIATOR_ID);
