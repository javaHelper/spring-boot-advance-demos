package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoleModel;


public interface RoleService {

	public RoleModel createRole(RoleModel roleModel);

	public List<RoleModel> getAllRoles();

	public RoleModel getRoleById(Long roleId);

	public void deleteRoleById(Long roleId);
}