package persistence;

import business.model.YdnsConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class YdnsConfigFile {

    private static final Logger logger = LogManager.getLogger(YdnsConfigFile.class);

    public static Optional<YdnsConfig> fromFile(){
        if(!Files.exists(Paths.get("ydnsConfig.cfg"))) {
            prepareDefaultFile();
            return Optional.empty();
        } else {
            try {
                Reader fileReade = Files.newBufferedReader(Paths.get("ydnsConfig.cfg"));
                Gson gson =  new GsonBuilder()
                        .disableHtmlEscaping()
                        .setPrettyPrinting()
                        .create();
                YdnsConfig toReturn = gson.fromJson(fileReade, YdnsConfig.class);
                fileReade.close();
                return Optional.of(toReturn);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
                ex.printStackTrace();
                return Optional.empty();
            }
        }
    }

    public static void toFile(YdnsConfig ydnsConfig){
        try {
            Writer fileWriter = Files.newBufferedWriter(Paths.get("ydnsConfig.cfg"));
            Gson gson = new GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(ydnsConfig,fileWriter);
            fileWriter.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void prepareDefaultFile(){
        try {
            Files.createFile(Paths.get("ydnsConfig.cfg"));
            List<String> DummyDomains = new ArrayList<>();
            DummyDomains.add("DomainNr1.ydns.eu");
            DummyDomains.add("DomainNr2.ydns.eu");
            DummyDomains.add("DomainNr3.ydns.eu");
            YdnsConfig cfg = YdnsConfig.NewYdnsConfigBuilder("https://ydns.io/api/v1/update/?host=","","",DummyDomains);
            cfg.toFile();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
