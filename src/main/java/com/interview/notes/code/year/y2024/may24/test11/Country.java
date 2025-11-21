package com.interview.notes.code.year.y2024.may24.test11;

class Country {
    private final String continent;
    private final int population;

    public Country(String continent, int population) {
        this.continent = continent;
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }
}