alter table KD_KD_EXCHANGE add constraint FK_KD_EXCHANGE_ON_AUTHOR foreign key (AUTHOR_ID) references SEC_USER(ID);
alter table KD_KD_EXCHANGE add constraint FK_KD_EXCHANGE_ON_RECEIVER foreign key (RECEIVER_ID) references SEC_USER(ID);
create index IDX_KD_EXCHANGE_ON_AUTHOR on KD_KD_EXCHANGE (AUTHOR_ID);
create index IDX_KD_EXCHANGE_ON_RECEIVER on KD_KD_EXCHANGE (RECEIVER_ID);
