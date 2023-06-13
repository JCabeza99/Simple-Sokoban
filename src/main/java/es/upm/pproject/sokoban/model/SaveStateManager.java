package es.upm.pproject.sokoban.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class SaveStateManager {

	private SaveStateManager() {
	}

	public static void save(SaveState save, File file) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(save);
		try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
			writer.write(json);
		}
	}

	public static SaveState load(File file) {
		SaveState loaded = null;
		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr)) {
			Gson gson = new Gson();
			loaded = gson.fromJson(br, SaveState.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loaded;
	}
}
