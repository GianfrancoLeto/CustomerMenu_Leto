/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customermenu_leto;

import javafx.scene.image.ImageView;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Gianfranco Leto
 */

public class CustomerMenu_Leto extends Application {
    
    ArrayList<dishO> menuItem = new ArrayList<>();
    String name = "";
    String description = "";
    double price = 0;
    String image = "";
    Label nm = new Label();
    Label desc = new Label();
    Label prc = new Label();
    ImageView img = new ImageView();
    static int whereAt = 0;    
        
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {


        
        //read config.txt and save into object
        try {             
            Scanner sc = new Scanner(getClass().getResourceAsStream("/resources/config.txt"));
            while(sc.hasNextLine()){
                //testing
                //String i = sc.nextLine();
                //System.out.println(i);                
                //}

            //for(int i = 0; i < size; i++){
                for(int j = 0; j < 4; j++){
                    String nLine = sc.nextLine();
                    if(nLine.contains("Name: ")){
                        name = nLine.substring(6);
                    }
                    else if(nLine.contains("Description: ")){
                        description = nLine.substring(13);
                    }
                    else if(nLine.contains("Price: ")){
                        price = Double.parseDouble(nLine.substring(7));                 
                    }
                    else if(nLine.contains("Image: ")){
                        image = "/images/" +nLine.substring(7);
                        //System.out.println(image);
                    }  
                }
                
                //adding dishes
                //System.out.println("Test0");
                dishO item = new dishO(name,description,price,image);
                //System.out.println("Test1");
                menuItem.add(item);
                //System.out.println("Test2");
                //System.out.println(menuItem.size());
                }        
        }
              
        catch(Exception e){
            System.out.println("Error Found: " + e);
        }
                   
        //Label Start
        nm = new Label(menuItem.get(whereAt).name);
        desc = new Label(menuItem.get(whereAt).description);
        prc = new Label("Price: $" + menuItem.get(whereAt).price);
        img = new ImageView(menuItem.get(whereAt).image);
        
        
        //Format(Make it look nice-ish!)
        desc.setWrapText(true);
        desc.setMaxWidth(400);
        img.setFitHeight(300);
        img.setFitWidth(300);
        
        //set the gridpane
        //System.out.println(menuItem.get(0).name);
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(nm,0,1,2,1);
        gp.add(img,1,2,1,1);        
        gp.add(desc,0,3,2,1);
        gp.add(prc,0,4,2,1);
        
        //Add buttons
        Button Pbtn = new Button();        
        gp.add(Pbtn,1,5,1,1);
        Pbtn.setText("< Previous");
        Button Nbtn = new Button();        
        gp.add(Nbtn,3,5,1,1);
        Nbtn.setText("Next >");
                
        //Button inner workings  
        
        //Prev
        Pbtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if (whereAt != 0){
                    whereAt -= 1;
                    //primaryStage.close();
                    //Need to remove first! Almost forgot...
                    gp.getChildren().remove(nm);                
                    gp.getChildren().remove(desc);                    
                    gp.getChildren().remove(prc);                    
                    gp.getChildren().remove(img);                   
                    //Now create new
                    nm = new Label(menuItem.get(whereAt).name);
                    desc = new Label(menuItem.get(whereAt).description);
                    prc = new Label("Price: $" + menuItem.get(whereAt).price);
                    img = new ImageView(menuItem.get(whereAt).image);
                    desc.setWrapText(true);
                    desc.setMaxWidth(400);
                    img.setFitHeight(300);
                    img.setFitWidth(300);
                    gp.add(nm,0,1,2,1);
                    gp.add(img,1,2,1,1);        
                    gp.add(desc,0,3,2,1);
                    gp.add(prc,0,4,2,1);            
                    //primaryStage.show();
                }
            }
        });
        
        //Next
        Nbtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

                if(whereAt != menuItem.size()-1){
                    whereAt += 1;
                    //primaryStage.close();
                    gp.getChildren().remove(nm);            
                    gp.getChildren().remove(desc);                    
                    gp.getChildren().remove(prc);                    
                    gp.getChildren().remove(img);                     
                    nm = new Label(menuItem.get(whereAt).name);
                    desc = new Label(menuItem.get(whereAt).description);
                    prc = new Label("Price: $" + menuItem.get(whereAt).price);
                    img = new ImageView(menuItem.get(whereAt).image);
                    desc.setWrapText(true);
                    desc.setMaxWidth(400);
                    img.setFitHeight(300);
                    img.setFitWidth(300);
                    gp.add(nm,0,1,2,1);
                    gp.add(img,1,2,1,1);        
                    gp.add(desc,0,3,2,1);
                    gp.add(prc,0,4,2,1);                
                    //primaryStage.show();
                }
            }
        });
                        
        Scene scene = new Scene(gp, 600, 500);
        
        primaryStage.setTitle("Smart Menu Table: Cool Kids");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
