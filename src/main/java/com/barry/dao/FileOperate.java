package com.barry.dao;

import java.io.*;

/**
 * Created by Barry on 2018/1/26.
 */
public class FileOperate {
    /**
     *@描述 根据文件名返回一个用于读取该文件的BufferedReader对象
     *@参数  [fileName]
     *@返回值  java.io.BufferedReader
     *@注意
     *@创建人  Barry
     *@创建时间  2018/2/1
     *@修改人和其它信息
     */
    public BufferedReader readFile(String fileName) {
        File file = new File(fileName);
        try {
            return new BufferedReader(new FileReader(file));
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("文件读取异常");
        }
        return null;
    }
    /**
     *@描述 根据文件名返回一个用于获取该文件二进制流的FileInputStream对象
     *@参数  [fileName]
     *@返回值  java.io.FileInputStream
     *@注意
     *@创建人  Barry
     *@创建时间  2018/2/1
     *@修改人和其它信息
     */
    public FileInputStream inputStreamFile(String fileName){
        File file = new File(fileName);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件输入流异常");
        }
        return null;
    }

    /**
     *@描述 用于通过BufferedReader将文件关闭
     *@参数  [reader]
     *@返回值  void
     *@注意
     *@创建人  Barry
     *@创建时间  2018/2/1
     *@修改人和其它信息
     */
    public void closeReadFile(BufferedReader reader){
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("BufferedReader关闭异常");
            }
        }
    }
    /**
     *@描述 用于通过FileInputStream将文件关闭
     *@参数  [reader]
     *@返回值  void
     *@注意
     *@创建人  Barry
     *@创建时间  2018/2/1
     *@修改人和其它信息
     */
    public void closeInputStreamFile(FileInputStream reader){
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("FileInputStream关闭异常");
            }
        }
    }

}
