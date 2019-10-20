package rpg.api.weapons

class Weapon {
    String name
    Float damage = 0
    Float defence = 0
    Boolean isTwoHanded = false

    static constraints = {
        damage nullable: true
        defence nullable: true
        isTwoHanded nullable: true
    }
}
