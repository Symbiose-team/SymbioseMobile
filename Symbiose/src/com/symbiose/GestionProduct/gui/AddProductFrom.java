/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionProduct.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.symbiose.GestionProduct.entities.Product;
import com.symbiose.GestionProduct.services.ServiceProduct;

/**
 *
 * @author Oussema Mestiri
 */
public class AddProductFrom extends Form{

    public AddProductFrom(Form previous) {
        setTitle("Add a new product");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Product Name");
        TextField tfDescription = new TextField("","Description");
        TextField tfPrice = new TextField("","Price: $$");
        TextField tfType = new TextField("","Type: Equipment or Clothing");
        TextField tfStatus = new TextField("","Available or Sold");
        
        
        Button btnValider = new Button("Add product");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfName.getText().length() == 0)||(tfStatus.getText().length() == 0)||(tfDescription.getText().length() == 0)||(tfPrice.getText().length() == 0)||(tfType.getText().length() == 0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    try {
                        Product t = new Product(tfName.getText(), Integer.parseInt(tfStatus.getText()), tfDescription.getText(), Float.parseFloat(tfPrice.getText()), Integer.parseInt(tfType.getText()));
                        if(new ServiceProduct().addProduct(t))
                            Dialog.show("Success", "Product has been added", new Command("OK"));
                        else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(tfName,tfDescription,tfStatus,tfPrice,tfType,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
}
