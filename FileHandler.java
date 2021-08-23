import java.io.*;
import java.util.ArrayList;
/*
This class contains all the methods who are responsible for handling the file.
 */
public class FileHandler {

    public FileHandler(){
        //nothing
    }

    /*
     This method add a new account in the file.
     */
    public  static void writeToFile(String name,String filename) {
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));

            out.write(name);
            out.write("\t");
            out.write("0");
            out.write("\t");
            out.write("0");
            out.write("\t");
            out.write("0");
            out.write("\n");
            out.close();
        }
        catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }
    }

    /*
    This method search and found the name that is parameter if there is in the file or not.
     */
    public static int foundName(String name, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\t");

                for (String word : words) {
                    if (word.equals(name)) {
                        return 1;
                    }
                }
            }
            return 0;
        }
        catch (IOException e) {
            e.printStackTrace();
            return 100;
        }
    }


    /*
    This method returns all the names of the file.
     */
    public static ArrayList<String> getNamesOfFile(){
        ArrayList<String> names=new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new FileReader("Users.txt"));
            String line;
            String Name;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                Name=tmp[0];
                names.add(Name);
            }
            return  names;
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return names;
    }

    /*
    This method returns all the wins of the file.
     */
    public static  ArrayList<Integer> getWinsOfFile(){
        ArrayList<Integer> highScores=new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new FileReader("Users.txt"));
            String line;
            int score;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                score= Integer.parseInt(tmp[1]);
                highScores.add(score);
            }
            return  highScores;
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return highScores;
    }

    /*
    This method returns all the draws of the file.
     */
    public static  ArrayList<Integer> getDrawsOfFile(){
        ArrayList<Integer> wins=new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new FileReader("Users.txt"));
            String line;
            int win;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                win=Integer.parseInt(tmp[2]);
                wins.add(win);
            }
            return  wins;
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return wins;
    }

    /*
    This method returns all the loses of the file.
     */
    public static  ArrayList<Integer> getLosesOfFile(){
        ArrayList<Integer> wins=new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new FileReader("Users.txt"));
            String line;
            int win;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                win=Integer.parseInt(tmp[3]);
                wins.add(win);
            }
            return  wins;
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return wins;
    }



    /*
    This method returns the wins for the player which the name that is in the parameter.
     */
    public static int findWins(String name, String filename){
        try{
            BufferedReader br=new BufferedReader(new FileReader(filename));
            String line;
            int highscore;
            String Name;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                for(String ignored : tmp){
                    Name=tmp[0];
                    highscore= Integer.parseInt(tmp[1]);
                    if(Name.equals(name)){
                        return highscore;
                    }
                }
            }

        }
        catch (IOException e ) {
            e.printStackTrace();
            return 100;
        }
        return 100;
    }

    /*
   This method returns the draws for the player which the name that is in the parameter.
    */
    public static int findDraws(String name, String filename){
        try{
            BufferedReader br=new BufferedReader(new FileReader(filename));
            String line;
            int wins;
            String Name;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                for(String ignored : tmp){
                    Name=tmp[0];
                    wins=Integer.parseInt((tmp[2]));
                    if(Name.equals(name)){
                        return wins;
                    }
                }

            }
        }
        catch (IOException e ) {
            e.printStackTrace();
            return 100;
        }
        return 100;
    }

    /*
   This method returns the loses for the player which the name that is in the parameter.
    */
    public static int findLoses(String name, String filename){
        try{
            BufferedReader br=new BufferedReader(new FileReader(filename));
            String line;
            int wins;
            String Name;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                for(String ignored : tmp){
                    Name=tmp[0];
                    wins=Integer.parseInt((tmp[3]));
                    if(Name.equals(name)){
                        return wins;
                    }
                }

            }
        }
        catch (IOException e ) {
            e.printStackTrace();
            return 100;
        }
        return 100;
    }


    /*
    This method add 1 win to player which the name that is in the parameter.
     */
    public static void add1Win(String file, String name, int wins1, int draws1, int loses1){
        try{
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Integer> wins = new ArrayList<>();
            ArrayList<Integer> draws = new ArrayList<>();
            ArrayList<Integer> loses = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            String Name;
            int win;
            int draw;
            int lose;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                for(String ignored : tmp){
                    Name=tmp[0];
                    win= Integer.parseInt(tmp[1]);
                    draw=Integer.parseInt(tmp[2]);
                    lose=Integer.parseInt(tmp[3]);
                    if(!Name.equals(name)){
                        names.add(Name);
                        wins.add(win);
                        draws.add(draw);
                        loses.add(lose);
                    }
                    break;
                }
            }

            wins1++;

            FileWriter writer = new FileWriter(file);
            for(int i=0;i<names.size();i++){
                writer.write(names.get(i)+"\t"+wins.get(i)+"\t"+draws.get(i)+"\t"+loses.get(i)+"\n");
            }
            writer.write(name+"\t"+wins1+"\t"+draws1+"\t"+loses1+"\n");
            writer.close();

        } catch (IOException e ) {
            e.printStackTrace();
        }
    }


    /*
    This method add 1 draw to player which the name that is in the parameter.
     */
    public static void add1Draw(String file, String name, int wins1, int draws1, int loses1){
        try{
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Integer> wins = new ArrayList<>();
            ArrayList<Integer> draws = new ArrayList<>();
            ArrayList<Integer> loses = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            String Name;
            int win;
            int draw;
            int lose;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                for(String ignored : tmp){
                    Name=tmp[0];
                    win= Integer.parseInt(tmp[1]);
                    draw=Integer.parseInt(tmp[2]);
                    lose=Integer.parseInt(tmp[3]);
                    if(!Name.equals(name)){
                        names.add(Name);
                        wins.add(win);
                        draws.add(draw);
                        loses.add(lose);
                    }
                    break;
                }
            }

            draws1++;

            FileWriter writer = new FileWriter(file);
            for(int i=0;i<names.size();i++){
                writer.write(names.get(i)+"\t"+wins.get(i)+"\t"+draws.get(i)+"\t"+loses.get(i)+"\n");
            }
            writer.write(name+"\t"+wins1+"\t"+draws1+"\t"+loses1+"\n");
            writer.close();

        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    /*
    This method add 1 lose to player which the name that is in the parameter.
     */
    public static void add1Lose(String file, String name, int wins1, int draws1, int loses1){
        try{
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Integer> wins = new ArrayList<>();
            ArrayList<Integer> draws = new ArrayList<>();
            ArrayList<Integer> loses = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            String Name;
            int win;
            int draw;
            int lose;

            while((line=br.readLine())!=null){
                String[] tmp =line.split("\t");
                for(String ignored : tmp){
                    Name=tmp[0];
                    win= Integer.parseInt(tmp[1]);
                    draw=Integer.parseInt(tmp[2]);
                    lose=Integer.parseInt(tmp[3]);
                    if(!Name.equals(name)){
                        names.add(Name);
                        wins.add(win);
                        draws.add(draw);
                        loses.add(lose);
                    }
                    break;
                }
            }

            loses1++;

            FileWriter writer = new FileWriter(file);
            for(int i=0;i<names.size();i++){
                writer.write(names.get(i)+"\t"+wins.get(i)+"\t"+draws.get(i)+"\t"+loses.get(i)+"\n");
            }
            writer.write(name+"\t"+wins1+"\t"+draws1+"\t"+loses1+"\n");
            writer.close();

        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

}
