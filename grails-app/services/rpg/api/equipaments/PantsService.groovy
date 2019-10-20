package rpg.api.equipaments

import grails.gorm.services.Service

@Service(Pants)
interface PantsService {

    Pants get(Serializable id)

    List<Pants> list(Map args)

    Long count()

    void delete(Serializable id)

    Pants save(Pants pants)

}