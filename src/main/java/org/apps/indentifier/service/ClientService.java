package org.apps.indentifier.service;

import org.apache.commons.lang3.StringUtils;
import org.apps.indentifier.dto.ClientDto;
import org.apps.indentifier.entity.Client;
import org.apps.indentifier.mapper.ClientMapper;
import org.apps.indentifier.repository.ClientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    /**
     * Service to finding a client
     * @param idNumber
     * @param name
     * @param mobileNumber
     * @return response
     */
    public Response.ResponseBuilder findClient(String idNumber, String name, String mobileNumber){

        if(!StringUtils.isBlank(idNumber)){
            return Response.status(Response.Status.OK.getStatusCode())
                    .entity(new ClientDto("9202438957084", "Lucky", "Khoza",
                            "08377", "Centurion"));
            /* Code for retrieving data from db */
            /*return Response.status(Response.Status.OK.getStatusCode())
                    .entity(ClientMapper.INSTANCE.userEntityToDto(clientRepository.find(
                            "idNumber", idNumber).firstResult()));*/
        }

        if(!StringUtils.isBlank(name)){
            return Response.status(Response.Status.OK.getStatusCode())
                    .entity(new ClientDto("9202111111111", "LuckyLucky", "Khoza",
                            "08377", "Centurion"));
            /* Code for retrieving data from db */
            /*return Response.status(Response.Status.OK.getStatusCode())
                    .entity(ClientMapper.INSTANCE.userEntityToDto(clientRepository.find(
                            "name", name).firstResult()));*/
        }

        if(!StringUtils.isBlank(mobileNumber)){

            return Response.status(Response.Status.OK.getStatusCode())
                    .entity(new ClientDto("9202111111111", "Lucky", "Khoza",
                            "0837772323", "Centurion"));
            /* Code for retrieving data from db */
            /*return Response.status(Response.Status.OK.getStatusCode())
                    .entity(ClientMapper.INSTANCE.userEntityToDto(clientRepository.find(
                            "mobileNumber", mobileNumber).firstResult()));*/
        }

        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity("No valid parameter");
    }

    /**
     * Service for creating a client
     * @param client
     * @return response
     */
    @Transactional
    public Response.ResponseBuilder createClient(ClientDto client){

        if (client.getIdNumber().length() != 13) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Invalid ID Number: ID Number must be 13 digits long.");
        }

        if(findAllIdNumbers().contains(client.getIdNumber())){
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Invalid ID Number: ID Number already exists.");
        }

        if(findAllMobileNumbers().contains(client.getMobileNumber())){
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Invalid Phone Number: Phone Number already exists.");
        }

        /* Save to DB */
        //clientRepository.persist(ClientMapper.INSTANCE.userDtoToUserEntity(client));

        return Response.status(Response.Status.OK.getStatusCode())
                .entity("Saved Successfully");

    }

    public Response.ResponseBuilder updateClient(Long id, ClientDto client){

        /*Get item from db */
        //Optional<Client> dbClient = clientRepository.findByIdOptional(id);
        Optional<Client> dbClient = findClientById(id);

        if(dbClient.isPresent()){
            if (client.getIdNumber().length() != 13) {
                return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                        .entity("Invalid ID Number: ID Number must be 13 digits long.");
            }

            if(findAllIdNumbers().contains(client.getIdNumber()) || findAllMobileNumbers().contains(client.getMobileNumber())){
                /*Update client in DB*/
                //clientRepository.getEntityManager().merge(ClientMapper.INSTANCE.userDtoToUserEntity(client));

                return Response.status(Response.Status.OK.getStatusCode())
                        .entity("Updated Successfully.");
            }
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                .entity("No client found.");
    }

    /**
     * This is to mock a list of id numbers on the system
     */
    private List<String> findAllIdNumbers(){
        return Arrays.asList("8203235678081","8705235678081", "9203275678081");
    }

    /**
     * This is to mock a list of phone numbers on the system
     */
    private List<String> findAllMobileNumbers(){
        return Arrays.asList("0683457890","0783457890", "0823457890");
    }

    private Optional<Client> findClientById(Long id){
        if(id.equals(2L))
            return Optional.of(ClientMapper.INSTANCE.userDtoToUserEntity(new ClientDto("9202438957084",
                "Lucky", "Khoza","08377", "Centurion")));
        else
            return Optional.empty();
    }
}
