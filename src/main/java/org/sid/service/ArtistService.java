package org.sid.service;

import java.util.List;

import org.sid.domaine.Artist;
import org.springframework.data.domain.Page;

import javassist.NotFoundException;

public interface ArtistService {
	
	Artist create(Artist artist);
	Artist update(Artist artist, String username);
	List<Artist> findAll(int pageSize, int offset);
	Artist findByUsername(String username) throws NotFoundException;
	void delete(String username) throws NotFoundException;
}
