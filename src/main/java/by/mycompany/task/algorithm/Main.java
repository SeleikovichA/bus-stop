package by.mycompany.task.algorithm;

import by.mycompany.task.algorithm.handler.FileHandler;
import by.mycompany.task.algorithm.handler.TimetableHandler;
import by.mycompany.task.algorithm.model.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try {
            if(args == null || args.length == 0){
                return;
            }

            String filePath = args[0];
            if (filePath == null || "".equals(filePath.trim())) {
                return;
            }

            File file = new File(filePath);
            FileHandler fileHandler = new FileHandler();

            if (fileHandler.checkFile(file)) {
                ArrayList<Service> serviceList = fileHandler.parseFile(file);

                TimetableHandler timetableHandler = new TimetableHandler();
                serviceList = timetableHandler.removeLongService(serviceList);
                serviceList = timetableHandler.removeDuplicateServices(serviceList);
                Collections.sort(serviceList);

                serviceList = timetableHandler.removeIneffectiveService(serviceList);

                ArrayList<Service> servicesPosh = timetableHandler.getServicesByName(serviceList, "Posh");
                ArrayList<Service> servicesGrotty = timetableHandler.getServicesByName(serviceList, "Grotty");

                fileHandler.writeToFile(file, servicesPosh, servicesGrotty);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
