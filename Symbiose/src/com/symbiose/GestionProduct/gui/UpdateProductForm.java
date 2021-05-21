/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionProduct.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionProduct.entities.Product;
import com.symbiose.GestionProduct.services.ServiceProduct;


/**
 *
 * @author Mahdi
 */
public class UpdateProductForm extends Form {
    
    public UpdateProductForm(Form previous,String idM) {
        
        TextField tfName = new TextField("","Product Name");
        TextField tfDescription = new TextField("","Description");
        TextField tfPrice = new TextField("","Price: $$");
        TextField tfType = new TextField("","Type: Equipment or Clothing");
        TextField tfStatus = new TextField("","Status: 0 - 1");

       
        
        Container maincnt = new Container(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.y());
        Container cnt2 = new Container(BoxLayout.x());

        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        //tb.setBackCommand("", e -> new HomeForm(res).show());
        tb.setBackCommand("", e -> new List2ProductForm(previous).show());
        //Button btnDashboard = new Button("Go back Dashboard");
        //btnDashboard.addActionListener(e-> new List2EventForm(previous).show());
        
        
        Button btn = new Button("update");
        Button btncancel = new Button("cancel");
        
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
                
            if (tfName.getText()== null){
                    Dialog.show("Alert","Please fill all the fields", new Command("OK"));
                }
                else
                {
                    Product t = new Product(Integer.parseInt((idM)),tfName.getText(), Integer.parseInt(tfStatus.getText()), tfDescription.getText(), Float.parseFloat(tfPrice.getText()), Integer.parseInt(tfType.getText()));
                    ServiceProduct.getInstance().updateReponse(t);
                    Dialog.show("Success","Connection accepted", new Command("OK"));
                    new List2ProductForm(previous).show();
                }
            }
        });
        btncancel.addActionListener(e-> new List2ProductForm(previous).show());

        
        cnt1.addAll(tfName,tfDescription,tfPrice,tfType,tfStatus);
        cnt2.addAll(btn,btncancel);
        maincnt.addAll(cnt1,cnt2);
    }

   
}
