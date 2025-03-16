package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    // CORECTAM! --------------  filename poate fi final
    private final static String filename = "data/menu.txt";
    private List<MenuDataModel> listMenu;

    private void readMenu() throws Exception {
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(filename);
        // CORECTAM! --------------  nu folosim raw parameterised (adaugam <>)
        // this.listMenu= new ArrayList();
        this.listMenu= new ArrayList<>();
        // CORECTAM! --------------  nu are rost sa declaram inafara scopului de utilizare
//        BufferedReader br = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            // CORECTAM! --------------  initializare inutila cu null
//            String line = null;
            String line;
            while((line=br.readLine())!=null){
                MenuDataModel menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();

        } catch (FileNotFoundException e) {
            // CORECTAM! -------------- tratam exceptiile sau le propagam la celelalte nivele
            // dar nu e ok doar sa ii dam printStackRace
            e.printStackTrace();
            throw new Exception("eroare la incarcarea meniului");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("eroare la incarcarea meniului");
        }
    }

    private MenuDataModel getMenuItem(String line){
        // CORECTAM! --------------  initializare inutila cu null
//        MenuDataModel item=null;
        MenuDataModel item;
        // CORECTAM! --------------  equals compara cu un obiect(care e implicit string la noi)
//        if (line==null|| line.equals("")) return null;
        if (line==null|| line.isEmpty()) return null;

        StringTokenizer st=new StringTokenizer(line, ",");

        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuDataModel(name, 0, price);
        return item;
    }

    public List<MenuDataModel> getMenu() throws Exception {
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}
