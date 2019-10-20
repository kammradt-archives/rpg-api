package rpg.api.equipaments

import grails.gorm.services.Service

@Service(Armor)
interface ArmorService {

    Armor get(Serializable id)

    List<Armor> list(Map args)

    Long count()

    void delete(Serializable id)

    Armor save(Armor armor)

}