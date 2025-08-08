package com.apexon.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Nagaraj Kharvi
 * @version 1.0
 * @since 29/07/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identifier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2442778896121136685L;
	
	private Long id;
	private Long foreignKey;
	private String word;
	private Long localeCd;
	private UUID uuid;
	private HttpHeaders headers;
	private Pageable pageable;
	private Identifier parentIdentifier; //locale Identifier
}