package net.intt.stock.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.intt.stock.FireStock;
import net.intt.util.LogManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DesktopLauncher {

	static boolean RUN = false;

	static String PATH;
	static LogManager log = new LogManager(FireStock.sgme_name);

	public static void main (String[] arg) {
		init();
		if (RUN) {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
			config.width = res.width;
			config.height = res.height;
			new LwjglApplication(new FireStock(PATH), config);
		} else {
			Gui ui = new Gui();
			ui.error();
		}
	}

	static void init() {
		if (OS.isMac()) {
			File file = new File(System.getProperty("user.home") + "/Library/Application Support/Minstock");
			if (file.mkdir()) {
				log.info("new folder");
			} else {
				log.info("return");
			}
			PATH = file.toString();
			log.info(file.toString());
			RUN = true;
		} else if (OS.isWin()) {
			String path = "C:\\" + System.getProperty("user.home") + "\\appdata\\roaming\\Minstock";
			File file = new File(path);
			if (file.mkdir()) {
				log.info("new folder");
			} else {
				log.info("return");
			}
			PATH = file.toString();
			log.info(file.toString());
			RUN = true;
		}
	}
}
