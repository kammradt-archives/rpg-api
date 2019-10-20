package rpg.api.equipaments

abstract class Equipment {
    String name
    Float defence = 0

    static constraints = {
        defence nullable: true
    }
}
