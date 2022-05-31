/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import View.View;
import Model.Model;
import Controller.Controller;

/**
 *
 * @author Aluito Aryo Prabowo
 */
public class MVC{
    View view = new View();
    Model model = new Model();
    Controller control = new Controller(model, view);//controller yg awal parameter sama yg di controller awal juga
}
