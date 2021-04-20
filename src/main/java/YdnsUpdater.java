import business.YdnsUpdateManager;
import presentation.ConsoleIO;

public class YdnsUpdater {
    public static void main(String[] args) {
        YdnsUpdateManager.addUpdateEvent(ConsoleIO.DisplayDomainStatusAction());
        boolean running = true;
        while (running){
            YdnsUpdateManager.TryUpdate();
        }
    }
}
