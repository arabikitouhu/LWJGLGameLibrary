package org.arabikitouhu.common;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogStream {

	public static Logger parent = null;

	protected Logger log;

	public LogStream(String name) {
		log = Logger.getLogger("Client ClassLoader");
		if(parent != null) {
			log.setParent(parent);
		} else {

			parent = Logger.getLogger("Root");
			parent.setUseParentHandlers(false);	//親に通知しない

				//コンソールハンドラの作成＆設定
				ConsoleHandler handler = new ConsoleHandler();
				handler.setFormatter(new CustomLogFormatter());
				handler.setLevel(Level.ALL);
				parent.setLevel(Level.ALL);
				parent.addHandler(handler);

			log.setParent(parent);
		}
	}

	public Logger GetStream() { return this.log; }

	class CustomLogFormatter extends Formatter {

		@Override
		public String format(LogRecord record) {
			//時間の取得
			long millis = record.getMillis();
			String time = String.format("%tD %<tT.%<tL", millis);

			//ログ名の取得
			String name = record.getLoggerName();

			//ログLevelの取得
			String level = record.getLevel().getLocalizedName();

			//メッセージの取得
			String message = formatMessage(record);

			return String.format("%s (%s) %s： %s\n", time, name, level, message);
		}

	}
}
//	private static LogStream mainStream = null;
//
//	public static LogStream GetMainStream() {
//		if(mainStream == null) {
//			mainStream = new LogStream(WindowStateObject.IsDebug(), "MAIN", "./data/gamelog.txt");
//		}
//		return mainStream;
//	}
//
//
//
//	protected boolean m_UseDebugMode;
//	protected String m_Name;
//
//	protected BufferedOutputStream m_Stream;
//
//	public LogStream(boolean isDebug, String name, LogStream logStream) {
//		m_UseDebugMode = isDebug;
//		m_Name = name;
//		m_Stream = logStream.m_Stream;
//	}
//
//	public LogStream(boolean isDebug, String name, String filename) {
//		m_UseDebugMode = isDebug;
//		m_Name = name;
//		try {
//			m_Stream = new BufferedOutputStream(new FileOutputStream(filename));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void Close() {
//		try {
//			m_Stream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void Info(String message) {
//		if(m_UseDebugMode) {
//			InputStream(m_Name, "INFO", message);
//		}
//	}
//
//	public void Warning(String message) {
//		if(m_UseDebugMode) {
//			InputStream(m_Name, "WARNING", message);
//		}
//	}
//
//	public void InputStream(String name, String level, String message) {
//
//		String time = String.format("%tD %<tT.%<tL", System.currentTimeMillis());
//		try {
//			m_Stream.write(String.format("%s (%s) %s： %s\n", time, name, level, message).getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

