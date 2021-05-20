/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFields.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import GestionFields.services.ServiceTask;

/**
 *
 * @author Chaima
 */
public class ListeForm extends Form {
    public ListeForm (Form dds){
        setTitle("Liste de terrain ");
         SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> dds.showBack()); // Revenir vers l'interface précédente
    }
    
}
