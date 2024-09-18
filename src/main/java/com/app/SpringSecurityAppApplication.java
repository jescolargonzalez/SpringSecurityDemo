package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {SpringApplication.run(SpringSecurityAppApplication.class, args);}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			// Crea permisos */
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

			// Crea roles */
			RoleEntity adminRole = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity userRole = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity invitedRole = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity developerRole = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			// Crea USERS */
			UserEntity user = UserEntity.builder()
					.username("user")
					.password(passwordEncoder.encode("user"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(userRole))
					.build();

			UserEntity admin = UserEntity.builder()
					.username("admin")
					.password(passwordEncoder.encode("admin"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(adminRole))
					.build();

			UserEntity invited = UserEntity.builder()
					.username("invited")
					.password(passwordEncoder.encode("invited"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(invitedRole))
					.build();

			UserEntity developer = UserEntity.builder()
					.username("developer")
					.password(passwordEncoder.encode("developer"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(developerRole))
					.build();

			UserEntity userAll = UserEntity.builder()
					.username("userAll")
					.password("userAll")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(userRole, adminRole, invitedRole, developerRole))
					.build();

			userRepository.saveAll(List.of(user, admin, invited, developer, userAll));

		//ver contraseña encriptada
		System.out.println("");
		System.out.println("USER DEFAULTS");
		System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Username : admin " + " Password : " + new BCryptPasswordEncoder().encode("admin"));
		System.out.println("Username : user " + " Password : " + new BCryptPasswordEncoder().encode("user"));
		System.out.println("Username : invited " + " Password : " + new BCryptPasswordEncoder().encode("invited"));
		System.out.println("Username : developer " + " Password : " + new BCryptPasswordEncoder().encode("developer"));
		System.out.println("Username : userAll " + " Password : " + new BCryptPasswordEncoder().encode("userAll"));
		System.out.println("----------------------------------------------------------------------------------------------------");


		};
	}
}
