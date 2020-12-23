package by.mycompany.task.algorithm.handler;

import by.mycompany.task.algorithm.model.Service;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileHandler {

    private DateTimeFormatter dateTimeFormatter;
    private final String OUTPUT_FILE_PATH = "output.txt";

    public FileHandler() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    public boolean checkFile(File file) {
        if (file == null) {
            System.err.println("File is null");
            return false;
        }

        if (file.isDirectory() || !file.exists()) {
            System.err.println("File is not exists or is directory");
            return false;
        }

        if (file.length() == 0) {
            System.err.println("File length = 0");
            return false;
        }

        return true;
    }

    public ArrayList<Service> parseFile(File file) throws Exception {
        ArrayList<Service> serviceList = new ArrayList<>();
        Service service;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(" ");
                service = new Service(params[0], convertStringToTime(params[1]), convertStringToTime(params[2]));
                serviceList.add(service);
            }

        }

        return serviceList;
    }


    public void writeToFile(File file, ArrayList<Service> servicesPosh, ArrayList<Service> servicesGrotty) throws Exception {
        String filePath = file.getParentFile().getAbsolutePath() + OUTPUT_FILE_PATH;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Service service : servicesPosh) {
                writer.write(service.toString());
                writer.newLine();
            }

            for (Service service : servicesGrotty) {
                writer.newLine();
                writer.append(service.toString());
            }
        }
    }

    private LocalTime convertStringToTime(String time) throws Exception {
        if (time == null || "".equals(time.trim())) {
            return null;
        }

        return LocalTime.parse(time, dateTimeFormatter);
    }
}
