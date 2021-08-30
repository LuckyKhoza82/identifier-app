package org.apps.indentifier.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.apps.indentifier.entity.Client;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {
    public Client findByUniqueId(String idNumber){
        return find("idNumber", idNumber).firstResult();
    }
}
