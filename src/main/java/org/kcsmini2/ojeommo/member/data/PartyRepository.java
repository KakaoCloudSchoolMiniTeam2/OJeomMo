package org.kcsmini2.ojeommo.member.data;

import org.kcsmini2.ojeommo.member.data.entity.Party;
import org.kcsmini2.ojeommo.member.data.entity.PartyPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, PartyPk> {
}
