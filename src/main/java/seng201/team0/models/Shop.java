package seng201.team0.models;

import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

import java.util.ArrayList;

public class Shop {
    ArrayList<Tower> towers = new ArrayList<>();
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    ArrayList<Consumable> consumables = new ArrayList<>();
}
