package com.DevSuperior.DSClient.services;

import com.DevSuperior.DSClient.dto.ClientDTO;
import com.DevSuperior.DSClient.entities.Client;
import com.DevSuperior.DSClient.repositories.ClientRepository;
import com.DevSuperior.DSClient.services.exceptions.DatabaseException;
import com.DevSuperior.DSClient.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    // Retorna o cliente buscando pelo ID
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    // Retorna uma lista paginada dos clientes
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(ClientDTO::new);
    }

    // Cria novo cliente no banco de dados
    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }


    // Atualiza os dados de um cliente encontrado pelo id
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    // Deleta cliente pelo id
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    // Função auxiliar
    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBrithDate(dto.getBrithDate());
        entity.setChildren(dto.getChildren());
    }


}
