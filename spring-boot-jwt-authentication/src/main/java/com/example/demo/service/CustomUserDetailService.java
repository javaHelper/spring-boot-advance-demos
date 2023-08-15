package com.example.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
    public UserModel register(UserModel userModel){

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);//it does not do a deep copy

        Set<RoleEntity> roleEntities = new HashSet<>();
        //fetch every role from DB based on role id and than set this role to user entity roles
        for(RoleModel rm :userModel.getRoles()){
            Optional<RoleEntity> optRole = roleRepository.findById(rm.getId());
            if(optRole.isPresent()){
                roleEntities.add(optRole.get());
            }
        }
        userEntity.setRoles(roleEntities);
        userEntity.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        userEntity = userRepository.save(userEntity);

        BeanUtils.copyProperties(userEntity, userModel);

        //convert RoleEntities to RoleModels
        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(RoleEntity re :userEntity.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }
        userModel.setRoles(roleModels);
        return userModel;
    }

    //this method actually does the validation for user existence
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

         UserEntity userEntity = userRepository.findByUsername(userName);

        if(userEntity == null){//here you can make a DB call with the help of repository and do the validation
            throw new UsernameNotFoundException("User does not exist!");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);

        //convert RoleEntities to RoleModels
        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(RoleEntity re :userEntity.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }

        userModel.setRoles(roleModels);
        return userModel;
    }
}