import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main {
    private static final int CONSOLE_LINE_WIDTH = 50;

    // Команда для powershell перед запуском приложения (для корректного отображения кириллицы и эмодзи):
    // [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));

            launchControlSystem();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }     
    }

    private static void launchControlSystem() {
        System.out.println("\nЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println(String.valueOf('=').repeat(CONSOLE_LINE_WIDTH));

        System.out.println("СОЗДАНИЕ СПЕЦИАЛИЗИРОВАННЫХ СПУТНИКОВ:");
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        ArrayList<Satellite> satellites = new ArrayList<>();
        satellites.add(new CommunicationSatellite("Связь-1", 0.85, 500));
        satellites.add(new CommunicationSatellite("Связь-2", 0.75, 1000));
        satellites.add(new ImagingSatellite("ДЗЗ-1", 0.92, 2.5));
        satellites.add(new ImagingSatellite("ДЗЗ-2", 0.45, 1.0));
        satellites.add(new ImagingSatellite("ДЗЗ-3", 0.15, 0.5));

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        SatelliteConstellation constellation = new SatelliteConstellation("RU Basic");

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));
        System.out.println("ФОРМИРОВАНИЕ ГРУППИРОВКИ:");
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        for (Satellite satellite : satellites) {
            constellation.addSatellite(satellite);
        }

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));
        System.out.println(constellation.toString());
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));
        System.out.println("АКТИВАЦИЯ СПУТНИКОВ:");
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        for (Satellite satellite : satellites) {
            satellite.activate();
        }

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));
        System.out.println(String.format("ВЫПОЛНЕНИЕ МИССИЙ ГРУППИРОВКИ %s",
                                         constellation.getConstellationName()));
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        constellation.executeAllMissions();

        System.out.println(String.valueOf('=').repeat(CONSOLE_LINE_WIDTH));
    }
}