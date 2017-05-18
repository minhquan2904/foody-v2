package com.example.minhquan.foodyv1.Database;

/**
 * Created by MinhQuan on 4/18/2017.
 */

public class CreateDatabase {
    public static String sql = "BEGIN TRANSACTION;\n" +
            "CREATE TABLE IF NOT EXISTS \"TYPE\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"NAME\" TEXT, \"IMG\" TEXT, \"CATEGORYID\" INTEGER);\n" +
            "CREATE TABLE IF NOT EXISTS \"STREET\" (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`NAME`\tTEXT,\n" +
            "\t`CITYID`\tINTEGER,\n" +
            "\t`DISTRICTID`\tINTEGER\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS \"ITEM\" (\n" +
            "\t`ID`\tINTEGER NOT NULL,\n" +
            "\t`NAME`\tTEXT,\n" +
            "\t`ADDRESS`\tTEXT,\n" +
            "\t`CITYID`\tINTEGER,\n" +
            "\t`DISTRICTID`\tINTEGER,\n" +
            "\t`STREETID`\tINTEGER,\n" +
            "\t`TOTALREVIEWS`\tINTEGER,\n" +
            "\t`IMG`\tTEXT,\n" +
            "\t`AVGRATING`\tDOUBLE DEFAULT (null),\n" +
            "\t`CATEGORYID`\tINTEGER DEFAULT (null),\n" +
            "\t`TYPEID`\tINTEGER DEFAULT (null),\n" +
            "\t`TOTALPICTURES`\tINTEGER DEFAULT (null),\n" +
            "\t`RESTAURANTID`\tINTEGER DEFAULT (null),\n" +
            "\tPRIMARY KEY(`ID`)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS \"DISTRICT\" (\n" +
            "\t`ID`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`NAME`\tTEXT NOT NULL,\n" +
            "\t`CITYID`\tINTEGER\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS \"CITY\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"NAME\" TEXT);\n" +
            "CREATE TABLE IF NOT EXISTS \"CATEGORYTYPE\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"NAME\" TEXT);\n" +
            "CREATE TABLE IF NOT EXISTS CATEGORY(IDCATEGORY INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,IMG TEXT );\n" +
            "COMMIT;\n";
}
