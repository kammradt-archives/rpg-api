package rpg.api

import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional
class BattleService {

    CharacterService characterService

    def calculate(Long id) {
        Character character = characterService.get(id)

        Float damage = 0
        Float defence = 0

        if (character.primaryWeapon) {
            damage += character.primaryWeapon.damage
            defence +=  character.primaryWeapon.defence
            if (character.secondaryWeapon) {
                damage += character.secondaryWeapon.damage
                defence +=  character.secondaryWeapon.defence
            }
        }

        if (character.helmet) defence += character.helmet.defence
        if (character.armor)  defence += character.armor.defence
        if (character.pants)  defence += character.pants.defence
        if (character.gloves) defence += character.gloves.defence
        if (character.shoes)  defence += character.shoes.defence

        Float power = (damage + defence) * getVocationBonus(character.vocation)
        def response = [
                character: character,
                damage: damage.round(2),
                defence: defence.round(2),
                power: power.round(2)
        ]
        return response
    }

    def fight(List<Long> ids) {
        def ranking = []
        ids.each {
            ranking.add(calculate(it))
        }
        ranking.sort{ a, b -> b.power <=> a.power }

        def winner = ranking.first()
        return [winner: winner, ranking: ranking]
    }

    private def getVocationBonus(Vocation vocation) {
        switch (vocation){
            case Vocation.WARRIOR : return 1.2
            case Vocation.MAGE    : return 1.1
            case Vocation.PALADIN : return 1.3
            default               : return 1

        }
    }
}
