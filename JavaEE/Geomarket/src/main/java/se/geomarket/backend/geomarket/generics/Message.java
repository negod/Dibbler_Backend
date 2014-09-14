/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class Message<E extends BaseEntity, D extends BaseDto> {

    Status status = Status.SUCCESS;

    Class<E> entityClass;
    Class<D> dtoClass;

    public enum Status {

        FAILURE, SUCCESS
    }

    public boolean success() {
        return status == status.SUCCESS;
    }

    public Message(Status status) {
        this.status = status;
    }

    public Message(E entity, D dto) {
      
    }
}

