package org.sid.repository;

import java.util.Optional;

import org.sid.domaine.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
	Optional<Artist> findByUsername(String username);
}
