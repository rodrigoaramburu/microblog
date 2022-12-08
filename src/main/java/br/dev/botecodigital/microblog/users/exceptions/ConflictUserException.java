package br.dev.botecodigital.microblog.users.exceptions;

import java.util.HashSet;
import java.util.Set;

public class ConflictUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Set<String> conflicts;

	public ConflictUserException(Set<String> conflicts) {
		this.conflicts = conflicts;
	}

	public ConflictUserException(String conflict) {
		conflicts = new HashSet<String>();
		conflicts.add(conflict);
	}

	public Set<String> getConflicts() {
		return conflicts;
	}
	
	

}
