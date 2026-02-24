package space;

import java.io.IOException;
import java.io.PrintStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import space.domain.CommunicationSatellite;
import space.domain.ImagingSatellite;
import space.repository.ConstellationRepository;
import space.services.SpaceOperationCenterService;

@SpringBootApplication
public class Main {
    private static final int CONSOLE_LINE_WIDTH = 60;

    // Команда для powershell перед запуском приложения (для корректного отображения кириллицы и эмодзи):
    // [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));

            ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
            launchControlSystem(context);
            context.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }     
    }

    private static void launchControlSystem(ConfigurableApplicationContext context) {
        System.out.println("\nЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println(String.valueOf('=').repeat(CONSOLE_LINE_WIDTH));

        ConstellationRepository constellationRepository = context.getBean(ConstellationRepository.class);
        SpaceOperationCenterService operationCenter = context.getBean(SpaceOperationCenterService.class);

        System.out.println("СОЗДАНИЕ СПЕЦИАЛИЗИРОВАННЫХ СПУТНИКОВ:");
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        CommunicationSatellite comSat1 = new CommunicationSatellite("Связь-1", 0.85, 500);
        CommunicationSatellite comSat2 = new CommunicationSatellite("Связь-2", 0.75, 1000);
        ImagingSatellite imgSat1 = new ImagingSatellite("ДЗЗ-1", 0.92, 2.5);
        ImagingSatellite imgSat2 = new ImagingSatellite("ДЗЗ-2", 0.45, 1.0);
        ImagingSatellite imgSat3 = new ImagingSatellite("ДЗЗ-3", 0.15, 0.5);

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.createAndSaveConstellation("Орбита-1");
        operationCenter.createAndSaveConstellation("Орбита-2");

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));
        System.out.println("ФОРМИРОВАНИЕ ГРУППИРОВКИ:");
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.addSatelliteToConstellation("Орбита-1", comSat1);
        operationCenter.addSatelliteToConstellation("Орбита-1", imgSat1);
        operationCenter.addSatelliteToConstellation("Орбита-1", imgSat2);
        operationCenter.addSatelliteToConstellation("Орбита-2", comSat2);
        operationCenter.addSatelliteToConstellation("Орбита-2", imgSat3);

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.showConstellationStatus("Орбита-1");
        operationCenter.showConstellationStatus("Орбита-2");

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.activateAllSatellites("Орбита-1");
        operationCenter.activateAllSatellites("Орбита-2");

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.executeConstellationMission("Орбита-1");
        operationCenter.executeConstellationMission("Орбита-2");

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.showConstellationStatus("Орбита-1");
        operationCenter.showConstellationStatus("Орбита-2");

        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        operationCenter.deactivateAllSatellites("Орбита-1");
        operationCenter.deactivateAllSatellites("Орбита-2");

        System.out.println(String.valueOf('=').repeat(CONSOLE_LINE_WIDTH));
    }
}