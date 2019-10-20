package rpg.api.weapons

import grails.gorm.services.Service

@Service(Weapon)
interface WeaponService {

    Weapon get(Serializable id)

    List<Weapon> list(Map args)

    Long count()

    void delete(Serializable id)

    Weapon save(Weapon weapon)

}