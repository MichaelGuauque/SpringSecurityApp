package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			//crear permisos
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			//crear roles
			RoleEntity adminRole = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			RoleEntity usertRole = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();
			RoleEntity inviteRole = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();
			RoleEntity developerRole = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//Crear usuarios
			UserEntity userMichael = UserEntity.builder()
					.username("michael")
					.password("$2a$10$eeTu3yyhB9G8J1ZzFTEF8ORHLLh4XV9iKq0nhOHSPP5gt2zOi42dy")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(adminRole))
					.build();
			UserEntity userAlejandro = UserEntity.builder()
					.username("alejandro")
					.password("$2a$10$eeTu3yyhB9G8J1ZzFTEF8ORHLLh4XV9iKq0nhOHSPP5gt2zOi42dy")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(developerRole))
					.build();
			UserEntity userEve = UserEntity.builder()
					.username("eve")
					.password("$2a$10$eeTu3yyhB9G8J1ZzFTEF8ORHLLh4XV9iKq0nhOHSPP5gt2zOi42dy")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(usertRole))
					.build();
			UserEntity userMarmo = UserEntity.builder()
					.username("marmo")
					.password("$2a$10$eeTu3yyhB9G8J1ZzFTEF8ORHLLh4XV9iKq0nhOHSPP5gt2zOi42dy")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(inviteRole))
					.build();

			//para guardar muchos datos del mismo tipo, se llama al metodo saveAll() y se le pasan los objetos
			userRepository.saveAll(List.of(userMichael, userAlejandro, userEve, userMarmo));
		};
	}
}
