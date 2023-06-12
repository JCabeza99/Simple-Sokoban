package es.upm.pproject.sokoban.model;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;

public class SaveStateManager {

	public static void save(SaveState save, File file) throws IOException{
		Gson gson=new Gson();
		String json=gson.toJson(save);
		try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
            writer.write(json);
        }
	}
	
	public static SaveState load(File file){
		SaveState loaded=null;
		try {
			FileReader rd=new FileReader(file);
			Gson gson=new Gson();
			loaded=gson.fromJson(rd, SaveState.class);
		}catch (Exception e){
			e.printStackTrace();
		}
		return loaded;
	}
}
