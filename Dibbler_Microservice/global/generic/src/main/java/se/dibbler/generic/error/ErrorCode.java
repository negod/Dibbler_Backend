/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.error;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public interface ErrorCode {

    public Integer getErrorCode();

    public String getErrorText();
    
    public String getErrorType();

}
