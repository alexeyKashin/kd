-- begin KD_KD_EXCHANGE
create table KD_KD_EXCHANGE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    AUTHOR_ID uuid,
    STATE varchar(50),
    RECEIVER_ID uuid,
    START_DATE timestamp,
    RECEIVE_DATE timestamp,
    DOCUMENT_LINKS varchar(4000),
    PROCESS_ID varchar(255),
    EXTERNAL_ID varchar(255),
    --
    primary key (ID)
)^
-- end KD_KD_EXCHANGE
