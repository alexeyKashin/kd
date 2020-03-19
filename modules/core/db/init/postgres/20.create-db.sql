-- begin KD_KD_EXCHANGE
alter table KD_KD_EXCHANGE add constraint FK_KD_EXCHANGE_ON_AUTHOR foreign key (AUTHOR_ID) references SEC_USER(ID)^
alter table KD_KD_EXCHANGE add constraint FK_KD_EXCHANGE_ON_RECEIVER foreign key (RECEIVER_ID) references SEC_USER(ID)^
create index IDX_KD_EXCHANGE_ON_AUTHOR on KD_KD_EXCHANGE (AUTHOR_ID)^
create index IDX_KD_EXCHANGE_ON_RECEIVER on KD_KD_EXCHANGE (RECEIVER_ID)^
-- end KD_KD_EXCHANGE
-- begin KD_TASK
alter table KD_TASK add constraint FK_KD_TASK_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
alter table KD_TASK add constraint FK_KD_TASK_ON_INITIATOR foreign key (INITIATOR_ID) references SEC_USER(ID)^
create index IDX_KD_TASK_ON_USER on KD_TASK (USER_ID)^
create index IDX_KD_TASK_ON_INITIATOR on KD_TASK (INITIATOR_ID)^
-- end KD_TASK
