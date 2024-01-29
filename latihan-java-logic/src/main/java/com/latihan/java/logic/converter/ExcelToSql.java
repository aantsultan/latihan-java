package com.latihan.java.logic.converter;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ExcelToSql {

    public static final String BASE_URL = "D:";

    public static void main(String[] args) {
        insert();
    }

    static void insert() {
        File input = new File(BASE_URL + "\\TPL_PromoCode-375-SajikuMSS (SG List).csv");
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = Files.newInputStream(input.toPath());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
//            String sql = "INSERT INTO md.dbo.m_warehouse (code,name,plant_code,status,created_by,created_date,modified_by,modified_date) VALUES('?1','?2','?3',1,1,DATEADD(HOUR, 7, CURRENT_TIMESTAMP),1,DATEADD(HOUR, 7, CURRENT_TIMESTAMP));\n";
            String sql = "insert into md.dbo.m_discount_sales_group (created_by, created_date, modified_by, modified_date, organization_level, organization_value, status, discount_id, sales_group_id) values (1, DATEADD(HOUR, 7, CURRENT_TIMESTAMP), 1, DATEADD(HOUR, 7, CURRENT_TIMESTAMP), '?2', '?3', 1, ?1, (select msg.sales_group_id from md.dbo.m_sales_group msg where msg.code = '?3')); \n";
            String[] resultArr = result.toString().split(",");
            StringBuilder sqlFinal = new StringBuilder();
            int counter = 0;
            int totalColumn = 3;
            for (int i = 0; i < resultArr.length / totalColumn; i++) {
                String sqlInit = sql;
                // total column
                for (int j = 0; j < totalColumn; j++) {
                    if (j == 0) {
                        sqlInit = sqlInit.replace("?1", resultArr[counter].trim());
                    } else if (j == 1) {
                        sqlInit = sqlInit.replace("?2", resultArr[counter].trim());
                    } else {
                        sqlInit = sqlInit.replace("?3", resultArr[counter].trim());
                    }
                    counter++;
                }
                sqlFinal.append(sqlInit);
            }
            sqlFinal = new StringBuilder("begin tran;\n" + sqlFinal + "--commit;\n--rollback;");
            long timestamp = System.currentTimeMillis();
            File output = new File(BASE_URL + "\\output_insert_" + timestamp + ".sql");
            OutputStream outputStream = Files.newOutputStream(output.toPath());
            outputStream.write(sqlFinal.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void update() {
        File input = new File(BASE_URL + "\\latest.txt");
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = Files.newInputStream(input.toPath());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
            String sql = "update md.dbo.m_warehouse set created_date = DATEADD(HOUR, 7, CURRENT_TIMESTAMP), modified_date = DATEADD(HOUR, 7, CURRENT_TIMESTAMP) where code = '?1' and name = '?2' and plant_code = '?3';\n";

            String[] resultArr = result.toString().split(",");
            StringBuilder sqlFinal = new StringBuilder();
            int counter = 0;
            int totalColumn = 3;
            for (int i = 0; i < resultArr.length / totalColumn; i++) {
                String sqlInit = sql;
                // total column
                for (int j = 0; j < totalColumn; j++) {
                    if (j == 0) {
                        sqlInit = sqlInit.replace("?1", resultArr[counter].trim());
                    } else if (j == 1) {
                        sqlInit = sqlInit.replace("?2", resultArr[counter].trim());
                    } else {
                        sqlInit = sqlInit.replace("?3", resultArr[counter].trim());
                    }
                    counter++;
                }
                sqlFinal.append(sqlInit);
            }
            sqlFinal = new StringBuilder("begin tran;\n" + sqlFinal + "--commit;\n--rollback;");
            long timestamp = System.currentTimeMillis();
            File output = new File(BASE_URL + "\\output_update_" + timestamp + ".sql");
            OutputStream outputStream = Files.newOutputStream(output.toPath());
            outputStream.write(sqlFinal.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void select(){
        File input = new File(BASE_URL + "\\latest.txt");
        List<String> resultList = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = Files.newInputStream(input.toPath());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
            String sql = "select mw.code, mw.name, mw.plant_code from md.dbo.m_warehouse mw where mw.code in (?1);";
            String[] resultArr = result.toString().split(",");
            String sqlFinal = "";
            int counter = 0;
            int totalColumn = 3;
            for (int i = 0; i < resultArr.length / totalColumn; i++) {
                // total column
                for (int j = 0; j < totalColumn; j++) {
                    if (j == 0) {
                        resultList.add("'" + resultArr[counter].trim() + "'");
                    }
                    counter++;
                }
            }
            sqlFinal = sql.replace("?1", String.join(",", resultList));
            long timestamp = System.currentTimeMillis();
            File output = new File(BASE_URL + "\\output_select_" + timestamp + ".sql");
            OutputStream outputStream = Files.newOutputStream(output.toPath());
            outputStream.write(sqlFinal.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
