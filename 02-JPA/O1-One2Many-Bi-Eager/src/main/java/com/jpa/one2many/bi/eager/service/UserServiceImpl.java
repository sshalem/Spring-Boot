package com.jpa.one2many.bi.eager.service;

import java.util.List;

import com.jpa.one2many.bi.eager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;
import com.jpa.one2many.bi.eager.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(long id) {
        // UserEntity userEntity = userRepository.findById(id);
        // UserEntity userEntity = userRepository.nativeFindById(id);
        UserEntity userEntity = userRepository.jpqlFindById(id);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with id = " + id);
        return userEntity;
    }

    @Override
    public UserEntity getUserByPid(long pid) {
        UserEntity userEntity = userRepository.findByPid(pid);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with pid = " + pid);
        return userEntity;
    }

    @Override
    public UserEntity getUserByName(String name) {
        UserEntity userEntity = userRepository.findByName(name);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with name = " + name);
        return userEntity;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with email = " + email);
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void removeUserByPid(long pid) {
        UserEntity userEntity = userRepository.findByPid(pid);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with pid = " + pid);
        userRepository.delete(userEntity);
    }

    @Override
    @Transactional
    public UserEntity addRoleToUser(long userPid, RoleEntity roleEntity) {
        System.out.println(roleEntity);
        UserEntity userEntity = userRepository.findByPid(userPid);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with userPid = " + userPid);
        roleEntity.setPid(userPid);
        userEntity.addRole(roleEntity);
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity addRoleUpdateUser(long userPid, UserEntity userEntity) {
        System.out.println(userEntity);
        UserEntity _userEntityDB = userRepository.findByPid(userPid);
        if (_userEntityDB == null)
            throw new ResourceNotFoundException("Not found User with userPid = " + userPid);
        System.out.println(_userEntityDB);
        _userEntityDB.setPassword(userEntity.getPassword());

        userEntity.getRoles().forEach(role -> _userEntityDB.addRole(role));

        return userRepository.save(_userEntityDB);
    }

    /**
     * @Transactional Annotation - Should be only on 'PUBLIC' methods that returns
     * value to higher level layer And : ✅ Rule of Thumb Any method
     * that modifies entities (insert/update/delete) in JPA/Hibernate
     * should usually be transactional so Hibernate can flush changes
     * . Read-only operations can be non-transactional (Unless I use
     * Fetch.Lazy), but updates need @Transactional so Hibernate can
     * flush changes.
     */
    @Override
    @Transactional
    public UserEntity removeRoleFromUser(long userPid, String role) {
        /**
         * In this Implementation 1. I add the orphanRemoval to UserEntity One2Many 2. I
         * Query For RoleEntity (I try with 2 different implementations) 3. remove the
         * Entity from the SET<RoleEntity> collection 4. Save the the info to UserEntity
         * 5. I must add @Transactional annotation to the method `removeRoleFromUser()`
         *
         * ✅ Rule of Thumb Any method that modifies entities (insert/update/delete) in
         * JPA/Hibernate should usually be transactional so Hibernate can flush changes
         * . Read-only operations can be non-transactional (Unless I use Fetch.Lazy),
         * but updates need @Transactional so Hibernate can flush changes.
         */
        UserEntity userEntity = userRepository.findByPid(userPid);
        if (userEntity == null)
            throw new ResourceNotFoundException("Not found User with userPid = " + userPid);
        /**
         * there are 4 different ways to retrieve roleEntity from DB
         */
        /**
         * @Transactional Annotation - Should be only on 'PUBLIC' methods that returns
         *                value to higher level layer And : ✅ Rule of Thumb Any method
         *                that modifies entities (insert/update/delete) in JPA/Hibernate
         *                should usually be transactional so Hibernate can flush changes
         *                . Read-only operations can be non-transactional (Unless I use
         *                Fetch.Lazy), but updates need @Transactional so Hibernate can
         *                flush changes.
         */
        /**
         * (1) - Not best approach We Don't need to add @Transactional If we search with
         * For loop With this Implementation , NO NEED orphanRemoval = true on
         * the @OneToMany also @OneToMany is W/O CascadeType.REMOVE
         */
//		Set<RoleEntity> roles = userEntity.getRoles();
//
//		RoleEntity roleEntity = null;
//
//		need to check what's faster :
//		[1] Fetching the role form DB , as I do in the example (2) (3) (4)
//		[2] Or, Iterate the roles and get the role I want
//		for (RoleEntity r : roles) {
//			if (r.getRole().equals(role)) {
//				roleEntity = r;
//			}
//		}
//		// In this approach
//		// [1] I remove the role from the collection in memory
//		// [2] I explicitly delete roleEntity from Role_DB
//		// [3] I don't the userRepository.save(userEntity);
//		userEntity.removeRole(roleEntity);
//		roleRepository.delete(roleEntity);
//		return userEntity;
        /**
         * (2) Query from UserRepo will work only if orphanRemoval = true. Note:
         * CascadeType.REMOVE ,only propagates when the parent entity itself is removed.
         * and Here I remove a child (role), not parent (user) , Thus, Doesn't matter in
         * this situation if Parent (User) is with or w/o CascadeType.REMOVE
         */
        RoleEntity roleEntity = userRepository.getRoleByIdAndRole(userEntity.getId(), role);
        userEntity.removeRole(roleEntity);
        // WHen I config orphanRemoval = flase , and NO CASCADE , I need do
        // do this line : roleRepository.save(roleEntity);
        // and not this line userRepository.save(userEntity);
        // Question why:
        // Why I do roleRepository.save(roleEntity) and No doing
        // userRepository.save(userEntity)
        // Answer : this is so I won't delete the role from DB, but I set it to null ,
        // when I do the line of userEntity.removeRole(roleEntity);
        // But it still deleting role From DB : rrrrrrrrrrrrrrrrrrrrrrr

        userRepository.save(userEntity);
        //        roleRepository.delete(roleEntity);

//		 roleRepository.save(roleEntity);
        return userEntity;
        /**
         * (3) Query from RoleRepo * Query from UserRepo will work only if orphanRemoval
         * = true. * Note: CascadeType.REMOVE ,only propagates when the parent entity
         * itself is removed. * and Here I remove a child (role), not parent (user) , *
         * Thus, Doesn't matter in this situation if Parent (User) is with or w/o
         * CascadeType.REMOVE
         */
//		RoleEntity roleEntity = roleRepository.jpqlFindRoleByPidAndRoleName(userPid, role);
//		userEntity.removeRole(roleEntity);
//        return userRepository.save(userEntity);
        /**
         * (4) Query from RoleRepo * Query from UserRepo will work only if orphanRemoval
         * = true. * Note: CascadeType.REMOVE ,only propagates when the parent entity
         * itself is removed. * and Here I remove a child (role), not parent (user) , *
         * Thus, Doesn't matter in this situation if Parent (User) is with or w/o
         * CascadeType.REMOVE
         */
//		RoleEntity roleEntity = roleRepository.findByPidAndRole(userPid, role);
// 		userEntity.removeRole(roleEntity);
//        return userRepository.save(userEntity);
    }
}
