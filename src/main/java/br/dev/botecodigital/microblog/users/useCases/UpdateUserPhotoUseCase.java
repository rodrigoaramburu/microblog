package br.dev.botecodigital.microblog.users.useCases;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.shared.FileStoreService;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class UpdateUserPhotoUseCase {
	
	@Autowired
	private FileStoreService fileStore;
	
	@Autowired
	private UserRepository userRepository;

	public User execute(UUID authUserId, String photoBase64) {
		String path = this.fileStore.writeFileBase64(photoBase64, new File("profiles/"+authUserId.toString()+".jpg"));
		
		User user = this.userRepository.findById(authUserId).get();
		
		user.setPhoto(path);
		
		this.userRepository.save(user);

		return user;
	}

}
