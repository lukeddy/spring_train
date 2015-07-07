package com.coderdream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			ResourceBundle rs = ResourceBundle.getBundle("dbutil");

			Class.forName(rs.getString("db.classname"));
			conn = DriverManager.getConnection(rs.getString("db.url"), rs.getString("db.username"), rs.getString("db.password"));
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动加载失败,堆栈轨迹如下");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接创建失败,堆栈轨迹如下");
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("数据库操作的ResultSet关闭失败,堆栈轨迹如下");
				e.printStackTrace();
			}
		}
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("数据库操作的PreparedStatement关闭失败,堆栈轨迹如下");
				e.printStackTrace();
			}
		}
		close(conn);
	}

	public static void close(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
				if (conn.isClosed()) {
					System.out.println("此数据库连接已关闭-->" + conn);
				} else {
					System.out.println("此数据库连接关闭失败-->" + conn);
				}
			} catch (SQLException e) {
				System.out.println("数据库连接关闭失败,堆栈轨迹如下");
				e.printStackTrace();
			}
		}
	}

}