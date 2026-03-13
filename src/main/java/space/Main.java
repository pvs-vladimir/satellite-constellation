package space;

import java.io.IOException;
import java.io.PrintStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import space.domain.satellites.CommunicationSatellite;
import space.domain.satellites.CommunicationSatelliteParam;
import space.domain.satellites.ImagingSatellite;
import space.domain.satellites.ImagingSatelliteParam;
import space.domain.satellites.Satellite;
import space.factory.CommunicationSatelliteFactory;
import space.factory.ImagingSatelliteFactory;
import space.factory.SatelliteFactory;
import space.repository.ConstellationRepository;
import space.services.FactorySatelliteService;
import space.services.SatelliteService;
import space.services.ConstellationService;

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
        ConstellationService operationCenter = context.getBean(ConstellationService.class);
        SatelliteFactory comFactory = context.getBean(CommunicationSatelliteFactory.class);
        SatelliteFactory imgFactory = context.getBean(ImagingSatelliteFactory.class);
        SatelliteService satelliteService = context.getBean(FactorySatelliteService.class);

        System.out.println("СОЗДАНИЕ СПЕЦИАЛИЗИРОВАННЫХ СПУТНИКОВ:");
        System.out.println(String.valueOf('-').repeat(CONSOLE_LINE_WIDTH));

        Satellite comSat1 = satelliteService.createSatellite(
            new CommunicationSatelliteParam("Связь-1", 0.85, 500)
        );
        Satellite comSat2 = satelliteService.createSatellite(
            new CommunicationSatelliteParam("Связь-2", 0.75, 1000)
        );
        Satellite imgSat1 = satelliteService.createSatellite(
            new ImagingSatelliteParam("ДЗЗ-1", 0.92, 2.5)
        );
        Satellite imgSat2 = satelliteService.createSatellite(
            new ImagingSatelliteParam("ДЗЗ-2", 0.45, 1.0)
        );
        Satellite imgSat3 = satelliteService.createSatellite(
            new ImagingSatelliteParam("ДЗЗ-3", 0.15, 0.5)
        );

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