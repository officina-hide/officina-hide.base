package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報[Table information class]<br>
 * <p>テーブル情報は基盤情報の一つで、非標準の方法で生成されます。</p>
 * <p>Table information is one of the basic information and is generated by the non-standard package method.</p>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/04/05
 */
public class FD_Table implements I_FD_DB {

	/** 環境情報 */
	private FD_EnvData env;
	/** 
	 * TODO Connection汎用化時に除去
	 * データベース接続情報
	 */
	protected static Connection conn;
	
	/**
	 * コンストラクター<br>
	 * 環境情報の保存[Ensure environmental information]<br>
	 * @param env 環境情報
	 */
	public FD_Table(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * テーブル情報生成[Table information generation]<br>
	 */
	public void createTable() {
		/*
		 * テーブル情報が生成済みかどうかをチェックする。<br>
		 * 未生成の時は、外部の生成用SQL文を読み込み、テーブル生成と情報の書き込みを行う。<br>
		 * Check if the table information has been generated.<br>
		 * When it is not generated, the external SQL statement is read, the table is generated and the information is written.<br>
		 */
		if(exitTable(I_FD_Table.Table_Name) == false) {
			System.out.println("FD_Table not created!");
			//テーブル情報の生成を開始する。[Start generating table information.]
			
		}
	}

	/**
	 * TODO 汎用化予定
	 * テーブル存在確認[Check for the existence of the table.]<br>
	 * @param tableName テーブル名
	 * @return true - 存在する、false - 存在しない。<br>
	 *  true - exists, false - not exists
	 */
	private boolean exitTable(String tableName) {
		boolean chk = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connection(env);
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM information_schema.tables WHERE table_name = ?");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, tableName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

	/**
	 * TODO 汎用化予定
	 * データベース接続[Database Connection]<br>
	 * @param env 環境情報[Environment Information]
	 */
	private void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url  = new StringBuffer().append("jdbc:mysql://www.officina-hide.com:3306/FDBASE");
				conn = DriverManager.getConnection(url.toString(), "fdadmin", "fdadminqAz*01");
				System.out.println(new Date() + " : "+"Database Connected.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
