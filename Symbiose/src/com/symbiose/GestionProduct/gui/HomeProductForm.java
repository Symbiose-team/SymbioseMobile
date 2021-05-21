/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionProduct.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Oussema Mestiri
 */
public class HomeProductForm extends Form{
    Form current;

    public HomeProductForm(Resources res) {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddProduct = new Button("Add Product");
        Button btnList2Products = new Button("Products Available");
        Button btnListProduct = new Button("List Product");
        Button btnCart = new Button("Cart");


        btnAddProduct.addActionListener(e-> new AddProductFrom(current).show());
        
        btnList2Products.addActionListener(e-> new List2ProductForm(current).show());

        btnListProduct.addActionListener(e-> new ListProductsForm(current).show());
        
        btnCart.addActionListener(e-> new CartForm(current).show());
        
        addAll(btnAddProduct ,btnList2Products , btnCart);
        
    }
    
    
}
