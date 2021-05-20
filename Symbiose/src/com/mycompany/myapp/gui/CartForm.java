/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ServiceProduct;
import java.util.ArrayList;
/**
 *
 * @author Oussema Mestiri
 */
public class CartForm extends Form{
    Form current;
    SpanLabel lb;

    
        
    public CartForm(Form previous) {

        
        setTitle("List of roducts");
        System.out.println(ServiceProduct.getInstance().getAllProducts());
        ArrayList<Product> products = new ArrayList();
        products =ServiceProduct.getInstance().getAllProducts();
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.setUIID("Container");
        tb.setBackCommand("back", e -> previous.showBack());
        /*
        Button btnAddEvent = new Button("Add event");
        btnAddEvent.addActionListener(e-> new AddEventForm(current).show());
        Container cnt = new Container(BoxLayout.x());
        
        cnt.add(btnAddEvent);
        add(cnt);
        */
        for (Product p :products ) {
                    
                Container cnt1 = new Container(BoxLayout.y());
                Container cnt2 = new Container(BoxLayout.x());
                String idM = String.valueOf(p.getId());
                Button delete = new Button ("Delete from cart",idM);
                Button separator = new Button ("-----------------------------");


                SpanLabel SLnom = new SpanLabel("Name :"+p.getName());
                SpanLabel SLdescription = new SpanLabel("Description :"+p.getDescription());
                SpanLabel SLstate = new SpanLabel("State :"+p.getState());
                SpanLabel SLprice = new SpanLabel("Price :"+p.getPrice());
                SpanLabel SLtype = new SpanLabel("Type :"+p.getType());

		
                cnt1.add(SLnom);
                cnt1.add(SLdescription);
                cnt1.add(SLstate);
                cnt1.add(SLprice);
                cnt1.add(SLtype);
                cnt1.add(delete);
                cnt1.add(separator);
                cnt2.add(cnt1);
                
                
                
                delete.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if(new ServiceProduct().deleteProduct(delete.getUIID())){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                            new List2ProductForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR","Server error", new Command("OK"));
                    }
                });
                
                   
            add(cnt2);
        }
    }
}

