package rpg.api

import rpg.api.equipaments.Armor
import rpg.api.equipaments.Gloves
import rpg.api.equipaments.Helmet
import rpg.api.equipaments.Pants
import rpg.api.equipaments.Shoes
import rpg.api.weapons.Weapon

class BootStrap {

    def init = { servletContext ->
        Helmet.saveAll(
                new Helmet(name: "Thin Helmet", defence: 4),
                new Helmet(name: "Fire Helmet", defence: 8),
                new Helmet(name: "Gold Helmet", defence: 20)
        )

        Armor.saveAll(
                new Armor(name: "Leather Armor", defence: 10),
                new Armor(name: "Iron Armor", defence: 20),
                new Armor(name: "Gold Armor", defence: 40)
        )

        Pants.saveAll(
                new Pants(name: "Sticky", defence: 10),
                new Pants(name: "Heavy with Iron", defence: 20),
                new Pants(name: "Fancy", defence: 40)
        )

        Gloves.saveAll(
                new Gloves(name: "Assassin Gloves", defence: 2),
                new Gloves(name: "Heavy with Iron", defence: 6),
                new Gloves(name: "Fancy", defence: 10)
        )

        Shoes.saveAll(
                new Shoes(name: "Wroker Shoes", defence: 3),
                new Shoes(name: "Fast Shoes", defence: 7),
                new Shoes(name: "Magic Shoes", defence: 13)
        )

        Weapon.saveAll(
                new Weapon(name: 'Sword'),
                new Weapon(name: 'Long Sword', damage: 30),
                new Weapon(name: 'Fight Club', damage: 20, defence: 10, isTwoHanded: true),
                new Weapon(name: 'Wood Shield', defence: 40, isTwoHanded: true),
                new Weapon(name: 'Iron Shield', defence: 30)
        )

        Character kamm = new Character(
                name: "Kammradt",
                vocation: Vocation.WARRIOR,
                helmet: Helmet.findByName("Gold Helmet"),
                armor: Armor.findByName("Gold Armor"),
                pants: Pants.findByName("Fancy"),
                primaryWeapon: Weapon.findByName('Fight Club')
        )

        Character alves = new Character(
                name: "Alves",
                vocation: Vocation.MAGE,
                shoes: Shoes.findByName("Magic Shoes"),
                gloves: Gloves.findByName("Fancy"),
                primaryWeapon: Weapon.findByName('Long Sword'),
                secondaryWeapon: Weapon.findByName('Iron Shield')
        )

        Character.saveAll(kamm, alves)
    }

    def destroy = {
    }
}
