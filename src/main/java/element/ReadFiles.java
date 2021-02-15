package element;

import com.google.gson.Gson;
import org.openqa.selenium.By;

import java.io.*;

public class ReadFiles {

    public static Gson gson;

    public static void main(String[] args) {

        //Resources altindaki .json uzantili dosya listeleniyor.
        //for (File i : TestReadFiles.getFileNames(".json"))
        //  System.out.println(i.getName());

        System.out.println(ReadFiles.readLocator("siparislerim"));


    }


    public static By readLocator(String elementValue) {

        File[] files = getFileNames(".json");
        gson = new Gson();

        for (File file : files) {

            try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/" + file.getName())) {

                // Convert JSON File to Java Object
                ElementInfo[] elementInfos = gson.fromJson(reader, ElementInfo[].class);


                for (ElementInfo elementInfo : elementInfos) {


                    if (elementValue.equals(elementInfo.getKey())) {
                        //System.out.println(elementInfo.getKey() + elementInfo.getValue());

                        return ElementHelper.getElementInfoToBy(elementInfo);

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }


    public static File[] getFileNames(String fileType) {

        try {
            File f = new File(System.getProperty("user.dir") + "/src/main/resources");

            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File f, String name) {
                    // We want to find only .json files
                    return name.endsWith(fileType);
                }
            };
            return f.listFiles(filter);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
