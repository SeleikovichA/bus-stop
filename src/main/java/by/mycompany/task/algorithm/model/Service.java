package by.mycompany.task.algorithm.model;

import java.time.LocalTime;
import java.util.Objects;

public class Service implements Comparable<Service>{
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

    public Service(String name, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isEffective(Service service) {
        if (startTime.compareTo(service.startTime) == 0 && endTime.compareTo(service.endTime) < 0) {
            return true;
        }

        if (startTime.compareTo(service.startTime) > 0 && endTime.compareTo(service.endTime) <= 0) {
            return true;
        }

        if (startTime.compareTo(service.startTime) == 0 && endTime.compareTo(service.endTime) == 0 && "Posh".equals(name)) {
            return true;
        }

        return false;
    }

    public int getTimeDifference(Service service){
        int time = this.startTime.getHour() * 60 + this.startTime.getMinute();
        int serviceTime = service.startTime.getHour() * 60 + service.startTime.getMinute();

        return time - serviceTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(name, service.name) &&
                Objects.equals(startTime, service.startTime) &&
                Objects.equals(endTime, service.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, endTime);
    }

    @Override
    public String toString() {
        return name + " " + startTime + " " + endTime;
    }

    @Override
    public int compareTo(Service o) {
        return startTime.compareTo(o.startTime);
    }
}
