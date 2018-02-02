package com.barry.dao;

import com.barry.po.ReferencePointData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Barry on 2018/1/25.
 */
public class DbService {

/**
 *@描述  导入文件本体，存二进制文件到数据库
 *@参数  [fileName, userId] 文件名称，用户id
 *@返回值  void
 *@注意 ReferencePoint文件和ReferencePointData文件属于管理员用户的，UserData属于普通用户的
 *@创建人  Barry
 *@创建时间  2018/2/1
 *@修改人和其它信息
 */
    public void importFileItself(String fileName, String userId) {

        FileOperate fileOperater = new FileOperate();
        FileInputStream fileInputStream = fileOperater.inputStreamFile(fileName);
        String sqlUserFile = "insert into user_file values(?,?,?)";
        Connection connection = DbConnect.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlUserFile);
            ps.setString(1, userId);
            ps.setString(2, fileName);
            ps.setBinaryStream(3, fileInputStream, fileInputStream.available());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOperater.closeInputStreamFile(fileInputStream);
            DbConnect.closeConnection(null, ps, connection);
        }
    }

/**
 *@描述 读取文件内容，解析文件到数据库
 *@参数  [fileName]
 *@返回值  void
 *@注意 默认文件命名中含有文件种类，即ReferencePoint、ReferencePointData、UserData、UserPosition四种
 *@创建人  Barry
 *@创建时间  2018/2/1
 *@修改人和其它信息
 */
    public void importFileContent(String fileName) {

        FileOperate fileOperater = new FileOperate();
        BufferedReader reader = fileOperater.readFile(fileName);
        String sqlReferencePoint = "insert into reference_point values(?,?,?)";
        String sqlReferencePointData = "insert into reference_point_data values(?,?,?,?,?, ?,?,?,?,?, ?)";
        String sqlUserData = "insert into user_data values(?,?,?,?,?, ?,?,?,?,?, ?)";
        String sqlUserPosition = "insert into user_position(?,?,?)";
        String sql = null;
        Method method = null;
        Object obj = null;
        try {
            Class<DbService> c = (Class<DbService>) Class.forName("com.barry.dao.DbService");
            obj = c.newInstance();
            if (fileName.matches("(.*)ReferencePoint(.*)")
                    && !fileName.matches("(.*)ReferencePointData(.*)")) {
                sql = sqlReferencePoint;
                //getDeclaredMethod可以获得类中所有方法，而getMethod只能获得public方法
                method = c.getDeclaredMethod("referencePointFormatProcess", String.class);
            } else if (fileName.matches("(.*)ReferencePointData(.*)")) {
                sql = sqlReferencePointData;
                method = c.getDeclaredMethod("referencePointDataFormatProcess", String.class);
            } else if (fileName.matches("(.*)UserData(.*)")) {
                sql = sqlUserData;
                method = c.getDeclaredMethod("userDataFormatProcess", String.class);
            } else if (fileName.matches("(.*)UserPosition(.*)")){
                sql = sqlUserPosition;
                method = c.getDeclaredMethod("userPositionFormatProcess", String.class);
            } else {
                System.out.println("文件命名不符合想定规范");
                return;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String tempString = null;
        String[] tempStrings = null;
        List<String> params = new ArrayList<String>();
        try {
            while ((tempString = reader.readLine()) != null) {
                assert method != null;
                tempStrings = (String[])method.invoke(obj, tempString);
                Collections.addAll(params, tempStrings);
                insertStringIntoTable(sql, params);
                params.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("BufferedReader的readline异常");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            fileOperater.closeReadFile(reader);
        }
    }
    //
/**
 *@描述
 *@参数  [sql, params]  [即将执行的sql script, sql script中要填入的参数其列表]
 *@返回值  void
 *@注意  一个params对应文件中一行
 *@创建人  Barry
 *@创建时间  2018/2/1
 *@修改人和其它信息
 */
    private static void insertStringIntoTable(String sql, List<String> params) {
        Connection connection = DbConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                if (params.get(i).equals("")) {
                    System.out.println("未填写参数,或有多余的空行");
                    return;
                }
                ps.setString(i + 1, params.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("PreparedStatement异常");
        } finally {
            DbConnect.closeConnection(null, ps, connection);
        }
    }

    /**
     *@描述 该方法为针对ReferencePoint种类的文件内容进行格式处理
     *@参数  [String]
     *@返回值  java.lang.String[]
     *@注意
     *@创建人  Barry
     *@创建时间  2018/2/1
     *@修改人和其它信息
     */
    private String[] referencePointFormatProcess(String String) {
        String[] Strings = null;
        Strings = String.split("\\s+");
        return Strings;
    }
    /**
     *@描述 该方法为针对ReferencePointData种类的文件内容进行格式处理
     *@参数  [String]
     *@返回值  java.lang.String[]
     *@注意
     *@创建人  Barry
     *@创建时间  2018/2/1
     *@修改人和其它信息
     */
    private String[] referencePointDataFormatProcess(String String) {
        String[] Strings = null;
        Strings = String.split("[ ;]");
        return Strings;
    }
    private String[] userDataFormatProcess(String String) {
//      待完成
        return null;
    }
    private String[] userPositionFormatProcess(String String) {
//      待完成
        return null;
    }

    public void provideReferencePointData(List<ReferencePointData> referencePointDataList) {
        Connection connection = DbConnect.getConnection();
        PreparedStatement ps = null;
        String sqlReferencePointData = "SELECT * FROM reference_point_data";
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlReferencePointData);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReferencePointData referencePointData = new ReferencePointData();
                referencePointData.setRpdId(rs.getString(1));
                referencePointData.setAP1(rs.getString(2));
                referencePointData.setRSSI1(rs.getString(3));
                referencePointData.setAP2(rs.getString(4));
                referencePointData.setRSSI2(rs.getString(5));
                referencePointData.setAP3(rs.getString(6));
                referencePointData.setRSSI3(rs.getString(7));
                referencePointData.setAP4(rs.getString(8));
                referencePointData.setRSSI4(rs.getString(9));
                referencePointData.setAP5(rs.getString(10));
                referencePointData.setRSSI5(rs.getString(11));
                referencePointDataList.add(referencePointData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnect.closeConnection(rs, ps, connection);
        }
    }
}
