package by.mycompany.task.algorithm.handler;

import by.mycompany.task.algorithm.model.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimetableHandler {
    private DateTimeFormatter dateTimeFormatter;
    private final LocalTime MAX_EFFECTIVE_TIME = LocalTime.parse("01:00");

    public TimetableHandler() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    public ArrayList<Service> removeLongService(ArrayList<Service> serviceList) throws Exception {
        LocalTime startTime, endTime;

        for (int i = 0; i < serviceList.size(); i++) {
            Service service = serviceList.get(i);

            startTime = service.getStartTime();
            endTime = service.getEndTime();
            LocalTime differenceTime = endTime.minusMinutes(startTime.getMinute()).minusHours(startTime.getHour());

            if (MAX_EFFECTIVE_TIME.compareTo(differenceTime) < 0) {
                serviceList.remove(i);
                i--;
            }

        }

        return serviceList;
    }

    public ArrayList<Service> removeIneffectiveService(ArrayList<Service> serviceList) throws Exception {
        for (int i = 0; i < serviceList.size(); i++) {
            Service service = serviceList.get(i);
            for (int j = 0; j < serviceList.size(); j++) {
                Service effectiveService = serviceList.get(j);
                int timeDifference = service.getTimeDifference(effectiveService);

                if (service.equals(effectiveService)) {
                    continue;
                }

                if (timeDifference > 60) {
                    continue;
                }

                if (timeDifference < 0) {
                    break;
                }

                if (service.isEffective(effectiveService)) {
                    if (i >= j) {
                        i--;
                    }

                    serviceList.remove(j);
                    j--;
                }

            }
        }

        return serviceList;
    }

    public ArrayList<Service> removeDuplicateServices(ArrayList<Service> serviceList) throws Exception {
        Set<Service> set = new LinkedHashSet<>();
        set.addAll(serviceList);

        serviceList.clear();
        serviceList.addAll(set);

        return serviceList;
    }

    public ArrayList<Service> getServicesByName(ArrayList<Service> serviceList, String name) throws Exception {
        ArrayList<Service> servicesByName = new ArrayList<>();
        for (int i = 0; i < serviceList.size(); i++) {
            Service service = serviceList.get(i);
            if (name.equals(service.getName())) {
                servicesByName.add(service);
            }
        }

        return servicesByName;
    }

}
