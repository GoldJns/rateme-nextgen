package com.app.rateme.repository;

import com.app.rateme.model.Role;

public interface RoleRepository {

    Role findByAuthority(String string);

}
