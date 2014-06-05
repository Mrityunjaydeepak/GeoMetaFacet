/**
 * Copyright 2012 52�North Initiative for Geospatial Open Source Software GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package tud.geometafacet.controlling;

/**
 * 
 * This class provides inital script for creating a HSQL database.
 *
 * @author Christin Henzen, Bernd Grafe. Professorship of Geoinformation Systems
 */
public class InitialDBScript {
	
	/**
	 * This methods creates a string for the basic HSQL elements 
	 * - creates database, tables, grants usage 
	 * @return
	 */
	public static String getInitalDBScript() {
		String initDB = "SET DATABASE UNIQUE NAME HSQLDB43A08E0CFC \n" +
		"SET DATABASE GC 0 \n" +
		"SET DATABASE DEFAULT RESULT MEMORY ROWS 0 \n" +
		"SET DATABASE EVENT LOG LEVEL 0 \n" +
		"SET DATABASE SQL NAMES FALSE \n" +
		"SET DATABASE SQL REFERENCES FALSE \n" +
		"SET DATABASE SQL SIZE TRUE \n" +
		"SET DATABASE SQL TYPES FALSE \n" +
		"SET DATABASE SQL TDC DELETE TRUE \n" +
		"SET DATABASE SQL TDC UPDATE TRUE \n" +
		"SET DATABASE SQL TRANSLATE TTI TYPES TRUE \n" +
		"SET DATABASE SQL CONCAT NULLS TRUE \n" +
		"SET DATABASE SQL UNIQUE NULLS TRUE \n" +
		"SET DATABASE SQL CONVERT TRUNCATE TRUE \n" +
		"SET DATABASE SQL AVG SCALE 0 \n" +
		"SET DATABASE SQL DOUBLE NAN TRUE \n" +
		"SET DATABASE TRANSACTION CONTROL LOCKS \n" +
		"SET DATABASE DEFAULT ISOLATION LEVEL READ COMMITTED \n" +
		"SET DATABASE TRANSACTION ROLLBACK ON CONFLICT TRUE \n" +
		"SET DATABASE TEXT TABLE DEFAULTS '' \n" +
		"SET FILES WRITE DELAY 500 MILLIS \n" +
		"SET FILES BACKUP INCREMENT TRUE \n" +
		"SET FILES CACHE SIZE 10000 \n" +
		"SET FILES CACHE ROWS 50000 \n" +
		"SET FILES SCALE 32 \n" +
		"SET FILES LOB SCALE 32 \n" +
		"SET FILES DEFRAG 0 \n" +
		"SET FILES NIO TRUE \n" +
		"SET FILES NIO SIZE 256 \n" +
		"SET FILES LOG TRUE \n" +
		"SET FILES LOG SIZE 50 \n" +
		"CREATE USER \"root\" PASSWORD DIGEST 'd41d8cd98f00b204e9800998ecf8427e' \n" +
		"ALTER USER \"root\" SET LOCAL TRUE \n" +
		"CREATE SCHEMA PUBLIC AUTHORIZATION DBA \n" +
		"SET SCHEMA PUBLIC \n" +
		"CREATE MEMORY TABLE PUBLIC.DETAILS(GMF_ID INTEGER PRIMARY KEY,ID VARCHAR(32768),TITLE VARCHAR(100),DESCRIPTION VARCHAR(400),TEMPORALEXTENTBEGINPOSITION VARCHAR(30),TEMPORALEXTENTENDPOSITION VARCHAR(30),KEYWORDS VARCHAR(32768)) \n" +
		"CREATE MEMORY TABLE PUBLIC.FACETS(GMF_ID INTEGER PRIMARY KEY,HIERARCHYLEVELNAME VARCHAR(50),SCENARIO VARCHAR(30),ORGANIZATION VARCHAR(100),DATATYPE VARCHAR(10),GEOGRAPHICBOUNDINGBOX VARCHAR(100),FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.TOPICS(TID INTEGER PRIMARY KEY,GMF_ID INTEGER,TOPICCATEGORY VARCHAR(30),FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.HIERARCHY(HID INTEGER PRIMARY KEY,GMF_ID INTEGER,PARENT INTEGER,FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.URL(GMF_ID INTEGER PRIMARY KEY,URL VARCHAR(32768),INFO VARCHAR(32768),SAFE VARCHAR(32768),FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.RELATEDS(GMF_ID INTEGER PRIMARY KEY,RELATEDSERVICE VARCHAR(32768),RELATEDSERVICEID VARCHAR(32768),RELATEDLAYER VARCHAR(100),FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.RELATEDDS(DSID INTEGER PRIMARY KEY,GMF_ID INTEGER,RELATEDDATASET INTEGER,FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.RELATEDPUB(PID INTEGER PRIMARY KEY,PUBID INTEGER,GMF_ID INTEGER) \n" +	
		"CREATE MEMORY TABLE PUBLIC.LINEAGE(LID INTEGER PRIMARY KEY, GMF_ID INTEGER, DESCRIPTION VARCHAR(400),FOREIGN KEY(GMF_ID) REFERENCES PUBLIC.DETAILS(GMF_ID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.PROCESSSTEPS(PSID INTEGER PRIMARY KEY, LID INTEGER, DESCRIPTION VARCHAR(400), DATETIME VARCHAR(50), RATIONALE VARCHAR(50), PROCESSOR VARCHAR(50),FOREIGN KEY(LID) REFERENCES PUBLIC.LINEAGE(LID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.PROCESSINFOS(PIID INTEGER PRIMARY KEY, PSID INTEGER, IDENTIFIER VARCHAR(100), RUNTIMEPARAMETER VARCHAR(100),FOREIGN KEY(PSID) REFERENCES PUBLIC.PROCESSSTEPS(PSID)) \n" +
		"CREATE MEMORY TABLE PUBLIC.RELATIONLINEAGESOURCE(PSID INTEGER, ID VARCHAR(32768), CODE VARCHAR(32768)) \n" +
		"CREATE MEMORY TABLE PUBLIC.RELATIONMODELS(LID INTEGER, ID VARCHAR(32768)) \n" +
		
		"CREATE INDEX IDX_LINDES ON PUBLIC.LINEAGE(DESCRIPTION) \n" + 
		"CREATE INDEX IDX_PSDES ON PUBLIC.PROCESSSTEPS(DESCRIPTION) \n" + 
		"CREATE INDEX IDX_PROC ON PUBLIC.PROCESSSTEPS(PROCESSOR) \n" + 
		"CREATE INDEX IDX_IDENT ON PUBLIC.PROCESSINFOS(IDENTIFIER) \n"+
		"CREATE INDEX IDX_SOURCE ON PUBLIC.RELATIONLINEAGESOURCE(ID) \n "+ 
		"CREATE INDEX IDX_MODELS ON PUBLIC.RELATIONMODELS(ID) \n "+ 
		"CREATE INDEX IDX_TITLE ON PUBLIC.DETAILS(TITLE) \n" +
		"CREATE INDEX IDX_DESCR ON PUBLIC.DETAILS(DESCRIPTION) \n" +
		"CREATE INDEX IDX_TBEGIN ON PUBLIC.DETAILS(TEMPORALEXTENTBEGINPOSITION) \n" +
		"CREATE INDEX IDX_TEND ON PUBLIC.DETAILS(TEMPORALEXTENTENDPOSITION) \n" +
		"CREATE INDEX IDX_KEYW ON PUBLIC.DETAILS(KEYWORDS) \n" +
		"CREATE INDEX IDX_HVL ON PUBLIC.FACETS(HIERARCHYLEVELNAME) \n" +
		"CREATE INDEX IDX_SCEN ON PUBLIC.FACETS(SCENARIO) \n" +
		"CREATE INDEX IDX_ORGA ON PUBLIC.FACETS(ORGANIZATION) \n" +
		"CREATE INDEX IDX_DATY ON PUBLIC.FACETS(DATATYPE) \n" +
		"CREATE INDEX IDX_BBOX ON PUBLIC.FACETS(GEOGRAPHICBOUNDINGBOX) \n" +
		"CREATE INDEX IDX_TOPS ON PUBLIC.TOPICS(TOPICCATEGORY) \n" +
		"CREATE VIEW PUBLIC.FINDALLIDS (GMF_ID,TITLE,DATATYPE) AS SELECT GMF_ID,TITLE,DATATYPE FROM PUBLIC.DETAILS JOIN PUBLIC.FACETS ON DETAILS.GMF_ID=FACETS.GMF_ID \n" +
		"CREATE VIEW PUBLIC.FINDALLBBOX (GMF_ID,GEOGRAPHICBOUNDINGBOX) AS SELECT GMF_ID,GEOGRAPHICBOUNDINGBOX FROM PUBLIC.FACETS \n" +
		"CREATE VIEW PUBLIC.TOPICFACETS (GMF_ID,TOPICCATEGORY,HIERARCHYLEVELNAME,SCENARIO,ORGANIZATION,DATATYPE,GEOGRAPHICBOUNDINGBOX) AS SELECT GMF_ID,TOPICCATEGORY,HIERARCHYLEVELNAME,SCENARIO,ORGANIZATION,DATATYPE,GEOGRAPHICBOUNDINGBOX FROM PUBLIC.FACETS LEFT JOIN PUBLIC.TOPICS ON FACETS.GMF_ID=TOPICS.GMF_ID \n" +
		"CREATE VIEW PUBLIC.DETAILSTOPICFACET (GMF_ID,TOPICCATEGORY,HIERARCHYLEVELNAME,SCENARIO,ORGANIZATION,DATATYPE,GEOGRAPHICBOUNDINGBOX,ID,TITLE,DESCRIPTION,TEMPORALEXTENTBEGINPOSITION,TEMPORALEXTENTENDPOSITION,KEYWORDS) AS SELECT GMF_ID,TOPICCATEGORY,HIERARCHYLEVELNAME,SCENARIO,ORGANIZATION,DATATYPE,GEOGRAPHICBOUNDINGBOX,ID,TITLE,DESCRIPTION,TEMPORALEXTENTBEGINPOSITION,TEMPORALEXTENTENDPOSITION,KEYWORDS FROM PUBLIC.DETAILS JOIN PUBLIC.TOPICFACETS ON DETAILS.GMF_ID=TOPICFACETS.GMF_ID \n" +
		"ALTER SEQUENCE SYSTEM_LOBS.LOB_ID RESTART WITH 1 \n" +
		"SET DATABASE DEFAULT INITIAL SCHEMA PUBLIC \n" +
		"GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.SQL_IDENTIFIER TO PUBLIC \n" +
		"GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.YES_OR_NO TO PUBLIC \n" +
		"GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.TIME_STAMP TO PUBLIC \n" +
		"GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.CARDINAL_NUMBER TO PUBLIC \n" +
		"GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.CHARACTER_DATA TO PUBLIC \n" +
		"GRANT DBA TO \"root\" \n" +
		"SET SCHEMA SYSTEM_LOBS \n" +
		"INSERT INTO BLOCKS VALUES(0,2147483647,0) \n" +
		"SET SCHEMA PUBLIC" ;
		return initDB;
	}
}
