package net.intt.stock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.intt.util.LogManager;


public class FireStock extends Game {

	public String gme_name = "FireStock";
	public static String sgme_name = "FireStock";

	LogManager log = new LogManager(gme_name);

	SpriteBatch batch;

	Texture img;

	public static String PATH;

	String path;
	public FireStock(String path) {
		this.path = path;
	}

	void init() {
		PATH = path;
		log.info("init");
	}

	@Override
	public void create () {
		init();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		System.exit(0);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
