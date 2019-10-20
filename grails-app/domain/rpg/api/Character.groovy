package rpg.api

import rpg.api.equipaments.Armor
import rpg.api.equipaments.Gloves
import rpg.api.equipaments.Helmet
import rpg.api.equipaments.Pants
import rpg.api.equipaments.Shoes
import rpg.api.weapons.Weapon

class Character {
    String name
    Weapon primaryWeapon
    Weapon secondaryWeapon
    Vocation vocation
    Helmet helmet
    Armor armor
    Pants pants
    Gloves gloves
    Shoes shoes

    static constraints = {
        primaryWeapon nullable: true
        secondaryWeapon nullable: true, validator: { Weapon newWeapon, Character character ->
            if (!character.primaryWeapon && newWeapon)
                return false
            if (character?.primaryWeapon?.isTwoHanded && newWeapon)
                return false
            return !newWeapon?.isTwoHanded
        }
        helmet nullable: true
        armor nullable: true
        pants nullable: true
        gloves nullable: true
        shoes nullable: true
    }

}
