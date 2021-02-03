--Projeto de Banco de Dados Para Sistemas de Informação
--Grupo: Caio Wey, Giovani Henriques, Igor Lisboa, Pautércio Ramilson e Rafael Herbert
--SGBD escolhido: Oracle

--https://www.oracle.com/tools/downloads/sqldev-v192-downloads.html
--Windows 64-bit with JDK 8 included


--https://www.oracle.com/database/technologies/xe-downloads.html
--Oracle Database 18c Express Edition for Windows x64


--Colocar script abaixo antes do script do chinook:
ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

--https://raw.githubusercontent.com/lerocha/chinook-database/master/ChinookDatabase/DataSources/Chinook_Oracle.sql

--PARTE 1
SELECT * FROM ALL_INDEXES;
create or replace PROCEDURE "DROP_INDEXES_FROM_TABLE" (vNOME_OWNER  VARCHAR2,vNOME_TABELA  VARCHAR2) IS
qtd INTEGER;
BEGIN
  FOR CUR IN (SELECT OWNER,INDEX_NAME FROM ALL_INDEXES WHERE TABLE_OWNER=vNOME_OWNER AND TABLE_NAME=vNOME_TABELA) LOOP
    BEGIN
        dbms_output.put_line('limpando os indexes relacionadas a tabela');
        SELECT count(*) INTO qtd FROM ALL_INDEXES WHERE OWNER=CUR.OWNER AND INDEX_NAME=CUR.INDEX_NAME;
        IF qtd > 0 THEN
            FOR CONST IN (SELECT CONSTRAINT_NAME,OWNER,TABLE_NAME FROM ALL_CONSTRAINTS WHERE INDEX_NAME=CUR.INDEX_NAME AND OWNER=CUR.OWNER) LOOP
                BEGIN
                    dbms_output.put_line('limpando as constraints que estao relacionadas ao index');
                    SELECT count(*) INTO qtd FROM ALL_CONSTRAINTS WHERE CONSTRAINT_NAME=CONST.CONSTRAINT_NAME AND OWNER=CONST.OWNER;
                    IF qtd > 0 THEN
                        FOR REFCONST IN (SELECT OWNER,TABLE_NAME,CONSTRAINT_NAME FROM ALL_CONSTRAINTS WHERE CONSTRAINT_TYPE='R' AND R_CONSTRAINT_NAME=CONST.CONSTRAINT_NAME) LOOP
                            BEGIN
                                dbms_output.put_line('limpando chaves estrangeiras que referenciam as constraints que estao relacionadas ao index');
                                SELECT count(*) INTO qtd FROM ALL_CONSTRAINTS WHERE CONSTRAINT_NAME=REFCONST.CONSTRAINT_NAME AND OWNER=REFCONST.OWNER;
                                IF qtd > 0 THEN                                
                                    dbms_output.put_line('ALTER TABLE ' || REFCONST.OWNER || '.' || REFCONST.TABLE_NAME ||' DROP CONSTRAINT ' || REFCONST.CONSTRAINT_NAME);
                                    EXECUTE IMMEDIATE 'ALTER TABLE ' || REFCONST.OWNER || '.' || REFCONST.TABLE_NAME ||' DROP CONSTRAINT ' || REFCONST.CONSTRAINT_NAME;
                                END IF;
                            END;
                        END LOOP;

                        dbms_output.put_line('ALTER TABLE ' || CONST.OWNER || '.' || CONST.TABLE_NAME ||' DROP CONSTRAINT ' || CONST.CONSTRAINT_NAME);
                        EXECUTE IMMEDIATE 'ALTER TABLE ' || CONST.OWNER || '.' || CONST.TABLE_NAME ||' DROP CONSTRAINT ' || CONST.CONSTRAINT_NAME;
                    END IF;
                END;
            END LOOP;
            dbms_output.put_line('DROP INDEX ' || CUR.OWNER || '.' || CUR.INDEX_NAME);
            EXECUTE IMMEDIATE 'DROP INDEX ' || CUR.OWNER || '.' || CUR.INDEX_NAME;
        END IF;
    END;
  END LOOP;
EXCEPTION /*catch */
      WHEN OTHERS THEN
          /* Log your error message here.. SQLERRM has it..*/
          DBMS_OUTPUT.PUT_LINE('::FAILED WITH ERROR::'||SQLERRM);
END DROP_INDEXES_FROM_TABLE;

EXEC DROP_INDEXES_FROM_TABLE('CHINOOK','ALBUM');

SELECT a.table_name, a.column_name, a.constraint_name, c.owner, 
       -- referenced pk
       c.r_owner, c_pk.table_name r_table_name, c_pk.constraint_name r_pk
  FROM all_cons_columns a
  JOIN all_constraints c ON a.owner = c.owner
                        AND a.constraint_name = c.constraint_name
  JOIN all_constraints c_pk ON c.r_owner = c_pk.owner
                           AND c.r_constraint_name = c_pk.constraint_name
 WHERE c.constraint_type = 'R';
spool generate_table.lst
set serveroutput on size 1000000
declare
starting boolean :=true;
r_owner varchar2(30) := '&owner';
r_table_name varchar2(30) := '&table_name';
begin
dbms_output.put_line('create table '||r_owner||'.'||r_table_name||'(');
for r in (select column_name, data_type, data_length, data_precision, data_scale, data_default, nullable
from all_tab_columns
where table_name = upper(r_table_name)
and owner=upper(r_owner)
order by column_id)
loop
if starting then
starting:=false;
else
dbms_output.put_line(',');
end if;

if r.data_type='NUMBER' then
if r.data_scale is null then
dbms_output.put(r.column_name||' NUMBER('||r.data_precision||')');
else
dbms_output.put(r.column_name||' NUMBER('||r.data_precision||','||r.data_scale||')');
end if;
else if r.data_type = 'DATE' then
dbms_output.put_line(r.column_name||' DATE');
else if instr(r.data_type, 'CHAR') >0 then
dbms_output.put(r.column_name||' '||r.data_type||'('||r.data_length||')');
else
dbms_output.put(r.column_name||' '||r.data_type);
end if;
end if;
end if;
if r.data_default is not null then
dbms_output.put(' DEFAULT '||r.data_default);
end if;
if r.nullable = 'N' then
dbms_output.put(' NOT NULL ');
end if;
end loop;
dbms_output.put_line(' ); ');
end;
/

spool off
--PARTE 2
--Todo nome de artista que for inserido ou atualizado deve ser maiúsculo.
--Ao excluir todas as tracks de uma playlist, a mesma deverá ser excluída

create or replace TRIGGER UPPER_ARTIST_NAME
BEFORE INSERT OR UPDATE ON ARTIST
        FOR EACH ROW
BEGIN
--FORCA TODO NAME A SER UPPERCASE
 :NEW.NAME := UPPER(:NEW.NAME);
END;

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
DROP USER teste CASCADE;
CREATE USER teste
IDENTIFIED BY s3nh4
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

create or replace PROCEDURE DELETE_PLAYLIST_TRACK (vTRACKID  number,vPLAYLISTID  number) IS
qtd INTEGER;
BEGIN
    --DELETA TRACK
    DELETE FROM CHINOOK.PLAYLISTTRACK WHERE TRACKID=vTRACKID AND PLAYLISTID=vPLAYLISTID;
   -- RECUPERA NUMERO DE TRACKS NA PLAYLIST
   SELECT count(*) INTO qtd FROM PLAYLISTTRACK WHERE PLAYLISTID=vPLAYLISTID;
   IF(qtd=0) THEN
    DELETE FROM CHINOOK.PLAYLIST WHERE PLAYLISTID=vPLAYLISTID;
   END IF;	
END DELETE_PLAYLIST_TRACK;

GRANT EXECUTE ON CHINOOK.DELETE_PLAYLIST_TRACK TO teste;

	CONN teste/s3nh4;
EXEC CHINOOK.DELETE_PLAYLIST_TRACK(1279,1);

--4.
create or replace PROCEDURE ATUALIZA_TOTAL_INVOICE (vINVOICEID  number) IS
BEGIN
--ATUALIZA TOTAL
UPDATE CHINOOK.INVOICE SET TOTAL=(SELECT SUM(QUANTITY*UNITPRICE) FROM CHINOOK.INVOICELINE WHERE INVOICEID=vINVOICEID) WHERE INVOICEID=vINVOICEID;
END ATUALIZA_TOTAL_INVOICE;



create or replace PROCEDURE INSERE_INVOICE_TRACK (vINVOICEID  number,vTRACKID number) IS
qtd NUMBER;
BEGIN
SELECT COUNT(*) INTO qtd FROM CHINOOK.INVOICELINE WHERE  INVOICEID=vINVOICEID AND TRACKID=vTRACKID;
IF(qtd>0) THEN
raise_application_error(-20101, 'TRACK NAO PODE SER INSERIDA NO INVOICE POIS JA ESTA INSERIDA');
END IF;
INSERT INTO CHINOOK.INVOICELINE (INVOICELINEID,INVOICEID,TRACKID,UNITPRICE,QUANTITY) 
VALUES 
(
(SELECT MAX(INVOICELINEID)+1 FROM CHINOOK.INVOICELINE),
vINVOICEID,
vTRACKID,
(SELECT UNITPRICE FROM CHINOOK.TRACK WHERE TRACKID=vTRACKID),
1
);
ATUALIZA_TOTAL_INVOICE(vINVOICEID);
END INSERE_INVOICE_TRACK;



create or replace PROCEDURE REMOVE_INVOICE_TRACK (vINVOICEID  number,vTRACKID number) IS
BEGIN
DELETE FROM CHINOOK.INVOICELINE WHERE INVOICEID=vINVOICEID AND TRACKID=vTRACKID;
ATUALIZA_TOTAL_INVOICE(vINVOICEID);
END REMOVE_INVOICE_TRACK;


create or replace PROCEDURE DEFINE_QTD_INVOICE_TRACK (vINVOICEID  number,vTRACKID number,vQUANTITY number) IS
BEGIN
IF(vQUANTITY<1) THEN
raise_application_error(-20101, 'QUANTIDADE NÃO PODE SER INFERIOR A 1');
END IF;
UPDATE CHINOOK.INVOICELINE SET QUANTITY=vQUANTITY WHERE INVOICEID=vINVOICEID AND TRACKID=vTRACKID;
ATUALIZA_TOTAL_INVOICE(vINVOICEID);
END DEFINE_QTD_INVOICE_TRACK;

GRANT EXECUTE ON CHINOOK.INSERE_INVOICE_TRACK TO teste;
GRANT EXECUTE ON CHINOOK.DEFINE_QTD_INVOICE_TRACK TO teste;
GRANT EXECUTE ON CHINOOK.REMOVE_INVOICE_TRACK TO teste;

CONN teste/s3nh4;
EXEC CHINOOK.INSERE_INVOICE_TRACK(61,2055);
EXEC CHINOOK.DEFINE_QTD_INVOICE_TRACK(61,2055,4);
EXEC CHINOOK.REMOVE_INVOICE_TRACK(61,2054);
