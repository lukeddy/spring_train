package com.coderdream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSetWriter;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.dataset.xml.XmlDataSetWriter;
import org.dbunit.dataset.xml.XmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.FileHelper;

public class DBExportImport {
	private static String TEST_DIR = DBExportImport.class.getResource("/").getPath();

	public static void main(String[] args) throws Exception {
		File file = new File(TEST_DIR + "backup.xml");

		Connection connection = DBUtil.getConnection();
		String[] tableNames = new String[] { "student" };
		// 导出指定的表的数据到xml文件
		//exportTables(connection, tableNames, file);
		// 导出所有表的数据到xml文件
		// exportAllTables(file, connection);
		// 将xml数据文件中的数据导入到数据库中
		importData(file, connection);
	}

	/**
	 * <pre>
	 * 导出数据到指定文件
	 * 
	 * 在这个方法中指定了一个表名"room"，如果有多个表可以通过参数或其他的方式按照这种方式继续增加。
	 * 这个文件是XML格式的。
	 * 具体格式说明或其他格式参见http://www.dbunit.org/components.html
	 * 
	 * 
	 * </pre>
	 * 
	 * @param connection
	 *            一个标准的java.sql.Connection
	 * @param tableNames
	 *            需要导出数据的表名数组
	 * @param file
	 *            一个标准的java.io.File
	 * @throws Exception
	 */
	public static void exportTables(Connection connection, String[] tableNames, File file) throws Exception {
		IDatabaseConnection databaseConnection = new DatabaseConnection(connection);
		QueryDataSet dataSet = new QueryDataSet(databaseConnection);
		if (null != tableNames && 0 < tableNames.length) {
			int tableNamesLength = tableNames.length;
			for (int i = 0; i < tableNamesLength; i++) {
				dataSet.addTable(tableNames[i]);
			}
		}
		Writer writer = new FileWriter(file);
		XmlDataSetWriter w = new XmlDataSetWriter(writer);
		w.write(dataSet);
		writer.flush();
		writer.close();
	}

	/**
	 * <pre>
	 * 导出数据库中的所有数据到指定文件
	 * 这个文件是XML格式的。
	 * 具体格式说明或其他格式参见http://www.dbunit.org/components.html
	 * 
	 * 这个方法可以把上面生成的XML文件导入到数据库中，
	 * 如果是其他格式的文件只需要更换IDataSetProducer的实现类就可以了。
	 * 具体格式请参见APIDOC在这个方法里使用了事务控制，保证数据的一致性。
	 * 
	 * </pre>
	 * 
	 * @param file
	 *            一个标准的java.io.File
	 * @param connection
	 *            一个标准的java.sql.Connection
	 * @throws Exception
	 */
	public static void exportAllTables(File file, Connection connection) throws Exception {
		IDatabaseConnection databaseConnection = new DatabaseConnection(connection);
		IDataSet dataSet = databaseConnection.createDataSet();
		Writer writer = new FileWriter(file);
		XmlDataSetWriter w = new XmlDataSetWriter(writer);
		w.write(dataSet);
		writer.flush();
		writer.close();
	}

	/**
	 * CsvDataSetWriter
	 * 
	 * <pre>
	 * 导出数据库中的所有数据到指定文件
	 * 这个文件是XML格式的。
	 * 具体格式说明或其他格式参见http://www.dbunit.org/components.html
	 * 
	 * 这个方法可以把上面生成的XML文件导入到数据库中，
	 * 如果是其他格式的文件只需要更换IDataSetProducer的实现类就可以了。
	 * 具体格式请参见APIDOC在这个方法里使用了事务控制，保证数据的一致性。
	 * 
	 * </pre>
	 * 
	 * @param file
	 *            一个标准的java.io.File
	 * @param connection
	 *            一个标准的java.sql.Connection
	 * @throws Exception
	 */
	public static void exportAllTablesToCsv(Connection connection, File file) throws Exception {
		IDatabaseConnection databaseConnection = new DatabaseConnection(connection);
		IDataSet dataSet = databaseConnection.createDataSet();
		CsvDataSetWriter w = new CsvDataSetWriter(file);
		w.write(dataSet);
	}

	/**
	 * 导入数据到数据库
	 * 
	 * @param file
	 *            一个标准的java.io.File
	 * @param connection
	 *            一个标准的java.sql.Connection
	 */
	public static void importData(File file, Connection connection) throws DatabaseUnitException, IOException, SQLException {
		IDataSetProducer dataSetProducer = new XmlProducer(FileHelper.createInputSource(file));
		IDataSet dataSet = new CachedDataSet(dataSetProducer);
		IDatabaseConnection databaseConnection = new DatabaseConnection(connection);
		DatabaseOperation operation = DatabaseOperation.CLEAN_INSERT;
		DatabaseOperation.TRANSACTION(operation);
		operation.execute(databaseConnection, dataSet);
		DatabaseOperation.CLOSE_CONNECTION(operation);
	}
	

}