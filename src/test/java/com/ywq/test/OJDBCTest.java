package com.ywq.test;

import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class OJDBCTest {

    @Test
    public void callProcedureTest() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.131:1521:orcl", "study_oracle", "orcl");
        //{?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
        //   {call <procedure-name>[(<arg1>,<arg2>, ...)]}
        CallableStatement cstat = conn.prepareCall("{call p1(?,?)}");
        cstat.setObject(1,7788);
        //找不到这个枚举吗 什么情况
        // 可我这里有枚举啊
        cstat.registerOutParameter(2, OracleTypes.NUMBER);
        cstat.execute();
        Object sal = cstat.getObject(2);
        System.out.println(sal);
        cstat.close();
        conn.close();
    }

    @Test
    public void callFunctionTest() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.131:1521:orcl", "study_oracle", "orcl");
        //{?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
        //   {call <procedure-name>[(<arg1>,<arg2>, ...)]}
        CallableStatement cstat = conn.prepareCall("{? = call f1(?)}");
        cstat.setObject(2,7788);
        cstat.registerOutParameter(1, OracleTypes.NUMBER);
        cstat.execute();
        Object sal = cstat.getObject(1);
        System.out.println(sal);
        cstat.close();
        conn.close();
    }
}
