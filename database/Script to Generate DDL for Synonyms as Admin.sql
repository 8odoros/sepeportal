REM LOCATION:   Object Management\Synonyms
REM FUNCTION:   Generate DDL for synonyms.
REM TESTED ON:  10.2.0.3, 11.1.0.6
REM PLATFORM:   non-specific
REM REQUIRES:   dba_synonyms
REM NOTES:      Prompts for synonym owner and synonym name. Wildcard accepted.
REM
REM  This is a part of the Knowledge Xpert for Oracle Administration library.
REM  Copyright (C) 2008 Quest Software
REM  All rights reserved.
REM
REM ******************** Knowledge Xpert for Oracle Administration ********************
UNDEF ENTER_OWNER_NAME
UNDEF ENTER_LINK_NAME
SET long 1000
SET serveroutput on
SET verify off lines 132

DECLARE
   v_output         CLOB          := NULL;
   v_owner          VARCHAR2 (30) := '&&ENTER_OWNER_NAME';
   v_synonym_name   VARCHAR2 (30) := '&&ENTER_SYNONYM_NAME';
BEGIN
   DBMS_OUTPUT.put_line ('DDL For Database Synonyms');

   FOR tt IN (SELECT owner, synonym_name
                FROM dba_synonyms
               WHERE owner LIKE v_owner AND synonym_name LIKE v_synonym_name)
   LOOP
      SELECT DBMS_METADATA.get_ddl ('SYNONYM', tt.synonym_name, tt.owner)
        INTO v_output
        FROM DUAL;

      DBMS_OUTPUT.put_line (v_output);
   END LOOP;
END;
/