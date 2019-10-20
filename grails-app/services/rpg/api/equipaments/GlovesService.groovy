package rpg.api.equipaments

import grails.gorm.services.Service

@Service(Gloves)
interface GlovesService {

    Gloves get(Serializable id)

    List<Gloves> list(Map args)

    Long count()

    void delete(Serializable id)

    Gloves save(Gloves gloves)

}