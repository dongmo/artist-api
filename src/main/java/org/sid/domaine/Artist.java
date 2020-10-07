package org.sid.domaine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "artists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Artist {
	
	@Id
	private String username;
	
	@NotBlank(message="le nom est obligatoire")
	private String artistName;
	
	@NotBlank(message="le genre est obligatoire")
	private String artistGenre;
	
	private int albumsRecorded;
	
}
