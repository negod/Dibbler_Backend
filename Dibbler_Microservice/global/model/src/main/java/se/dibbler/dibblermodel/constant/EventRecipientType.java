/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.dibblermodel.constant;

import javax.xml.bind.annotation.XmlEnum;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlEnum
public enum EventRecipientType {

    PUBLIC, SUBSCRIBER, NONE;
}
