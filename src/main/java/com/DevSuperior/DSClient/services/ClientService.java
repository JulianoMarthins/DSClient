package com.DevSuperior.DSClient.services;

import com.DevSuperior.DSClient.dto.ClientDTO;
import com.DevSuperior.DSClient.entities.Client;
import com.DevSuperior.DSClient.repositories.ClientRepository;
import com.DevSuperior.DSClient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    // TODO: Implementar método findById
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    //TODO: implementar método findAll
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(ClientDTO::new);
    }

    // TODO: implementar método insert

    // TODO: Implementar método update

    // TODO: Implementar método delete



}
