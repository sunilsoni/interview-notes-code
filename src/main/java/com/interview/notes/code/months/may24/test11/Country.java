package com.interview.notes.code.months.may24.test11;

class Country {
    private String continent;
    private int population;

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