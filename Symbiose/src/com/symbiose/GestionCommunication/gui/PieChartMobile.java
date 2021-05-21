/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionCommunication.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.symbiose.GestionCommunication.entities.Publication;
import com.symbiose.GestionCommunication.services.ServicePublication;
import com.symbiose.GestionCommunication.services.ServiceCommentaire;

/**
 *
 * @author abbes
 */
public class PieChartMobile {
Resources theme;

        public String getName() {
    return "Budget chart";
    }

  
    public String getDesc() {
     return "(pie chart)";
    }
     
          /*   
        
               //  b = new SpanLabel("•• Ajout nouceau question ••");
        ServiceQuestion sq = new ServiceQuestion();
    
        int lenght =sq.getListQuestion().size();
       int i=0;
         double[] values  = new double[20] ;
   
             for(Question q: sq.getListQuestion() )
        {
         ServiceReponse sr = new  ServiceReponse();   
             varGlobales.setId(q.getId());
   values[i]= (sr.getDetailQuestion().size());
            i++;
   
        }
             
  */
             
             
             
 private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(40);
    renderer.setLegendTextSize(40);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}            
/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
                            theme = UIManager.initFirstTheme("/theme3");

    CategorySeries series = new CategorySeries(title);
    int k = 0;
    
        ServicePublication sq = new ServicePublication();

     
         
   
    
   for (double value : values) {
       
        series.add(sq.getListPub().get(k).getDescription(), value);
        k++;
   }
  
    return series;
}
 public Form createPieChartForm() {
    // Generate the values
   // double[] values = new double[]{12, 14, 11, 10, 19};

    ServicePublication sq = new ServicePublication();
    
        int lenght =sq.getListPub().size();
       int i=0;
      
         double[] values  = new double[lenght] ;
    int[] colors  = new int[lenght] ;
             for(Publication q: sq.getListPub())
        {
         ServiceCommentaire sr = new  ServiceCommentaire();   
             varGlobales.setId(q.getId());
   values[i]= (sr.getDetailQuestion().size());
  
   colors[i]=ColorUtil.BLUE;
            i++;
           
   
        }
   
   
    // Set up the renderer
   //int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.

        final CategorySeries seriesSet = buildCategoryDataset("Project budget", values);
    final com.codename1.charts.views.PieChart chart = new com.codename1.charts.views.PieChart(seriesSet, renderer);
    
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("PieChart des questions", new BorderLayout());
     
    f.getToolbar().addCommandToRightBar("retour", null, (ev)->{Accueil h=new Accueil();
          h.getF().show();
          });
    
    f.add(BorderLayout.CENTER, c);
    
    
    
    return f;

}       
 
     public Form getF() {
        return createPieChartForm();
        
    }

}   
    
  
    

