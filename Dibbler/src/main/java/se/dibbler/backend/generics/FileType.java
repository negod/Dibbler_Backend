/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum FileType {

    JPG("jpg"),
    PNG("png"),
    XML("xml");

    private final String id;

    FileType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getIdWithDot() {
        return "." + id;
    }

}
