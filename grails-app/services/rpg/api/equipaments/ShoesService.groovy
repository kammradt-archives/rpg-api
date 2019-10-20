package rpg.api.equipaments

import grails.gorm.services.Service

@Service(Shoes)
interface ShoesService {

    Shoes get(Serializable id)

    List<Shoes> list(Map args)

    Long count()

    void delete(Serializable id)

    Shoes save(Shoes shoes)

}