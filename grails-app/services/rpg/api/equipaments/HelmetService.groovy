package rpg.api.equipaments

import grails.gorm.services.Service

@Service(Helmet)
interface HelmetService {

    Helmet get(Serializable id)

    List<Helmet> list(Map args)

    Long count()

    void delete(Serializable id)

    Helmet save(Helmet helmet)

}