package org.gan;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * @author ganxinming
 * @createDate 2021/1/19
 * @description
 */
public class copyTest {

	public static void main(String[] args) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("我马上就要去剪切板了"), null);
	}
}
