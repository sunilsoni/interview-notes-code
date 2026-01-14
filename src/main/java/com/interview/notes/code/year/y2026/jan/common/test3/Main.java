package com.interview.notes.code.year.y2026.jan.common.test3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Project {
    String name;
    int hours;

    Project(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    String getName() { return name; }
    int getHours() { return hours; }
}

class Emp {
    List<Project> projects;

    Emp(List<Project> projects) {
        this.projects = projects;
    }

    List<Project> getProjects() {
        return projects;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Emp> employees = List.of(
            new Emp(List.of(
                new Project("Kafka", 10),
                new Project("Spring", 20)
            )),
            new Emp(List.of(
                new Project("Kafka", 15),
                new Project("AWS", 25)
            )),
            new Emp(List.of(
                new Project("Spring", 5),
                new Project("AWS", 10)
            ))
        );

        // Group by technology name and sum total hours
        Map<String, Integer> totalHoursByTech =
            employees.stream()
                     .flatMap(e -> e.getProjects().stream())
                     .collect(Collectors.groupingBy(
                         Project::getName,
                         Collectors.summingInt(Project::getHours)
                     ));

        System.out.println(totalHoursByTech); // {Kafka=25, Spring=25, AWS=35}
    }
}
