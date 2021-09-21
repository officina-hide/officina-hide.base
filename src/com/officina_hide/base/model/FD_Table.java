package com.officina_hide.base.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.sql.FD_sql;

/**
 * テーブル情報[Table information class]<br>
 * <p>テーブル情報は基盤情報の一つで、非標準の方法で生成されます。</p>
 * <p>Table information is one of the basic information and is generated by the non-standard package method.</p>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/04/05
 */
public class FD_Table extends FD_DB implements I_FD_Table {

	/** 環境情報 */
	private FD_EnvData env;
	
	/** SQLクラス */
	FD_sql sq = new FD_sql();
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
//		/** 項目一覧設定 */
//		createItemList();
	}

	public FD_Table() {
	}

	/**
	 * コンストラクター[Constructor]<br>
	 * 環境情報の保存[Ensure environmental information]<br>
	 * XML情報からテーブル情報をセットする。<br>
	 * Set table information from XML information.<br>
	 * @author officina-hide.com
	 * @since 1.00 2021/05/06
	 * @param env 環境情報[Environment Information]
	 * @param xmlData XML情報[XML Information]
	 */
	public FD_Table(FD_EnvData env, Element xmlData) {
		//環境情報保存[Storage of environmental information]
		this.env = env;
		//XML情報読み取り
		System.out.println(xmlData.getAttribute(COLUMN_NAME));
	}
//
//	/**
//	 * テーブル項目リスト生成[Table item list generation]<br>
//	 * TODO 汎用化予定　2021/08/26
//	 * @author officine-hide.net
//	 * @since 1.00 2021/04/23
//	 * @return テーブル項目リスト[Table item list]
//	 */
//	private void createItemList() {
//		items = new FD_Items();
//		items.add(COLUMNNAME_FD_Table_ID, null, Item_Value_Type_ID);
//		items.add(COLUMNNAME_FD_Table_Name, null, Item_Value_Type_String);
//		items.add(COLUMNNAME_FD_Name, null, Item_Value_Type_String);
//		items.add(COLUMNNAME_FD_Description, null, Item_Value_Type_Text);
//		baseItemSet(items);
//	}

	/**
	 * テーブル情報生成[Table information generation]<br>
	 * <p>本メソッドが呼び出された時は、XML情報よりテーブル生成と情報登録を行う。</p>
	 * <p>When this method is called, table generation and information registration are performed from XML information.</p>
	 * @author officina-hide.com
	 * @since 2021/04/05
	 */
	public void createTable() {
		Statement stmt = null;
		try {
			File currentdir = new File("."+"/document/install/");	// TODO 環境変数化
			File xmlFile = new File(currentdir.getAbsolutePath() + "\\FD_Table.xml");	// TODO 環境変数化
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			Element xmlData = document.getDocumentElement();
//			connection(env);
			stmt = conn.createStatement();
			// TODO 生成に関するメッセージが必要(2020/05/01)
			//テーブル項目情報から項目リストを作成する。
//			FD_Items items = new FD_Items(xmlData);
			//XML情報からテーブル情報を取得する。
//			X_FD_Table table = new X_FD_Table(env, xmlData);
			
			
//			//既登録分の削除用SQL文を生成する生成する。
//			String sql = sq.createSqlStatement(env, FD_sql.DELETE_TABLE, xmlData);
//			stmt.addBatch(sql);
//			//生成用SQL文を作成する。
//			sql = sq.createSqlStatement(env, FD_sql.CREATE_TABLE, xmlData);
//			stmt.addBatch(sql);
//			stmt.executeBatch();
//			//テーブル情報登録
////			NodeList entry = xmlData.getElementsByTagName("entry");
////			NodeList datas = ((Element) entry.item(0)).getElementsByTagName("data");
////			Element data = (Element) datas.item(0);
//			sql = createEntrySQL(xmlData);
			
		} catch (ParserConfigurationException | SAXException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
		/**
		 * テーブル情報生成[Table information generate]<br>
		 * @author officine-hide.net
		 * @since 1.00 2021/09/19
		 * @param env 環境情報[Environment information]
		 */
		public void createTable(FD_EnvData env) {
			Statement stmt = null;
			try {
				connection(env);
				stmt = getConn().createStatement();
				stmt.executeUpdate(Table_Drop_SQL);
				stmt.executeUpdate(Table_Create_SQL);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose(stmt, null);
			}
		}

	/**
	 * テーブル情報登録用SQL文生成[SQL statement generation for table information registration]<br>
	 * @author officina-hide.com
	 * @since 1.00 2021/05/02
	 * @param xmlData[XML Information]
	 * @param sql SQLステートメント[SQL Statement]
	 */
	private String createEntrySQL(Element xmlData) {
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlItem = new StringBuffer();
		NamedNodeMap data = xmlData.getAttributes();
		for(int ix =0; ix < data.getLength(); ix++) {
			System.out.println(data.item(ix).getNodeName()+":"+data.item(ix).getNodeValue());
			if(data.item(ix).getNodeName().equals("table")) {
				sql.append("INSERT INTO ").append(data.item(ix).getNodeValue()).append(" SET ");
			} else {
				sqlItem.append(data.item(ix).getNodeName()).append(" = ").append(data.item(ix).getNodeValue()).append(" ");
			}
		}
		return null;
	}

	/**
	 * ファイルからデータを挿入する。[Insert data from a file.]<br>
	 * @author officine-hide.com
	 * @since 1.00 2021/04/19
	 * @param dataFileName 挿入ファイル[Insert data file]
	 */
	private void fileDataImport(String dataFileName) {
		try {
			File currentdir = new File("."+"/document/install/");
			File dataFile = new File(currentdir.getAbsolutePath() + "\\" + dataFileName);
			FileInputStream fs = new FileInputStream(dataFile);
			Properties prop = new Properties();
			prop.load(fs);
			//テーブル項目リストからデータの各項目情報をセットする。
			
		} catch (IOException e) {
			e.printStackTrace();
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
//		connection(env);
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
	 * テーブル毎の登録処理を行う。[Perform registration processing for each table.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/19
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void addData(FD_EnvData env, String tableName) {
		switch(tableName) {
		case Table_Name:
			add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
			break;
		case I_FD_Numbering.Table_Name:
			FD_Numbering num = new FD_Numbering();
			num.add(env, 0, Table_ID, 101, 110);
			break;
		case I_FD_DataDictionary.Table_Name:
			FD_DataDictionary dd = new FD_DataDictionary();
			dd.add(env, 0, COLUMNNAME_FD_Table_Name, NAME_FD_Table_Name, COMMENT_FD_Table_Name);
			break;
		}
	}

	/**
	 * テーブル情報登録[Table information registration]<br>
	 * @author officina-hide.net
	 * @since 2021/09/20
	 * @param env 環境情報[Enfironment information]
	 * @param tableId テーブル情報ID[Table information ID]
	 * @param tableName テーブル識別名[Table distinguished name]
	 * @param name 表示名[display name]
	 * @param description 解説[description]
	 */
	public void add(FD_EnvData env, long tableId, String tableName, String name, String description) {
		X_FD_Table table = new X_FD_Table(env, 0);
		table.setFD_Table_ID(tableId);
		table.setFD_Table_Name(tableName);
		table.setFD_Name(name);
		table.setFD_Description(description);
		table.setFD_Group_ID(SYSTEM_GROUP_ID);
		table.save(env);		
	}
}