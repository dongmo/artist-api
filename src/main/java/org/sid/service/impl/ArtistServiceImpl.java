package org.sid.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.sid.domaine.Artist;
import org.sid.repository.ArtistRepository;
import org.sid.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService{

	@Autowired
	ArtistRepository artistRepository;

	@Override
	public Artist create(Artist artist) {
		return this.artistRepository.save(artist);
	}

	@Override
	public Artist update(Artist artist, String username) {
		Optional<Artist> artistDb = this.artistRepository.findByUsername(username);
		
		if(artistDb.isPresent()) {
			Artist artistUpdate  = artistDb.get();
			artistUpdate.setArtistGenre(artist.getArtistGenre());
			artistUpdate.setArtistName(artist.getArtistName());
			artistUpdate.setAlbumsRecorded(artist.getAlbumsRecorded());
			return this.artistRepository.save(artist);
		}else {
			return null;
		}
	}

	@Override
	public List<Artist> findAll(int page, int size) {
		Pageable pagin = PageRequest.of(page-1, size);
		return this.artistRepository.findAll(pagin).toList();
	}

	@Override
	public Artist findByUsername(String username) throws NotFoundException {
		Optional<Artist> artistDb = this.artistRepository.findByUsername(username);
		if(artistDb.isPresent()) {
			return artistDb.get();
		}else {
			throw new NotFoundException("Record not found with username: "+username);
		}
	}

	@Override
	public void delete(String username) throws NotFoundException {
		Optional<Artist> artistDb = this.artistRepository.findByUsername(username);
		if(artistDb.isPresent()) {
			this.artistRepository.delete(artistDb.get());
		}else {
			throw new NotFoundException("Record not found with username: "+username);
		}
	}
	
	
}
