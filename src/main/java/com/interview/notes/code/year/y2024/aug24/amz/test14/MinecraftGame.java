package com.interview.notes.code.year.y2024.aug24.amz.test14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum AnimalType {
    SHEEP, WOLF, COW, CHICKEN, PIG
}

interface IFriendly {
    boolean isFriendly();

    void setFriendly(boolean friendly);
}

/*
Create a set of classes for a simple Minecraft type game.
Add suitable properties and methods to each class. Use good 00 practices.
Top level classes:
Player -
Properties: Name, location, health.
Methods - you define
Animal -
Properties: AnimalType, location, friendly/not, health.
Methods - you define
Character - a character played by the computer
Properties: CharacterType, location, friendly/not, health, greeting phrase.
Methods - you define
World - list of players, characters, animals.
Properties - you define."
Methods
CreateWorld() - Initialized a new world with 1 player, 3 characters and 2 animals.
FindNearby(Player/Animal/Character, distance) and returns a list of all Players, Characters, and Animals list of all Players, Characters, and Animals within distance.


 */
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

    public double distanceTo(Location other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }
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

class Player extends Entity {
    public Player(String name, Location location, int health) {
        super(name, location, health);
    }

    public void craft(String item) {
        System.out.println(getName() + " is crafting " + item);
    }
}

class Animal extends Entity implements IFriendly {
    private AnimalType animalType;
    private boolean friendly;

    public Animal(String name, AnimalType animalType, Location location, int health, boolean friendly) {
        super(name, location, health);
        this.animalType = animalType;
        this.friendly = friendly;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public void makeSound() {
        String sound;
        switch (animalType) {
            case SHEEP:
                sound = "baa";
                break;
            case WOLF:
                sound = "woof";
                break;
            case COW:
                sound = "moo";
                break;
            case CHICKEN:
                sound = "cluck";
                break;
            case PIG:
                sound = "oink";
                break;
            default:
                sound = "unknown";
                break;
        }
        System.out.println(getName() + " the " + animalType + " says " + sound);
    }
}

class Character extends Entity implements IFriendly {
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

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public void greet() {
        System.out.println(getName() + " the " + characterType + " says: " + greetingPhrase);
    }
}

class World {
    private List<Entity> entities;

    public World() {
        entities = new ArrayList<>();
    }

    public void createWorld() {
        entities.add(new Player("Steve", new Location(0, 0, 0), 100));
        entities.add(new Character("Bob", "Farmer", new Location(5, 0, 5), 50, true, "Hello, traveler!"));
        entities.add(new Character("Alice", "Trader", new Location(-5, 0, -5), 50, true, "Want to trade?"));
        entities.add(new Character("Charlie", "Warrior", new Location(10, 0, 10), 100, false, "Halt! Who goes there?"));
        entities.add(new Animal("Woolly", AnimalType.SHEEP, new Location(3, 0, 3), 20, true));
        entities.add(new Animal("Howler", AnimalType.WOLF, new Location(-3, 0, -3), 30, false));
    }

    public List<Entity> findNearby(Entity entity, double distance) {
        return entities.stream()
                .filter(e -> e != entity && e.getLocation().distanceTo(entity.getLocation()) <= distance)
                .collect(Collectors.toList());
    }

    public List<IFriendly> findFriendlyEntities() {
        return entities.stream()
                .filter(e -> e instanceof IFriendly)
                .map(e -> (IFriendly) e)
                .filter(IFriendly::isFriendly)
                .collect(Collectors.toList());
    }
}

public class MinecraftGame {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

        // Example 1: Find nearby entities for the player
        Player player = (Player) world.findNearby(new Player("Dummy", new Location(0, 0, 0), 100), 100).stream()
                .filter(e -> e instanceof Player)
                .findFirst()
                .orElse(null);

        if (player != null) {
            System.out.println("Nearby entities for " + player.getName() + ":");
            List<Entity> nearbyEntities = world.findNearby(player, 10);
            for (Entity entity : nearbyEntities) {
                System.out.println("- " + entity.getName());
            }
        }

        // Example 2: Character greeting
        world.findNearby(player, 100).stream()
                .filter(e -> e instanceof Character)
                .map(e -> (Character) e)
                .forEach(Character::greet);

        // Example 3: Animal sounds
        world.findNearby(player, 100).stream()
                .filter(e -> e instanceof Animal)
                .map(e -> (Animal) e)
                .forEach(Animal::makeSound);

        // Example 4: Add a new animal and make it sound
        Animal newCow = new Animal("Bessie", AnimalType.COW, new Location(1, 0, 1), 25, true);
        newCow.makeSound();

        // Example 5: Interact with friendly entities
        System.out.println("\nFriendly entities:");
        world.findFriendlyEntities().forEach(e -> {
            if (e instanceof Entity) {
                System.out.println("- " + ((Entity) e).getName());
            }
        });
    }
}
