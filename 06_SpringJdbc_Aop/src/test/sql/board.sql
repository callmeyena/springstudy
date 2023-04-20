DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ NOCACHE;

DROP TABLE BOARD;
CREATE TABLE BOARD(
    BOARD_NO NUMBER              NOT NULL,
    TITLE    VARCHAR2(1000 BYTE) NOT NULL,
    CONTENT  CLOB,
    WRITER   VARCHAR2(100 BYTE)  NOT NULL,
    CREATED_AT VARCHAR2(30 BYTE),
    MODIFIED_AT VARCHAR2(30 BYTE),
    CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)
);