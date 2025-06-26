/*
 * Copyright 2020 TopicQuests
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.topicquests.asr.nlp.uitil;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * @author jackpark
 *
 */
public class TextFileHandler {
	private String fName = null;
	private String body = null;
	private BufferedReader inStream = null;
	private JFileChooser chooser = null;

	public TextFileHandler() {
	}

	public File _saveAs() {
		File result = null;
		if (this.chooser == null) {
			this.chooser = new JFileChooser(new File("."));
		}

		int retVal = this.chooser.showSaveDialog((Component) null);
		if (retVal == 0) {
			result = this.chooser.getSelectedFile();
		}

		return result;
	}

	public void saveAs(String body) {
		File myFile = this._saveAs();
		if (myFile != null) {
			this.writeFile(myFile, body);
		}

	}

	public File openFile() {
		return this.openFile((String) null);
	}

	public File openFile(String title) {
		File result = null;
		JFileChooser chooser = new JFileChooser(new File("."));
		if (title != null) {
			chooser.setDialogTitle(title);
		}

		int retVal = chooser.showOpenDialog((Component) null);
		if (retVal == 0) {
			result = chooser.getSelectedFile();
		}

		return result;
	}

	public File[] openFiles(String title) {
		File[] result = null;
		JFileChooser chooser = new JFileChooser(new File("."));
		if (title != null) {
			chooser.setDialogTitle(title);
		}

		chooser.setMultiSelectionEnabled(true);
		int retVal = chooser.showOpenDialog((Component) null);
		if (retVal == 0) {
			result = chooser.getSelectedFiles();
		}

		return result;
	}

	public File openDirectory() {
		return this.openDirectory((String) null);
	}

	public File openDirectory(String title) {
		File result = null;
		JFileChooser chooser = new JFileChooser(new File("."));
		chooser.setFileSelectionMode(1);
		if (title != null) {
			chooser.setDialogTitle(title);
		}

		int retVal = chooser.showOpenDialog((Component) null);
		if (retVal == 0) {
			result = chooser.getSelectedFile();
		}

		return result;
	}

	public String readFile(String fileName) {
		File f = new File(fileName);
		this.fName = fileName;
		return this.readFile(f);
	}

	public String readFile(File f) {
		int size = (int) f.length();
		this.body = null;

		try {
			FileInputStream in = new FileInputStream(f);
			byte[] data = new byte[size];
			in.read(data, 0, size);
			this.body = new String(data);
			in.close();
		} catch (IOException var6) {
			System.out.println("Error: TextFileHandler couldn\'t read from " + f + "\n");
		}

		return this.body;
	}

	public String readFile16(File f) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStreamReader in = null;

		try {
			in = new InputStreamReader(new FileInputStream(f), "UTF-16");
			BufferedReader reader = new BufferedReader(in);

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} finally {
			in.close();
		}

		return sb.toString();
	}

	public void writeFile(String fileName, String inBody) {
		File f = new File(fileName);
		this.fName = fileName;
		this.writeFile(f, inBody);
	}

	public void writeFile(File f, String inBody) {
		int size = inBody.length();
		byte[] data = inBody.getBytes();

		try {
			FileOutputStream out = new FileOutputStream(f);
			out.write(data, 0, size);
			out.flush();
			out.close();
		} catch (IOException var7) {
			System.out.println("Error: TextFileHandler couldn\'t write to " + this.fName + "\n");
		}

	}

	public String readFirstLine(String fileName) {
		File f = new File(fileName);
		return this.readFirstLine(f);
	}

	public String readFirstLine(File f) {
		this.fName = f.getName();

		try {
			FileInputStream in = new FileInputStream(f);
			this.inStream = new BufferedReader(new InputStreamReader(in));
		} catch (IOException var3) {
			System.out.println("Error: TextFileHandler couldn\'t open a DataInputStream on " + this.fName + "\n");
		}

		return this.readNextLine();
	}

	public String readNextLine() {
		String str = null;

		try {
			str = this.inStream.readLine();
		} catch (IOException var3) {
			System.out.println("Error: TextFileHandler couldn\'t read from " + this.fName + "\n");
		}

		return str;
	}

	public void persist(String fileName, Object obj) {
		try {
			(new ObjectOutputStream(new FileOutputStream(new File(fileName)))).writeObject(obj);
		} catch (Exception var4) {
			throw new RuntimeException(var4);
		}
	}

	public Object restore(String fileName) {
		Object result = null;

		try {
			result = (new ObjectInputStream(new FileInputStream(new File(fileName)))).readObject();
		} catch (Exception var4) {
			System.out.println("Restoring " + fileName);
		}

		return result;
	}

	public void saveGZipFile(String fileName, String content) {
		try {
			GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(fileName));
			PrintWriter pw = new PrintWriter(out);
			pw.write(content);
			pw.flush();
			pw.close();
		} catch (Exception var5) {
			System.out.println(var5.getMessage());
		}

	}

	public PrintWriter getGZipWriter(String fileName) throws Exception {
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(fileName));
		return new PrintWriter(out);
	}

	public void saveGZipFile(File outFile, String content) throws Exception {
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outFile));
		PrintWriter pw = new PrintWriter(out);
		pw.write(content);
		pw.flush();
		pw.close();
	}

	public String openGZipFile(String fileName) {
		try {
			GZIPInputStream in = new GZIPInputStream(new FileInputStream(fileName));
			StringBuffer buf = new StringBuffer();
			byte[] b = new byte[1024];

			while (in.read(b) > 0) {
				String s = new String(b);
				buf.append(s);
			}

			return buf.toString().trim();
		} catch (Exception var7) {
			System.out.println(var7.getMessage());
			return null;
		}
	}

	public GZIPInputStream getGZipInputStream(FileInputStream fis) throws IOException {
		return new GZIPInputStream(fis);
	}
}
