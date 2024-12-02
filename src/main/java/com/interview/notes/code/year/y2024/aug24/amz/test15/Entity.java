package com.interview.notes.code.year.y2024.aug24.amz.test15;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Friendly {
    boolean isFriendly();
}

abstract class Entity {
    private String name;
    private Location location;
    private int health;

    public Entity(String name, Location location, int health) {
        this.name = name;
        this.location = location;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getHealth() {
        return health;
    }

    public void move(Location newLocation) {
        this.location = newLocation;
    }

    public void takeDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }
}

class Location {
    private int x, y, z;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

class Player extends Entity {
    public Player(String name, Location location, int health) {
        super(name, location, health);
    }

    public void craft(String item) {
        System.out.println(getName() + " is crafting " + item);
    }
}

class Animal extends Entity implements Friendly {
    private String animalType;
    private boolean friendly;

    public Animal(String name, String animalType, Location location, int health, boolean friendly) {
        super(name, location, health);
        this.animalType = animalType;
        this.friendly = friendly;
    }

    public String getAnimalType() {
        return animalType;
    }

    @Override
    public boolean isFriendly() {
        return friendly;
    }

    public void makeSound() {
        System.out.println(getName() + " the " + animalType + " makes a sound");
    }
}

class Character extends Entity implements Friendly {
    private String characterType;
    private boolean friendly;
    private String greetingPhrase;

    public Character(String name, String characterType, Location location, int health, boolean friendly, String greetingPhrase) {
        super(name, location, health);
        this.characterType = characterType;
        this.friendly = friendly;
        this.greetingPhrase = greetingPhrase;
    }

    public String getCharacterType() {
        return characterType;
    }

    @Override
    public boolean isFriendly() {
        return friendly;
    }

    public void greet() {
        System.out.println(getName() + " says: " + greetingPhrase);
    }
}

class World {
    private List<Entity> entities;

    public World() {
        entities = new ArrayList<>();
    }

    public void createWorld() {
        entities.add(new Player("Player1", new Location(0, 0, 0), 100));
        entities.add(new Character("Villager1", "Villager", new Location(5, 0, 5), 50, true, "Welcome, traveler!"));
        entities.add(new Character("Merchant", "Trader", new Location(-5, 0, -5), 50, true, "Want to trade?"));
        entities.add(new Character("Guard", "Warrior", new Location(10, 0, 10), 100, false, "Halt! Who goes there?"));
        entities.add(new Animal("Fluffy", "Sheep", new Location(3, 0, 3), 20, true));
        entities.add(new Animal("Growler", "Wolf", new Location(-3, 0, -3), 30, false));
    }

    public List<Entity> findNearby(Entity entity, double distance) {
        return entities.stream()
                .filter(e -> e != entity && calculateDistance(entity.getLocation(), e.getLocation()) <= distance)
                .collect(Collectors.toList());
    }

    private double calculateDistance(Location loc1, Location loc2) {
        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) +
                Math.pow(loc1.getY() - loc2.getY(), 2) +
                Math.pow(loc1.getZ() - loc2.getZ(), 2));
    }
}
