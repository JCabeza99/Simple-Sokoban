package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.view.Gui;
public class Controller {
	Player jugador;
	Level nivel;
	LevelFactory Generador;
	Gui gui;
	Controller(String path){
		jugador=new Player();
		Generador=new LevelFactory(path);
		gui =new Gui();
		nivel= Generador.GenerateLevel();
	}
	 public void mainController() {
		 boolean terminar=false;
		 int input;
		 while(!terminar) {
/*			 input=gui.getImput();
			 switch input{
			 	case input=0: acction =nivel.move(input)
			 				  acction.guardar
			 				  mover jugador;
			 				  gui.pintar(matriz y score )
			 				  break
			    case imput=4: generador=new LevelFactory(path)
			           		  nivel
			 }
*/			 
		 }
	 }
}
